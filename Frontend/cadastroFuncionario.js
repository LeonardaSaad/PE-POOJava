const form = document.querySelector("form");
const inputName = document.querySelector(".iName");

function cadastroFuncionario(obj) {
  fetch("http://localhost:8080/funcionarios", {
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(obj),
  })
    .then((res) => {
      console.log(res);
    })
    .catch((res) => {
      console.log(res);
    });
}

function cleanForm() {
  inputName.value = "";
}

function handleForm(event) {
  event.preventDefault();

  const dados = {
    "name": inputName.value
  };
  console.log(dados);

  cadastroFuncionario(dados);

  cleanForm();
}

form.addEventListener("submit", handleForm);
