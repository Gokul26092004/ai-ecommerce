import api from "../services/api";

export const getUserId = () => {
    const token = localStorage.getItem("token");
    if (!token) return null;
    try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.userId || null;
    } catch(e) { return null; }
};

export const getCart = async () => {
    const userId = getUserId();
    if (!userId) return [];
    try {
        const response = await api.get(`/cart/${userId}`);
        return response.data; // Expected to be Cart object, adjust if it's Cart with items array
    } catch(e) {
        return [];
    }
};

export const addToCart = async (product) => {
    const userId = getUserId();
    if (!userId) {
        alert("Please login to add to cart");
        return;
    }
    try {
        await api.post(`/cart/add?userId=${userId}&productId=${product.id}&qty=1`);
        alert("Product added to cart");
    } catch (e) {
        alert("Failed to add to cart");
    }
};

export const updateQuantity = async (itemId, qty) => {
    try {
        await api.put(`/cart/update/${itemId}?qty=${qty}`);
    } catch (e) {
        console.error("Failed to update qty");
    }
};

export const removeFromCart = async (itemId) => {
    try {
        await api.delete(`/cart/remove/${itemId}`);
    } catch (e) {
        console.error("Failed to remove item");
    }
};
