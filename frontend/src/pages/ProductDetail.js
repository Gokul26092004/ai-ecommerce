import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";

const ProductDetail = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    const products = [
      {
        id: 1,
        name: "Wireless Headphones",
        description: "High quality noise cancelling headphones",
        price: 99.99,
        category: "Electronics",
      },
      {
        id: 2,
        name: "Smart Watch",
        description: "Water resistant smart watch",
        price: 149.99,
        category: "Electronics",
      },
      {
        id: 3,
        name: "Gaming Mouse",
        description: "RGB gaming mouse with high DPI",
        price: 49.99,
        category: "Gaming",
      },
    ];

    const foundProduct = products.find(
      (p) => p.id === parseInt(id)
    );
    setProduct(foundProduct);
  }, [id]);

  if (!product) {
    return <div className="p-6">Product Out of Stock</div>;
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-3xl mx-auto bg-white p-6 rounded shadow">
        <h1 className="text-3xl font-bold mb-4">
          {product.name}
        </h1>

        <p className="text-gray-700 mb-2">
          {product.description}
        </p>

        <p className="text-green-600 font-bold mb-2">
          ${product.price}
        </p>

        <p className="text-gray-500 mb-6">
          Category: {product.category}
        </p>

        <button className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 mb-4">
          Add to Cart
        </button>

        <br />

        {/* 🔙 BACK LINK */}
        <Link
          to="/products"
          className="text-blue-600 hover:underline"
        >
          ← Back to Products
        </Link>
      </div>
    </div>
  );
};

export default ProductDetail;
