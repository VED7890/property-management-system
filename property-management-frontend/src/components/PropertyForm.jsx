import { useState, useContext } from "react";
import api from "../api/api";
import { AuthContext } from "../context/AuthContext";
import { toast } from "react-toastify";

const PropertyForm = ({ onSuccess }) => {
  const { user } = useContext(AuthContext);

  const [form, setForm] = useState({
    title: "",
    address: "",
    price: "",
    description: "",
  });

  const [image, setImage] = useState(null);

  const submit = async (e) => {
    e.preventDefault();

    if (!user?.id) {
      toast.error("User not logged in");
      return;
    }

    const data = new FormData();

    // ✅ INCLUDE userId
    data.append(
      "property",
      new Blob(
        [
          JSON.stringify({
            ...form,
            userId: user.id,
          }),
        ],
        { type: "application/json" }
      )
    );

    if (image) {
      data.append("image", image);
    }

    try {
      await api.post("/properties", data, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      toast.success("Property added successfully");
      onSuccess();
    } catch (err) {
      toast.error(
        err.response?.data?.[0]?.message || "Failed to add property"
      );
    }
  };

  return (
    <form onSubmit={submit}>
      <input
        placeholder="Title"
        value={form.title}
        onChange={(e) => setForm({ ...form, title: e.target.value })}
        required
      />
      <input
        placeholder="Address"
        value={form.address}
        onChange={(e) => setForm({ ...form, address: e.target.value })}
        required
      />
      <input
        type="number"
        placeholder="Price"
        value={form.price}
        onChange={(e) => setForm({ ...form, price: e.target.value })}
        required
      />
      <textarea
        placeholder="Description"
        value={form.description}
        onChange={(e) =>
          setForm({ ...form, description: e.target.value })
        }
        required
      />
      <input
        type="file"
        accept="image/*"
        onChange={(e) => setImage(e.target.files[0])}
      />

      <button>Add Property</button>
    </form>
  );
};

export default PropertyForm;
