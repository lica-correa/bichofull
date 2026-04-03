import "./Apostas.css";
import { useEffect, useState } from "react";
import {
  buscarUsuarioPorEmail,
  criarAposta,
  buscarAnimais,
  sortearResultado
} from "../services/api";

function Apostas() {
  const [valor, setValor] = useState("");
  const [usuario, setUsuario] = useState(null);
  const [animais, setAnimais] = useState([]);
  const [animalSelecionado, setAnimalSelecionado] = useState(null);
  const [modalAberto, setModalAberto] = useState(false);
  const [mensagemResultado, setMensagemResultado] = useState("");

  useEffect(() => {
    const usuarioSalvo = JSON.parse(localStorage.getItem("usuario"));
    setUsuario(usuarioSalvo);

    buscarAnimais().then((data) => {
      const unicos = [...new Map(data.map((a) => [a.id, a])).values()];
      setAnimais(unicos);
    });
  }, []);

  const handleApostar = async () => {
    if (!valor || !animalSelecionado) {
      alert("Preencha o valor e selecione um animal");
      return;
    }

    try {
      const aposta = await criarAposta(Number(valor), animalSelecionado.id);

      const resultado = await sortearResultado(aposta.id);

      // 🔥 abre modal ao invés de alert
      setMensagemResultado(resultado);
      setModalAberto(true);

      setValor("");
      setAnimalSelecionado(null);

      const usuarioAtualizado = await buscarUsuarioPorEmail(usuario.email);
      localStorage.setItem("usuario", JSON.stringify(usuarioAtualizado));
      setUsuario(usuarioAtualizado);

    } catch (error) {
      alert("Erro ao apostar");
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");
    window.location.href = "/";
  };

  const gerarDezenas = (grupo) => {
    const inicio = (grupo - 1) * 4 + 1;
    const dezenas = [];
    for (let i = 0; i < 4; i++) {
      dezenas.push(String(inicio + i).padStart(2, "0"));
    }
    return dezenas.join(" - ");
  };

  return (
    <div className="apostas-container">

      <div className="topo">
        <h2>Saldo: R$ {usuario?.saldo}</h2>

        <div>
          <button onClick={() => window.location.href = "/historico"}>
            Histórico
          </button>
          <button onClick={logout}>Sair</button>
        </div>
      </div>

      <h1>Escolha seu Animal</h1>

      {/* 🔥 GRID DE ANIMAIS */}
      <div className="grid-animais">
        {animais.map((a) => (
          <div
            key={a.id}
            className={`card-animal ${
              animalSelecionado?.id === a.id ? "selecionado" : ""
            }`}
            onClick={() => setAnimalSelecionado(a)}
          >
            <h3>{a.nome}</h3>
            <p>Grupo: {a.numero}</p>
            <p>{gerarDezenas(a.numero)}</p>
          </div>
        ))}
      </div>

      {/* 🔥 APOSTA */}
      <div className="area-aposta">
        <input
          type="number"
          placeholder="Valor da aposta"
          value={valor}
          onChange={(e) => setValor(e.target.value)}
        />

        <button onClick={handleApostar}>
          Apostar 🎯
        </button>
      </div>

      {modalAberto && (
        <div className="modal-overlay">
          <div className="modal">

            <h2>Resultado 🎯</h2>

            <pre>{mensagemResultado}</pre>

            <button onClick={() => setModalAberto(false)}>
              Fechar
            </button>

          </div>
        </div>
      )}

    </div>
  );
}

export default Apostas;