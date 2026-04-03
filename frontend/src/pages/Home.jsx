import "./Home.css";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <div className="home-card">

        <div className="logo-circle">
          <h1>JOGO<br/>DO<br/>BICHO</h1>
        </div>

        <button 
          className="btn-entrar"
          onClick={() => navigate("/")}
        >
          ENTRAR
        </button>

        <button 
          className="btn-cadastro"
          onClick={() => navigate("/cadastro")}
        >
          CADASTRE-SE
        </button>

      </div>
    </div>
  );
}

export default Home;