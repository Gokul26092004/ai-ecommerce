from fastapi import FastAPI,UploadFile,File
from recommender import recommend
import shutil
from app.services.visual_search_service import search_similar

app = FastAPI()

@app.get("/recommend/{product_id}")
def get_recommendation(product_id:int):
    return recommend(product_id)
@app.post("/visual-search")
async def visual_search(file: UploadFile = File(...)):
    file_location = f"data/query.jpg"

    with open(file_location, "wb") as buffer:
        shutil.copyfileobj(file.file,buffer)

    results = search_similar(file_location)

    return {"similar_products": results}