function promptNumberOfNumbers() {
  let n = parseInt(prompt("Enter the count of numbers", 1));
  while (isNaN(n) || n < 1) {
    alert("Please enter a positive number.");
    n = parseInt(prompt("Enter the count of numbers", 1));
  }

  return n;
}

function promptNumber() {
  let number = parseInt(prompt("Enter a number"));

  while (isNaN(number)) {
    alert("Please enter a number");
    number = parseInt(prompt("Enter a number"));
  }

  return number;
}

function showResult(result) {
  let pTag = document.getElementById("result");
  pTag.innerText = `The count of positive numbers is ${result}`;
}

function main() {
  let numberOfNumbers = promptNumberOfNumbers();
  let countOfPositives = 0;
  for (let i = 0; i < numberOfNumbers; i++) {
    let usrNumber = promptNumber();
    if (usrNumber > 0) {
      countOfPositives++;
    }
  }
  showResult(countOfPositives);
}

main();
