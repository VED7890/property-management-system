import { useState } from "react";
import api from "../api/api";
import { toast } from "react-toastify";

const EditPropertyForm = ({ property, onClose }) => {
  const [form, setForm] = useState({
    title: property.title || "",
    address: property.address || "",
    price: property.price || "",
    description: property.description || "",
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const submit = async (e) => {
    e.preventDefault();

    try {
      await api.put(`/properties/${property.id}`, form);
      toast.success("Property updated successfully");
      onClose();
      window.location.reload(); // simple refresh
    } catch (err) {
      toast.error("Failed to update property");
    }
  };

  return (
    <form onSubmit={submit} className="edit-form">
      <input
        name="title"
        placeholder="Title"
        value={form.title}
        onChange={handleChange}
        required
      />

      <input
        name="address"
        placeholder="Address"
        value={form.address}
        onChange={handleChange}
        required
      />

      <input
        type="number"
        name="price"
        placeholder="Price"
        value={form.price}
        onChange={handleChange}
        required
      />

      {/* ✅ DESCRIPTION FIX */}
      <textarea
        name="description"
        placeholder="Description"
        value={form.description}
        onChange={handleChange}
        rows={4}
        required
      />

      <div className="card-actions">
        <button type="submit">Update</button>
        <button type="button" onClick={onClose}>
          Cancel
        </button>
      </div>
    </form>
  );
};

export default EditPropertyForm;
