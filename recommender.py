import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

df = pd.read_csv("data/products.csv")

tfidf = TfidfVectorizer(stop_words='english')
tfidf_matrix = tfidf.fit_transform(df["description"])

cosine_sim = cosine_similarity(tfidf_matrix)

def recommend(product_id, top_n=3):
    idx = df.index[df["id"] == product_id][0]
    scores = list(enumerate(cosine_sim[idx]))
    scores = sorted(scores, key=lambda x: x[1], reverse=True)
    product_indices = [i[0] for i in scores[1:top_n+1]]
    return df.iloc[product_indices].to_dict(orient="records")
