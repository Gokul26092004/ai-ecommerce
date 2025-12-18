import { getOrders } from "../utils/orderUtils";

const Orders = () => {
  const orders = getOrders();

  if (orders.length === 0) {
    return <h2 className="text-center mt-10">No orders placed yet</h2>;
  }

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold text-center mb-6">Order History</h1>

      <div className="max-w-3xl mx-auto space-y-4">
        {orders.map((order) => (
          <div key={order.id} className="bg-white p-4 rounded shadow">
            <p><b>Order ID:</b> {order.id}</p>
            <p><b>Date:</b> {order.date}</p>
            <p><b>Status:</b> {order.status}</p>
            <p><b>Payment:</b> {order.paymentMode}</p>
            <p><b>Total:</b> ₹ {order.total}</p>

            <div className="mt-2">
              <b>Items:</b>
              <ul className="list-disc ml-6">
                {order.items.map((item) => (
                  <li key={item.id}>
                    {item.name} × {item.quantity}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Orders;
