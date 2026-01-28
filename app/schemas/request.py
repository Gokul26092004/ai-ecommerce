from pydantic import BaseModel

class RecommendRequest(BaseModel):
    product_id : int

class SearchRequest(BaseModel):
    query : str

class SentimentRequest(BaseModel):
    text : str