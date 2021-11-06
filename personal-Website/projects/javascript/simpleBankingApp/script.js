'use strict';

//* DOM Elements
const labelWelcome = document.querySelector('.welcome');
const labelDate = document.querySelector('.date');
const labelBalance = document.querySelector('.balance__value');
const labelSumIn = document.querySelector('.summary__value--in');
const labelSumOut = document.querySelector('.summary__value--out');
const labelSumInterest = document.querySelector('.summary__value--interest');
const labelTimer = document.querySelector('.timer');

const containerApp = document.querySelector('.app');
const containerMovements = document.querySelector('.movements');

const btnLogin = document.querySelector('.login__btn');
const btnTransfer = document.querySelector('.form__btn--transfer');
const btnLoan = document.querySelector('.form__btn--loan');
const btnClose = document.querySelector('.form__btn--close');
const btnSort = document.querySelector('.btn--sort');

const inputLoginUsername = document.querySelector('.login__input--user');
const inputLoginPin = document.querySelector('.login__input--pin');
const inputTransferTo = document.querySelector('.form__input--to');
const inputTransferAmount = document.querySelector('.form__input--amount');
const inputLoanAmount = document.querySelector('.form__input--loan-amount');
const inputCloseUsername = document.querySelector('.form__input--user');
const inputClosePin = document.querySelector('.form__input--pin');

////////////////////DATA//////////////////////////

const account1 = {
  owner: 'Joseph McConnell',
  movements: [200, 455.23, -306.5, 25000, -642.21, -133.9, 79.97, 1300],
  interestRate: 1.2, // %
  pin: 1111,

  movementsDates: [
    '2020-11-18T21:31:17.178Z',
    '2020-12-23T07:42:02.383Z',
    '2021-01-01T09:15:04.904Z',
    '2021-01-01T10:17:24.185Z',
    '2021-01-02T14:11:59.604Z',
    '2021-01-03T17:01:17.194Z',
    '2021-01-04T23:36:17.929Z',
    '2021-01-05T10:51:36.790Z',
  ],
  currency: 'USD',
  locale: 'en-US', // de-DE
};

const account2 = {
  owner: 'Jessica Davis',
  movements: [5000, 3400, -150, -790, -3210, -1000, 8500, -30],
  interestRate: 1.5,
  pin: 2222,

  movementsDates: [
    '2019-11-01T13:15:33.035Z',
    '2019-11-30T09:48:16.867Z',
    '2019-12-25T06:04:23.907Z',
    '2020-01-25T14:18:46.235Z',
    '2020-02-05T16:33:06.386Z',
    '2020-04-10T14:43:26.374Z',
    '2020-06-25T18:49:59.371Z',
    '2020-07-26T12:01:20.894Z',
  ],
  currency: 'EUR',
  locale: 'pt-PT',
};

const accounts = [account1, account2];
const movements = account1.movements;

//////////////! FUNCTIONS /////////////////

//* Date format Display functionality
const formatDate = function (date, locale) {
  const calcDaysPassed = (date1, date2) => Math.round(Math.abs(date2 - date1) / (1000 * 60 * 60 * 24));

  const daysPassed = calcDaysPassed(new Date(), date);
  console.log(daysPassed);

  if (daysPassed == 0) return 'Today';
  if (daysPassed == 1) return 'Yesterday';
  if (daysPassed <= 7) return `${daysPassed} days ago`

  return new Intl.DateTimeFormat(locale).format(date);
}

//* Format the currency to the local
const formatCurrency = function (mov, acct) {

  const formattedMov = new Intl.NumberFormat(acct.locale, {
    style: 'currency',
    currency: acct.currency
  }).format(mov);
  return formattedMov;
}

//*Logout function
const logOutUser = function () {
  containerApp.style.opacity = 0;
  labelWelcome.textContent = 'Login to get Started'
}


const displayMovements = function (acct, sort = false) {
  containerMovements.innerHTML = '';

  const movs = sort ? acct.movements.slice().sort((a, b) => a - b) : acct.movements;


  movs.forEach(function (mov, i) {
    const type = mov > 0 ? 'deposit' : 'withdrawal';

    const date = new Date(acct.movementsDates[i]);
    const displayDate = formatDate(date, acct.locale);

    const html = `
      <div class="movements__row">
        <div class="movements__type movements__type--${type}">${
      i + 1
    } ${type}</div>
        <div class="movements__date">${displayDate}</div>
        <div class="movements__value">${formatCurrency(mov, acct)}</div>
      </div>
    `;

    containerMovements.insertAdjacentHTML('afterbegin', html);
  });
};

displayMovements(account1);

const calcDisplayBalance = function (acct) {
  acct.balance = acct.movements.reduce((acc, cur) => acc + cur);
  labelBalance.textContent = `${formatCurrency(acct.balance, acct)}`;
}

calcDisplayBalance(account1);

