import torch
import torchvision.models as models
import torchvision.transforms as transforms
from PIL import Image
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
import os

# Load pretrained MobileNet
model = models.mobilenet_v2(pretrained=True)
model.classifier = torch.nn.Identity()
model.eval()

transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
])

DATASET_PATH = "data/images"

# Extract features
def extract_features(image_path):
    image = Image.open(image_path).convert("RGB")
    image = transform(image).unsqueeze(0)

    with torch.no_grad():
        features = model(image)

    return features.numpy().flatten()


# Index dataset images
def index_images():
    features = []
    paths = []

    for file in os.listdir(DATASET_PATH):
        path = os.path.join(DATASET_PATH, file)
        features.append(extract_features(path))
        paths.append(path)

    return np.array(features), paths


# Search similar products
def search_similar(query_image_path):
    dataset_features, dataset_paths = index_images()
    query_features = extract_features(query_image_path)

    similarities = cosine_similarity(
        [query_features], dataset_features
    )

    top_indices = similarities[0].argsort()[-4:][::-1]

    results = []
    for i in top_indices:
        results.append({
            "image_path": dataset_paths[i],
            "score": float(similarities[0][i])
        })

    return results
