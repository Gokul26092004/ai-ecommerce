import { Link } from "react-router-dom";
import { getCart, updateQuantity, removeFromCart } from "../utils/cartUtils";
import { useState } from "react";

const Cart = () => {
  const [cart, setCart] = useState(getCart());

  const handleQtyChange = (id, qty) => {
    updateQuantity(id, qty);
    setCart(getCart());
  };

  const handleRemove = (id) => {
    removeFromCart(id);
    setCart(getCart());
  };

  const total = cart.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  if (cart.length === 0) {
    return (
      <div className="text-center mt-10">
        <h2 className="text-2xl font-bold">Your cart is empty</h2>
        <Link to="/products" className="text-blue-600 underline">
          Continue Shopping
        </Link>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="text-3xl font-bold text-center mb-6">Your Cart</h1>

      <div className="max-w-3xl mx-auto bg-white p-4 rounded shadow">
        {cart.map((item) => (
          <div
            key={item.id}
            className="flex justify-between items-center border-b py-3"
          >
            <div>
              <h3 className="font-semibold">{item.name}</h3>
              <p>₹ {item.price}</p>
            </div>

            <div className="flex items-center gap-2">
              <button
                onClick={() => handleQtyChange(item.id, item.quantity - 1)}
                className="px-2 bg-gray-300 rounded"
              >
                -
              </button>

              <span>{item.quantity}</span>

              <button
                onClick={() => handleQtyChange(item.id, item.quantity + 1)}
                className="px-2 bg-gray-300 rounded"
              >
                +
              </button>
            </div>

            <button
              onClick={() => handleRemove(item.id)}
              className="text-red-600"
            >
              Remove
            </button>
          </div>
        ))}

        {/* TOTAL */}
        <div className="text-right mt-4 font-bold text-xl">
          Total: ₹ {total}
        </div>

        {/* ✅ CHECKOUT BUTTON */}
        <Link
          to="/checkout"
          className="block mt-6 bg-green-600 text-white text-center py-2 rounded hover:bg-green-700"
        >
          Proceed to Checkout
        </Link>
      </div>
    </div>
  );
};

export default Cart;
