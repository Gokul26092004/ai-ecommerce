import { useState, useMemo, useEffect } from "react";
import { Link } from "react-router-dom";
import { addToCart } from "../utils/cartUtils";
import api from "../services/api";

const Products = () => {
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("All");
  const [sort, setSort] = useState("");
  const [productsData, setProductsData] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await api.get("/products/all");
        setProductsData(response.data);
      } catch (error) {
        console.error("Failed to fetch products", error);
      }
    };
    fetchProducts();
  }, []);

  const filteredProducts = useMemo(() => {
    let products = [...productsData];

    if (search) {
      products = products.filter((p) =>
        p.name.toLowerCase().includes(search.toLowerCase())
      );
    }

    if (category !== "All") {
      products = products.filter((p) => p.category === category);
    }

    if (sort === "low") {
      products.sort((a, b) => a.price - b.price);
    } else if (sort === "high") {
      products.sort((a, b) => b.price - a.price);
    }

    return products;
  }, [search, category, sort]);

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold text-center mb-6">Products</h1>

      {/* 🔍 Filters */}
      <div className="flex flex-wrap gap-4 justify-center mb-6">
        <input
          type="text"
          placeholder="Search product..."
          className="p-2 border rounded w-60"
          onChange={(e) => setSearch(e.target.value)}
        />

        <select
          className="p-2 border rounded"
          onChange={(e) => setCategory(e.target.value)}
        >
          <option>All</option>
          <option>Mobile</option>
          <option>Laptop</option>
        </select>

        <select
          className="p-2 border rounded"
          onChange={(e) => setSort(e.target.value)}
        >
          <option value="">Sort By</option>
          <option value="low">Price: Low → High</option>
          <option value="high">Price: High → Low</option>
        </select>
      </div>

      {/* 🛍 Product Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {filteredProducts.map((product) => (
          <div
            key={product.id}
            className="bg-white p-4 rounded-xl shadow hover:shadow-lg transition"
          >
            <img
              src={product.image}
              alt={product.name}
              className="h-48 w-full object-cover rounded"
            />

            <h2 className="text-xl font-semibold mt-3">{product.name}</h2>
            <p className="text-gray-600">₹ {product.price}</p>

            <div className="flex gap-2 mt-3">
              <Link
                to={`/products/${product.id}`}
                className="flex-1 text-center bg-blue-600 text-white py-1 rounded"
              >
                View Details
              </Link>

              <button
                onClick={() => addToCart(product)}
                className="flex-1 bg-green-600 text-white py-1 rounded"
              >
                Add to Cart
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Products;
