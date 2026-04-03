import "./Auth.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login, buscarUsuarioPorEmail } from "../services/api";

function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const token = await login(email, senha);

      localStorage.setItem("token", token);

      const usuario = await buscarUsuarioPorEmail(email);
      localStorage.setItem("usuario", JSON.stringify(usuario));

      navigate("/apostas");
    } catch (error) {
      alert("Email ou senha inválidos");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">

        <div className="logo-circle">
          <h1>LOGIN</h1>
        </div>

        <input
          placeholder="Email"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Senha"
          onChange={(e) => setSenha(e.target.value)}
        />

        <button className="btn-primary" onClick={handleLogin}>
          ENTRAR
        </button>

        <p onClick={() => navigate("/cadastro")}>
          Não tem conta? Cadastre-se
        </p>

      </div>
    </div>
  );
}

export default Login;