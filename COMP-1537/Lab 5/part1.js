const Operation = {
  ADDITION: "addition",
  SUBSTRACTION: "substraction",
  MULTIPLICATION: "multiplication",
  DIVISION: "division",
};

function promptOperation() {
  let operation = prompt(
    "Please insert an operation (addition / substraction / multiplication / division)",
    "addition"
  );

  while (true) {
    switch (operation) {
      case "addition":
        return Operation.ADDITION;
      case "substraction":
        return Operation.SUBSTRACTION;
      case "multiplication":
        return Operation.MULTIPLICATION;
      case "division":
        return Operation.DIVISION;
      default: {
        alert("Please insert a valid operation");
        operation = prompt(
          "Please insert an operation (addition / substraction / multiplication / division)",
          "addition"
        );
      }
    }
  }
}

function promptNumber(message, exclude = null) {
  let number = parseInt(prompt(message));

  while (isNaN(number) || (exclude && exclude.includes(number))) {
    alert("Invalid number");
    number = parseInt(prompt(message));
  }

  return number;
}

const doOperation = {};
doOperation[Operation.ADDITION] = (x, y) => x + y;
doOperation[Operation.SUBSTRACTION] = (x, y) => x - y;
doOperation[Operation.MULTIPLICATION] = (x, y) => x * y;
doOperation[Operation.DIVISION] = (x, y) => x / y;

function showResult(operation, x, y, result) {
  const resultTag = document.getElementById("result");
  resultTag.innerText = `The result of ${operation} of ${x} and ${y} is ${result}`;
}

function main() {
  const operation = promptOperation();
  let exclude = null;

  if (operation === Operation.DIVISION) {
    exclude = [0];
  }

  const x = promptNumber("Please enter the first number");
  const y = promptNumber("Please enter the second number", exclude);

  const result = doOperation[operation](x, y);

  showResult(operation, x, y, result);
}

main();
