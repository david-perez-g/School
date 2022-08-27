function resetFields() {
    let name = document.getElementById('input-name');
    let age = document.getElementById('input-age');
    let email = document.getElementById('input-email');

    name.value = 'Name'
    age.value = 'Age'
    email.value = 'Email'
}

function isCharAlpha(char) {
    let posInASCII = char.charCodeAt(0);

    if (isNaN(posInASCII)) {
        return false;
    }
    //  upper case letters in ascii table     lower case
    return (65 <= posInASCII && posInASCII <= 90) || (97 <= posInASCII && posInASCII <= 122);
}

function isUserInputValid(name, age, email) {
    if (name === '' || name === 'Name' || age === '' || age === 'Age'
        || email === '' || email === 'Email') {
        alert('Please complete every field.');
        resetFields();
        return false;
    }

    // checking name
    for (let i=0; i<name.length; i++) {
        if (! isCharAlpha(name[i])) {
            alert('Name must be alphabetical characters only')
            return false;
        }
    }

    age = Number(age)
    if (isNaN(age)) {
        alert('Please insert your age.');
        return false;
    }

    if (age < 18 || age > 50) {
        alert('Your age must be between 18 and 50 years old.');
        return false;
    }

    if (! (email.includes('@') && email.includes('.'))) {
        alert('Invalid Email');
        return false;
    }

    return true;
}

function getUserInput() {
    let name = document.getElementById('input-name')
    let age = document.getElementById('input-age')
    let email = document.getElementById('input-email')

    if (isUserInputValid(name.value, age.value, email.value)) {
        alert('Contacted successfully!');
        resetFields();
    }
}

const btnSubmit = document.getElementById('button-submit')
const btnReset = document.getElementById('button-reset')

btnSubmit.addEventListener('click', getUserInput)
btnReset.addEventListener('click', resetFields)
