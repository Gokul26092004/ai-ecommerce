import { useState, useMemo } from "react";
import { Link } from "react-router-dom";

/* ✅ Static data outside component */
const PRODUCTS = [
  { id: 1, name: "Wireless Headphones", price: 99.99, category: "Electronics" },
  { id: 2, name: "Smart Watch", price: 149.99, category: "Electronics" },
  { id: 3, name: "Gaming Mouse", price: 49.99, category: "Gaming" },
  { id: 4, name: "Mechanical Keyboard", price: 89.99, category: "Gaming" },
  { id: 5, name: "Running Shoes", price: 129.99, category: "Fashion" },
];

const Products = () => {
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("All");
  const [sort, setSort] = useState("");

  const filteredProducts = useMemo(() => {
    let result = [...PRODUCTS];

    if (search) {
      result = result.filter((p) =>
        p.name.toLowerCase().includes(search.toLowerCase())
      );
    }

    if (category !== "All") {
      result = result.filter((p) => p.category === category);
    }

    if (sort === "low-high") {
      result.sort((a, b) => a.price - b.price);
    }

    if (sort === "high-low") {
      result.sort((a, b) => b.price - a.price);
    }

    return result;
  }, [search, category, sort]); // ✅ no warning now

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="text-3xl font-bold text-center mb-6">Products</h1>

      {/* Filters */}
      <div className="flex flex-col md:flex-row gap-4 mb-6 justify-center">
        <input
          type="text"
          placeholder="Search product..."
          className="p-2 border rounded"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <select
          className="p-2 border rounded"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="All">All Categories</option>
          <option value="Electronics">Electronics</option>
          <option value="Gaming">Gaming</option>
          <option value="Fashion">Fashion</option>
        </select>

        <select
          className="p-2 border rounded"
          value={sort}
          onChange={(e) => setSort(e.target.value)}
        >
          <option value="">Sort By</option>
          <option value="low-high">Price: Low → High</option>
          <option value="high-low">Price: High → Low</option>
        </select>
      </div>

      {/* Product Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {filteredProducts.length === 0 ? (
          <p className="text-center col-span-3 text-gray-500">
            No products found
          </p>
        ) : (
          filteredProducts.map((product) => (
            <div key={product.id} className="bg-white p-4 rounded shadow">
              <h2 className="text-xl font-semibold">{product.name}</h2>
              <p className="text-gray-600">{product.category}</p>
              <p className="text-green-600 font-bold">${product.price}</p>

              <Link
                to={`/products/${product.id}`}
                className="text-blue-600 hover:underline"
              >
                View Details →
              </Link>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Products;
