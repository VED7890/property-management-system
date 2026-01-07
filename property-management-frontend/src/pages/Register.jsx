import { useState } from "react";
import api from "../api/api";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [form, setForm] = useState({});
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/register", form);
      alert("Registered Successfully");
      navigate("/login");
    } catch (err) {
      alert(err.response.data[0].message);
    }
  };

  return (
    <form onSubmit={submit} className="page">
      <h2>Register</h2>
      <input placeholder="Name" onChange={e => setForm({...form, ownerName:e.target.value})} />
      <input placeholder="Email" onChange={e => setForm({...form, ownerEmail:e.target.value})} />
      <input placeholder="Phone" onChange={e => setForm({...form, phone:e.target.value})} />
      <input type="password" placeholder="Password" onChange={e => setForm({...form, password:e.target.value})} />
      <button>Register</button>
    </form>
  );
};

export default Register;
