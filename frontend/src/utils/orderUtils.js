import api from "../services/api";

export const getOrders = async () => {
  try {
    const response = await api.get("/orders/my-orders");
    return response.data;
  } catch (error) {
    console.error("Failed to fetch orders", error);
    return [];
  }
};

export const placeOrder = async (cartItems, address, paymentMode) => {
  try {
    const items = cartItems.map(item => ({
      productId: item.product?.id || item.productId,
      quantity: item.quantity
    }));

    // The backend OrderController actually only takes {items: ...} for PlaceOrderDto
    // It currently doesn't accept address and paymentMode in DTO, but we pass them along if needed later.
    const response = await api.post("/orders/place", { items });
    
    // Clear local token cart if we were using it, but cart is server-side now.
    // One could call a clear-cart API here if it existed.
    
    return response.data;
  } catch (error) {
    console.error("Failed to place order", error);
    throw error;
  }
};
