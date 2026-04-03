import "./Auth.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { criarUsuario } from "../services/api";

function Cadastro() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const navigate = useNavigate();

  const handleCadastro = async (e) => {
    e.preventDefault();

    try {
      await criarUsuario({ nome, email, senha });
      alert("Cadastro realizado com sucesso!");
      navigate("/");
    } catch (error) {
      alert("Erro ao cadastrar");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">

        <div className="logo-circle">
          <h1>CADASTRO</h1>
        </div>

        <input
          placeholder="Nome"
          onChange={(e) => setNome(e.target.value)}
        />

        <input
          placeholder="Email"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Senha"
          onChange={(e) => setSenha(e.target.value)}
        />

        <button className="btn-primary" onClick={handleCadastro}>
          CADASTRAR
        </button>

        <p onClick={() => navigate("/")}>
          Já tem conta? Entrar
        </p>

      </div>
    </div>
  );
}

export default Cadastro;