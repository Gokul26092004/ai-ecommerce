import { useParams } from "react-router-dom";
import { addToCart } from "../utils/cartUtils";
import { useNavigate } from "react-router-dom";



const sampleProducts = [
  {
    id: 1,
    name: "iPhone 15",
    price: 79999,
    category: "Mobile",
    image: "https://via.placeholder.com/400",
    description: "Latest Apple smartphone with A17 chip.",
  },
  {
    id: 2,
    name: "Samsung Galaxy S23",
    price: 69999,
    category: "Mobile",
    image: "https://via.placeholder.com/400",
    description: "High-end Android phone with AMOLED display.",
  },
  {
    id: 3,
    name: "Dell XPS Laptop",
    price: 99999,
    category: "Laptop",
    image: "https://via.placeholder.com/400",
    description: "Powerful laptop for professionals.",
  },
];



const ProductDetails = () => {
  const { id } = useParams();
  const product = sampleProducts.find((p) => p.id === Number(id));
  const navigate = useNavigate();
const handleOrderNow = () => {
  addToCart(product);
  navigate("/checkout");
};

  if (!product) {
    return <h2 className="text-center mt-10">Product out of stock</h2>;
  }

  return (
    <div className="p-6 bg-gray-100 min-h-screen flex justify-center">
      <div className="bg-white p-6 rounded-xl shadow-md max-w-xl w-full">
        <img
          src={product.image}
          alt={product.name}
          className="h-64 w-full object-cover rounded"
        />

        <h1 className="text-2xl font-bold mt-4">{product.name}</h1>
        <p className="text-gray-600 mt-2">{product.description}</p>
        <p className="text-xl font-semibold mt-3">₹ {product.price}</p>

        <button
          onClick={() => addToCart(product)}
          className="w-full mt-4 bg-green-600 text-white py-2 rounded"
        >
          Add to Cart
        </button>
        <button onClick={handleOrderNow}className="w-full mt-3 bg-green-600 text-white py-2 rounded hover:bg-green-700"
>
  Order Now
</button>

      </div>
    </div>
  );
};

export default ProductDetails;
