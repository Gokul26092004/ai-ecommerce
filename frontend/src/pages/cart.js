import { Link } from "react-router-dom";
import { getCart, updateQuantity, removeFromCart } from "../utils/cartUtils";
import { useState, useEffect } from "react";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);

  const fetchCart = async () => {
    const cartData = await getCart();
    setCartItems(cartData.items || []);
  };

  useEffect(() => {
    fetchCart();
  }, []);

  const handleQtyChange = async (itemId, qty) => {
    if (qty < 1) return;
    await updateQuantity(itemId, qty);
    await fetchCart();
  };

  const handleRemove = async (itemId) => {
    await removeFromCart(itemId);
    await fetchCart();
  };

  const total = cartItems.reduce(
    (sum, item) => sum + (item.product?.price || 0) * item.quantity,
    0
  );

  if (cartItems.length === 0) {
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
        {cartItems.map((item) => (
          <div
            key={item.id}
            className="flex justify-between items-center border-b py-3"
          >
            <div>
              <h3 className="font-semibold">{item.product?.name || "Unknown Product"}</h3>
              <p>₹ {item.product?.price || 0}</p>
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
