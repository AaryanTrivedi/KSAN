import { Request, Response, Router } from "express";
import { Post, farmingTypes } from "../schema"

async function createPost(request: Request, response: Response) {
    try {
        const post = new Post(request.body);
        await post.save();
        response
            .status(201)
            .send(post);
    } catch (error: any) {
        response
            .status(400)
            .send({ error: error.message });
    }
}

async function fetchPosts(request : Request, response : Response) {
    const farmingType = request.query.farmingType;
    if (typeof farmingType !== "string") {
        return response.status(400).json({
            error: "farmingType query parameter is required"
        });
    }

    if (!farmingTypes.includes(farmingType as typeof farmingTypes[number])) {
        return response.status(400).json({
            error: `Invalid farmingType. Allowed values: ${farmingTypes.join(", ")}`
        });
    }
    try {
        const posts = await Post.find({ farmingType }).select("title _id createdAt sections");
        response.send(posts.map(post => ({ id: post._id, title: post.title, createdAt: post.createdAt, sections: post.sections })));
    } catch (error:any) {
        response.status(500).send({ error: error.message });
    }
}

async function getPostByID(request: Request, response: Response) {
  try {
      const post = await Post.findById(request.params.id);
      if (!post) {
          return response.status(404).send({ error: 'Post not found' });
      }
      response.send(post);
  } catch (error : any) {
      response.status(500).send({ error: error.message });
  }
}

async function updatePost(request : Request, response : Response) {
    try {
        const post = await Post.findByIdAndUpdate(request.params.id, request.body, { new: true, runValidators: true });
        if (!post) {
            return response.status(404).send({ error: 'Post not found' });
        }
        response.send(post);
    } catch (error : any) {
        response.status(400).send({ error: error.message });
    }
}

async function deletePost(request : Request, response : Response) {
    try {
        const post = await Post.findByIdAndDelete(request.params.id);
        if (!post) {
            return response.status(404).send({ error: 'Post not found' });
        }
        response.send({ message: 'Post deleted successfully' });
    } catch (error : any) {
        response.status(500).send({ error: error.message });
    }
}

const router = Router();


router.post('/', createPost);           // Create a new Post
router.get('/', fetchPosts);            // Fetch Posts by farming type
router.get('/:id', getPostByID);       // Get one Post by its ID
router.put('/:id', updatePost);        // Update Post by ID
router.delete('/:id', deletePost);     // Delete post by ID

export default router;
