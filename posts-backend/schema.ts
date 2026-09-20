import { model, Schema } from "mongoose";
export const farmingTypes = [
    "agriculture",
    "horticulture",
    "floriculture",
    "sericulture",
    "apiculture",
    "aquaculture",
    "mariculture",
    "poultry",
    "dairy",
    "livestock",
    "mixed",
    "agroforestry",
    "organic",
    "urban",
    "precision",
    "hydroponics",
    "aeroponics",
    "aquaponics",
    "vertical",
    "mechanized",
    "permaculture",
] as const;

const postSchema = new Schema({
    title: { type: String, required: true, trim: true },
    farmingType: {
        type: String,
        required: true,
        enum: farmingTypes
    },
    sections: [{ subHeading: String, text: String, imageUrl: String }],
    createdAt: { type: Date, default: Date.now },
    author: {
        name: { type: String, required: true },
        email: { type: String, required: true }
    }
});

export const Post = model('Post', postSchema);
