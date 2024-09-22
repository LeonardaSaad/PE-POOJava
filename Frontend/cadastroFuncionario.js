const form = document.querySelector("form");
const inputName = document.querySelector(".iName");

function cadastroFuncionario(obj) {
	fetch("http://localhost:8080/funcionarios", {
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json",
		},
		method: "POST",
		body: JSON.stringify(obj),
	})
		.then((res) => {
			if (!res.ok) throw new Error("deu erro mano!");
			return res.json();
		})
		.then((data) => {
			console.log(data);
		})
		.catch((err) => {
			console.error(err);
		});
}

function cleanForm() {
	inputName.value = "";
}

function handleForm(event) {
	event.preventDefault();

	const dados = {
		name: inputName.value,
	};
	console.log(dados);

	cadastroFuncionario(dados);

	cleanForm();
}

form.addEventListener("submit", handleForm);
