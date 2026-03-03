from transformers import pipeline

# Load HuggingFace sentiment model
sentiment_analyzer = pipeline("sentiment-analysis",model = "distilbert-base-uncased-finetuned-sst-2-english")

# Sample reviews
reviews = [
    "This product is amazing and works perfectly!",
    "Very bad quality, waste of money.",
    "Product is okay, not great not bad."
]

# Classify reviews
for review in reviews:
    result = sentiment_analyzer(review)[0]
    print("Review:", review)
    print("Sentiment:", result['label'])
    print("Confidence:", result['score'])
    print()