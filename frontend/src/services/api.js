const API_URL = "http://localhost:8080";

export const login = async (email, senha) => {
  const response = await fetch(`${API_URL}/usuarios/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, senha })
  });

  if (!response.ok) {
    throw new Error("Erro ao fazer login");
  }

  return response.text(); // JWT
};

export const listarApostas = async () => {
  const token = localStorage.getItem("token");

  const response = await fetch("http://localhost:8080/apostas/usuario/2", {
    method: "GET",
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });

  if (!response.ok) {
    throw new Error("Erro ao buscar apostas");
  }

  return response.json();
};

export const buscarApostas = async () => {
  const token = localStorage.getItem("token");

  const response = await fetch("http://localhost:8080/apostas", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return response.json();
};

export async function criarAposta(valor, animalId) {
  const usuario = JSON.parse(localStorage.getItem("usuario"));

  const response = await fetch("http://localhost:8080/apostas", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      valor: valor,
      usuario: { id: usuario.id },
      animal: { id: Number(animalId) }
    }),
  });

  if (!response.ok) {
    throw new Error("Erro ao apostar");
  }

  return response.json();
}

export const buscarUsuario = async () => {
  const token = localStorage.getItem("token");

  const response = await fetch("http://localhost:8080/usuarios/me", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return response.json();
};

export const buscarAnimais = async () => {
  const response = await fetch("http://localhost:8080/animais");
  return response.json();
};

export async function sortearResultado(id) {
  const token = localStorage.getItem("token");

  const response = await fetch(`http://localhost:8080/apostas/resultado/${id}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  if (!response.ok) {
    throw new Error("Erro ao sortear resultado");
  }

  return response.text();
}

export async function criarUsuario(usuario) {
  const response = await fetch("http://localhost:8080/usuarios", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(usuario)
  });

  return response.json();
}

export async function buscarUsuarioPorEmail(email) {
  const response = await fetch(`http://localhost:8080/usuarios/email/${email}`);
  return response.json();
}