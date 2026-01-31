from fastapi import FastAPI
from recommender import recommend

app = FastAPI()

@app.get("/recommend/{product_id}")
def get_recommendation(product_id:int):
    return recommend(product_id)