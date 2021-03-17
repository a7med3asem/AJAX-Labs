function isEmailValid(email) {
    var regex = /^[^\W_]+@+[a-z\d]+(\.[a-z]{2,}){1,2}/i;
    var result = regex.test(email);
    if (result) {
        return true;
    } else {
        return false;
    }
}

// //unit test
//     console.log(isEmailValid("Ahmed@gmail.com"));
//     console.log(isEmailValid("*/asdxzc*-@zxcasd.a"));

function isMobileValid(mobile) {
    var regex = /^\+201[0125]\d{8}$/;
    var result = regex.test(mobile);
    if (result) {
        return true;
    } else {
        return false;
    }
}

// //unit test
//     console.log(isMobileValid("01100200250"));
//     console.log(isMobileValid("564564678677"));
//     console.log(isMobileValid("0110020025"));

function isCreditCard(creditCardNum) {
    var regex = /\b(\d{4}-){3}\d{4}\b/;
    var result = regex.test(creditCardNum);
    if (result) {
        return true;
    } else {
        return false;
    }
}

// //unit test
//     console.log(isCreditCard("5555-5555-5555-5555"));
//     console.log(isCreditCard("5555-555-5555-55555"));
//     console.log(isCreditCard("5asd5-ds555-5x5-5-5"));

function parseCreditCard(creditCardNum) {
    if (isCreditCard(creditCardNum)) {
        var regex = /\d{4}/g;
        return creditCardNum.match(regex);
    } else {
        return false;
    }
}

// //unit test
//     console.log(parseCreditCard("5555-5555-5555-5555"));
//     console.log(parseCreditCard("5555-555-5555-55555"));
//     console.log(parseCreditCard("5asd5-ds555-5x5-5-5"));

function isDateValid(dateInput) {
    if (/\d{4}(-\d{2}){2}/.test(dateInput)) {
        return true;
    } else {
        return false;
    }
}

function isPasswordValid(password) {
    if (/.+/.test(password)) {
        return true;
    } else {
        return false;
    }
}

function isUserNameValid(userName) {
    if (/^\w+$/.test(userName)) {
        return true;
    } else {
        return false;
    }
}

function isNameValid(name) {
    if (/\w+/.test(name)) {
        return true;
    } else {
        return false;
    }
}

function isEmpty(text) {
    if (!/[^\s\n]+/.test(text)) {
        return true;
    } else {
        return false;
    }
}
