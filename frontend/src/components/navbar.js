import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-gray-800 p-4 flex gap-4">
      <Link to="/" className="text-white">Home</Link>
      <Link to="/about" className="text-white">About</Link>
      <Link to="/login" className="text-white">Login</Link>
      <Link to="/register" className="text-white">Register</Link>
      <Link to="/products" className="text-white">Products</Link>
      <Link to="/cart" className="absolute top-4 right-6 bg-black text-white px-4 py-2 rounded">Cart</Link>
      
<Link to="/orders" className="text-white">Orders</Link>

    </nav>
  );
};

export default Navbar;
