const fs = require("fs");

let codigoPagamentoCounter = 0;
let cpfCounter = 0;

const generateRandomData = () => {
  return {
    codigoPagamento: (++codigoPagamentoCounter).toString().padStart(12, "0"),
    exercicioFinanceiro: "01012022",
    anoBase: "2022",
    numeroParcela: "01",
    valorPagamento: (Math.random() * 1312).toFixed(2),
    mesesTrabalhados: Math.floor(Math.random() * 12) + 1,
    dataInicialPagamento: "05022024",
    dataFinalPagamento: "31122024",
    numeroSentenca: "00000000000000000000",
    banco: {
      banco:
        Math.random() < 0.33 ? "0000" : Math.random() < 0.66 ? "5555" : "9999",
      agencia: Math.floor(Math.random() * 100000),
      digitoVerificador: Math.floor(Math.random() * 10),
      tipoConta:
        Math.random() < 0.33 ? "CC" : Math.random() < 0.66 ? "PP" : "CD",
      conta: Math.floor(Math.random() * 1000000000000000),
      indicadorPagamento: Math.floor(Math.random() * 3) + 1,
    },
    trabalhador: {
      cpf: (++cpfCounter).toString().padStart(11, "0"),
      nome: [
        "Maria da Silva",
        "Joana dos Santos",
        "Josefa Pereira",
        "Roberta Barbosa",
        "Alan Barbosa",
        "Cristiano Ronaldo",
        "Carlos Fabrício",
        "Pedro Monteiro",
        "Victor Czarnobay",
      ][Math.floor(Math.random() * 9)],
      nomeMae: [
        "Maria da Silva",
        "Joana dos Santos",
        "Josefa Pereira",
        "Roberta Barbosa",
      ][Math.floor(Math.random() * 4)],
      nascimento: "01012014",
      pisPasep: Math.floor(Math.random() * 100000000000),
    },
  };
};

const generateLargeJson = (numItems) => {
  const data = [];
  for (let i = 0; i < numItems; i++) {
    data.push(generateRandomData());
  }
  return data;
};

// Inicia o cronômetro
console.time("Tempo de execução");

const jsonData = generateLargeJson(1000); // Altere o número de itens conforme necessário

const writeStream = fs.createWriteStream("2M.json", { encoding: "utf8" });
writeStream.write("[\n");

jsonData.forEach((item, index) => {
  const comma = index === jsonData.length - 1 ? "" : ",";
  const jsonString = JSON.stringify(item, null, 2);
  writeStream.write(`  ${jsonString}${comma}\n`);
});

writeStream.write("]\n");
writeStream.end();

writeStream.on("finish", () => {
  // Encerra o cronômetro e exibe o tempo de execução
  console.timeEnd("Tempo de execução");
  console.log("JSON gerado com sucesso.");
});

writeStream.on("error", (err) => {
  console.error("Erro ao escrever o arquivo JSON:", err);
});
