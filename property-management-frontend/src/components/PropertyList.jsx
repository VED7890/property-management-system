import {
  useEffect,
  useState,
  useContext,
  forwardRef,
  useImperativeHandle,
} from "react";
import { motion } from "framer-motion";
import api from "../api/api";
import { AuthContext } from "../context/AuthContext";
import EditPropertyForm from "./EditPropertyForm";
import { toast } from "react-toastify";

const cardVariants = {
  hidden: { opacity: 0, y: 30 },
  visible: { opacity: 1, y: 0 },
};

const PropertyList = forwardRef((props, ref) => {
  const { user } = useContext(AuthContext);

  const [properties, setProperties] = useState([]);
  const [editing, setEditing] = useState(null);
  const [search, setSearch] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");

  const loadProperties = async () => {
    try {
      const res = await api.get(`/properties/users/${user.id}`);
      setProperties(res.data);
    } catch {
      toast.error("Failed to load properties");
    }
  };

  useEffect(() => {
    if (user?.id) loadProperties();
  }, [user]);

  // 🔥 EXPOSE RELOAD METHOD
  useImperativeHandle(ref, () => ({
    reload: loadProperties,
  }));

  const deleteProperty = async (id) => {
    if (!window.confirm("Delete this property?")) return;

    try {
      await api.delete(`/properties/${id}`);
      setProperties((prev) => prev.filter((p) => p.id !== id));
      toast.success("Property deleted");
    } catch {
      toast.error("Delete failed");
    }
  };

  const filteredProperties = properties.filter((p) => {
    const text = `${p.title} ${p.address} ${p.description}`.toLowerCase();
    return (
      text.includes(search.toLowerCase()) &&
      (!minPrice || p.price >= Number(minPrice)) &&
      (!maxPrice || p.price <= Number(maxPrice))
    );
  });

  return (
    <div>
      <h3>Your Properties</h3>

      <div className="filter-bar">
        <input
          placeholder="Search..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <input
          type="number"
          placeholder="Min ₹"
          value={minPrice}
          onChange={(e) => setMinPrice(e.target.value)}
        />
        <input
          type="number"
          placeholder="Max ₹"
          value={maxPrice}
          onChange={(e) => setMaxPrice(e.target.value)}
        />
      </div>

      <div className="property-grid">
        {filteredProperties.map((p, index) => (
          <motion.div
            key={p.id}
            className="property-card"
            variants={cardVariants}
            initial="hidden"
            animate="visible"
            transition={{ delay: index * 0.05 }}
            whileHover={{ scale: 1.03, y: -5 }}
          >
            <img
              src={p.imageUrl || "/placeholder.png"}
              alt={p.title}
              className="property-img"
            />

            {editing === p.id ? (
              <EditPropertyForm
                property={p}
                onClose={() => setEditing(null)}
              />
            ) : (
              <>
                <h4>{p.title}</h4>
                <p><strong>Address:</strong> {p.address}</p>
                <p><strong>Price:</strong> ₹{p.price}</p>
                <p><strong>Description:</strong> {p.description}</p>

                <div className="card-actions">
                  <button onClick={() => setEditing(p.id)}>Edit</button>
                  <button
                    className="delete"
                    onClick={() => deleteProperty(p.id)}
                  >
                    Delete
                  </button>
                </div>
              </>
            )}
          </motion.div>
        ))}
      </div>
    </div>
  );
});

export default PropertyList;
