import shutil

from fastapi import FastAPI, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
from app.schemas.request import RecommendRequest, SearchRequest, SentimentRequest
from app.services.visual_search_service import search_similar

app = FastAPI(title="AI microservice")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods =["*"],
    allow_headers=["*"],
)

@app.get("/health")
def health_check():
    return {"status": "AI service running"}

@app.post("/recommand")
def recommand(req:RecommendRequest):
    return {"recommand_ids" : [102,103,110]}

@app.post("/search")
def search(req: SearchRequest):
    return {"results" : ["product_1","product_2"]}

@app.post("/sentiment")
def sentiment(req:SentimentRequest):
    return {"sentiment":"POSITIVE"}

import uuid
import os

@app.post("/visual-search")
async def visual_search(file: UploadFile = File(...)):
    unique_filename = f"query_{uuid.uuid4().hex}.jpg"
    file_location = os.path.join("data", unique_filename)
    os.makedirs("data", exist_ok=True)

    with open(file_location, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)

    results = search_similar(file_location)

    # Clean up the file after search to save space
    if os.path.exists(file_location):
        os.remove(file_location)

    return {"similar_products": results}