import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getCart } from "../utils/cartUtils";
import { placeOrder } from "../utils/orderUtils";

const Checkout = () => {
  const cart = getCart();
  const navigate = useNavigate();

  const [address, setAddress] = useState("");
  const [payment, setPayment] = useState("COD");

  const handleOrder = () => {
    if (!address) {
      alert("Please enter delivery address");
      return;
    }

    placeOrder(cart, address, payment);
    alert("Order placed successfully!");
    navigate("/orders");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-xl mx-auto bg-white p-6 rounded-xl shadow">
        <h2 className="text-2xl font-bold mb-4">Checkout</h2>

        <textarea
          placeholder="Delivery Address"
          className="w-full border p-2 rounded mb-4"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />

        <div className="mb-4">
          <h3 className="font-semibold mb-2">Payment Method</h3>
          <select
            value={payment}
            onChange={(e) => setPayment(e.target.value)}
            className="w-full border p-2 rounded"
          >
            <option value="COD">Cash on Delivery</option>
            <option value="UPI">UPI</option>
            <option value="CARD">Card</option>
          </select>
        </div>

        <button
          onClick={handleOrder}
          className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700"
        >
          Place Order
        </button>
      </div>
    </div>
  );
};

export default Checkout;
