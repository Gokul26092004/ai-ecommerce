# AI E-Commerce Microservice

An AI microservice for e-commerce platforms, built with FastAPI. Currently features a working **visual (image-based) product search** using a pretrained CNN, alongside recommendation, text search, and sentiment endpoints that are scaffolded and in progress.

---

## 🚀 Features

### ✅ Visual Search (implemented)
Upload a product image and find visually similar products from a dataset. Uses a pretrained **MobileNetV2** (torchvision) for feature extraction and cosine similarity for ranking matches.

### 🧪 Content-Based Recommendations (standalone script, not yet wired to API)
`recommender.py` computes product recommendations using **TF-IDF** on product descriptions + cosine similarity. Works as a standalone script against `data/products.csv`; not yet exposed through the API.

### 🚧 In Progress
- `/recommand` — recommendation endpoint (currently returns placeholder data; not yet connected to `recommender.py`)
- `/search` — text-based product search (placeholder)
- `/sentiment` — review sentiment classification (placeholder)

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| API | FastAPI, Uvicorn |
| Visual search | PyTorch, Torchvision (MobileNetV2), Pillow |
| Recommendations | scikit-learn (TF-IDF, cosine similarity), pandas |
| NLP (planned) | Hugging Face Transformers |

---

## 📁 Repository Structure

```
├── app/
│   ├── main.py                          # FastAPI app & route definitions
│   ├── schemas/
│   │   └── request.py                   # Pydantic request models
│   └── services/
│       └── visual_search_service.py     # MobileNetV2 feature extraction + similarity search
├── data/
│   ├── images/                          # Product image dataset for visual search
│   └── products.csv                     # Product catalog used by recommender.py
├── recommender.py                       # Standalone TF-IDF recommendation script
├── requirements.txt
└── README.md
```

---

## ⚙️ Getting Started

```bash
git clone https://github.com/Gokul26092004/ai-ecommerce.git
cd ai-ecommerce
pip install -r requirements.txt

# run the API
uvicorn app.main:app --reload
```

- `GET /health` — service status
- `POST /visual-search` — upload an image, get visually similar products from `data/images/`

Run the standalone recommender separately:
```bash
python recommender.py
```

---

## 🗺️ Roadmap

- [ ] Wire `recommender.py` logic into the `/recommand` endpoint
- [ ] Implement real text search (`/search`)
- [ ] Implement sentiment analysis on reviews (`/sentiment`) using Hugging Face Transformers
- [ ] Add tests and API documentation
