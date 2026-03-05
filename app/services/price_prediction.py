import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression

# Load dataset
data = pd.read_csv("price_data.csv")

# Features
X = data[['demand','rating','competitor_price','stock']]

# Target
y = data['price']

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

# Train model
model = LinearRegression()
model.fit(X_train, y_train)

# Predict price
sample = [[85,4.6,1200,40]]

predicted_price = model.predict(sample)

print("Suggested Price:", predicted_price[0])