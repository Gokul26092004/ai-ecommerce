import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-gray-800 p-4 flex gap-4">
      <Link to="/" className="text-white">Home</Link>
      <Link to="/about" className="text-white">About</Link>
      
    </nav>
  );
};

export default Navbar;
