import {ImageCategory} from "@/scripts/ts/models/enums/ImageCategory";
import { ImageOrientation } from "./enums/ImageOrientation";

export interface Image {
    id: number;
    title: string;
    orientation: ImageOrientation;
    categories: ImageCategory[];
    file: File;
    created: string;
}