const calcDisplaySummary = function (acct) {
  const deposits = acct.movements
    .filter(val => val > 0)
    .reduce((acc, val) => acc + val);
  const withdrawalsArray = acct.movements
    .filter(val => val < 0);
  let withdrawals = (withdrawalsArray.length > 0 ? withdrawalsArray.reduce((acc, val) => acc + val) : 0);
  const interest = acct.movements
    .filter(val => val > 0)
    .map((val) => val * (acct.interestRate / 100))
    .filter((val) => val >= 1)
    .reduce((acc, val) => acc + val);

  labelSumIn.textContent = `${formatCurrency(deposits, acct)}`;
  labelSumOut.textContent = `${formatCurrency(Math.abs(withdrawals), acct)}`;
  labelSumInterest.textContent = `${formatCurrency(interest, acct)}`;
}

calcDisplaySummary(account1);

const createUserNames = function (accs) {
  accs.forEach(function (acc) {
    acc.username = acc.owner
      .toLowerCase()
      .split(' ')
      .map(name => name[0])
      .join('');
  });
};
createUserNames(accounts)

//*Display Login UI values
const updateUI = function (acct) {
  displayMovements(acct);
  calcDisplaySummary(acct);
  calcDisplayBalance(acct);
}

//*Start Logout Timer
const startLogoutTimer = function () {
  const tick = function () {
    const min = String(Math.trunc(time / 60)).padStart(2, 0);
    const sec = String(time % 60).padStart(2, 0);
    //* In each call print the remaining time
    labelTimer.textContent = `${min}:${sec}`;
    //*When time is at zero, stop timer and logout user
    if (time == 0) {
      clearInterval(timer);
      logOutUser();
    }
    //*Decrement time
    time--;
  }

  //*set timer for 5 min
  let time = 300;

  //*Call the timer every second
  tick();
  const timer = setInterval(tick, 1000)
  return timer;
}

//*Event Handler 
let currentAccount, timer;

btnLogin.addEventListener('click', function (e) {
  //*Prevent form from submitting
  e.preventDefault();

  currentAccount = accounts.find(acct => acct.username === inputLoginUsername.value);
  console.log(currentAccount);
  if (currentAccount?.pin === Number(inputLoginPin.value)) {
    //*Display UI and welcome message
    labelWelcome.textContent = `Welcome Back, ${currentAccount.owner.split(' ')[0]}`;
    containerApp.style.opacity = 100;
    //*Start logout timer
    if(timer) clearInterval(timer);
    timer = startLogoutTimer()
    //*Prepare the UI
    updateUI(currentAccount);
  } else {
    swal('Invalid', "Please check login deatails and try again", 'error');
    logOutUser();
  }
  //*Clear inputs
  inputLoginUsername.value = '';
  inputLoginPin.value = '';
  inputLoginPin.blur();
});

//*Implement transfer functionality
btnTransfer.addEventListener('click', function (e) {
  e.preventDefault();
  const amount = Number(inputTransferAmount.value);
  const recieverAcc = accounts.find(acc => acc.username === inputTransferTo.value)
  //*Ensure the transfer value is a valid value
  if (amount > 0 &&
    recieverAcc &&
    currentAccount.balance >= amount &&
    recieverAcc?.username !== currentAccount.username) {
    currentAccount.movements.push(-amount);
    recieverAcc.movements.push(amount);
    updateUI(currentAccount);
  } else {
    swal('Error', `Invalid Transfer Values.`, 'error');
  }
  //*Clear the inputs
  inputTransferAmount.value = '';
  inputTransferTo.value = '';
  inputTransferAmount.blur();
  //*re-Start logout timer
  if(timer) clearInterval(timer);
  timer = startLogoutTimer();
});

//*Add close account functionality
btnClose.addEventListener('click', function (e) {
  e.preventDefault();

  const userIndex = accounts.findIndex(acct => acct.username === currentAccount.username);
  const userPin = Number(inputClosePin.value);
  if (currentAccount.username === inputCloseUsername.value && currentAccount.pin === userPin) {
    setTimeout(() => {
      accounts.splice(userIndex, 1);
      logOutUser();
      swal('Account closed', 'Your account was successfully closed', 'success');
    }, 2000)

  } else {
    swal('Error', 'Invalid account closure details.', 'error');
  }
  inputClosePin.value = '';
  inputCloseUsername.value = '';
});

//*Implement loan functionality
btnLoan.addEventListener('click', function (e) {
  e.preventDefault();

  const loanAmount = Math.floor(inputLoanAmount.value);
  console.log(loanAmount);
  //*if there is a deposit = 10% more than loan request it's approved
  if (currentAccount.movements.filter(val => val > 0).some(mov => mov > loanAmount * .1)) {
    setTimeout(() => {
      currentAccount.movements.push(loanAmount);
      currentAccount.movementsDates.push(new Date());
      updateUI(currentAccount);
      swal('Approved', `Congratulations your loan of ${formatCurrency(loanAmount, currentAccount)} was approved`, 'success')
    }, 4000)
  }else{
    swal('Invalid', 'Loan value too high.', 'error');
  }
  inputLoanAmount.value = '';
  inputLoanAmount.blur();
  //*re-Start logout timer
  if(timer) clearInterval(timer);
  timer = startLogoutTimer();
});

//*State variable
let sortedMov = false;
//*Add functionality to sort the movements
btnSort.addEventListener('click', function (e) {
  e.preventDefault();
  displayMovements(currentAccount, !sortedMov);
  sortedMov = !sortedMov;
  //*re-Start logout timer
  if(timer) clearInterval(timer);
  timer = startLogoutTimer();
})