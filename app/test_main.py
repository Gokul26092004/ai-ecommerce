import sys
import os
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

import pytest
from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)

def test_health_check():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "AI service running"}

def test_recommend():
    response = client.post(
        "/recommand",
        json={"user_id": 1, "product_id": 101, "limit": 3}
    )
    assert response.status_code == 200
    assert "recommand_ids" in response.json()

def test_search():
    response = client.post(
        "/search",
        json={"query": "laptop"}
    )
    assert response.status_code == 200
    assert "results" in response.json()

def test_sentiment():
    response = client.post(
        "/sentiment",
        json={"text": "This product is great!"}
    )
    assert response.status_code == 200
    assert response.json() == {"sentiment": "POSITIVE"}
