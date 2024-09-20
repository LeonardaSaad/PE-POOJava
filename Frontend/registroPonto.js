const form = document.querySelector(".form");
const iId = document.querySelector(".iId");

function toClockInRegister(obj) {
  fetch("http://localhost:8080/registro_ponto", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(obj),
  })
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.error(err);
    });
}

function handleForm(event) {
  event.preventDefault();

  const dados = {
      funcionario_id: iId.value,
      tipo: document.querySelector("#type").value
  };
  console.log(dados);

  toClockInRegister(dados);
}

form.addEventListener("submit", handleForm);
