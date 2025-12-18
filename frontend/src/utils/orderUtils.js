export const getOrders = () => {
  const orders = localStorage.getItem("orders");
  return orders ? JSON.parse(orders) : [];
};

export const placeOrder = (cart, address, paymentMode) => {
  const orders = getOrders();

  const newOrder = {
    id: Date.now(),
    items: cart,
    total: cart.reduce(
      (sum, item) => sum + item.price * item.quantity,
      0
    ),
    address,
    paymentMode,
    status: "PLACED",
    date: new Date().toLocaleString(),
  };

  orders.push(newOrder);
  localStorage.setItem("orders", JSON.stringify(orders));

  // clear cart
  localStorage.removeItem("cart");

  return newOrder;
};
