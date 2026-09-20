import app from "./app"
import postRoutes from "./routes/postsRoute";

app.use("/api/users", postRoutes);

const PORT = process.env.PORT || 3001;

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
