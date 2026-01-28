from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from app.schemas.request import RecommendRequest, SearchRequest, SentimentRequest

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