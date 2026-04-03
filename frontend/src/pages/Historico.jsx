import { useEffect, useState } from "react";

function Historico() {
  const [apostas, setApostas] = useState([]);

  useEffect(() => {
    const usuario = JSON.parse(localStorage.getItem("usuario"));

    fetch(`http://localhost:8080/apostas/usuario/${usuario.id}`)
      .then(res => res.json())
      .then(setApostas);
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Histórico</h1>

      {apostas.map((a) => (
        <div key={a.id} style={{
          background: a.ganhou ? "#2e7d32" : "#b71c1c",
          color: "white",
          padding: "10px",
          marginBottom: "10px",
          borderRadius: "10px"
        }}>
          <p>Animal: {a.animal.nome}</p>
          <p>Valor: R$ {a.valor}</p>
          <p>{a.ganhou ? "✅ Ganhou" : "❌ Perdeu"}</p>
        </div>
      ))}
    </div>
  );
}

export default Historico;