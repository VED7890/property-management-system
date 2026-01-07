import { useState, useContext } from "react";
import api from "../api/api";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [form, setForm] = useState({ ownerEmail: "", password: "" });
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/login", form);
      login(res.data);
      navigate("/dashboard");
    } catch (err) {
      alert(err.response.data[0].message);
    }
  };

  return (
    <form onSubmit={submit} className="page">
      <h2>Login</h2>
      <input placeholder="Email" onChange={e => setForm({...form, ownerEmail:e.target.value})} />
      <input type="password" placeholder="Password" onChange={e => setForm({...form, password:e.target.value})} />
      <button>Login</button>
    </form>
  );
};

export default Login;
