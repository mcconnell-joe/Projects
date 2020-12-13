'use strict';

//Joseph McConnell Guess my Number
//Necessary varables and functions
let score = 25;
let highScore = 0;

const ranRange = function () {
  return Math.trunc(Math.random() * 100) + 1;
};
let secretNumber = ranRange();
const displayMessage = function (message, iClass) {
  document.querySelector(iClass).textContent = message;
};

//Get the number when 'check' button is pressed
document.querySelector('.check').addEventListener('click', function () {
  const guess = Number(document.querySelector('.guess').value);
  console.log(guess);

  //When the guess is out of range or empty
  if (guess > 100 || guess < 1 || !guess) {
    displayMessage(
      'Please guess a number within the range, you silly goose!🦢',
      '.message'
    );

    //When Player wins
  } else if (guess === secretNumber) {
    displayMessage(
      '⭐Oh darn smarty pants, you guessed my number⭐!',
      '.message'
    );
    displayMessage(secretNumber, '.number');
    displayMessage('🌟You Win!🌟', '.head');
    //Refactor style
    document.querySelector('body').style.backgroundColor = 'orange';
    document.querySelector('.number').style.width = '30rem';
    document.querySelector('.number').style.backgroundColor = 'red';

    //HighScore
    if (score > highScore) {
      highScore = score;
      displayMessage(highScore, '.highscore');
    }

    //when guess is lower or higher than 'secretNumber'
  } else if (guess !== secretNumber) {
    if (score > 1) {
      document.querySelector('.message').textContent =
        guess < secretNumber
          ? '☝Guess again, my number is larger!☝'
          : '👇Guess again, my number is smaller!👇';
      score--;
      displayMessage(score, '.score');
    } else {
      displayMessage(
        '😭Too bad! I win, you could not guess my number!😭',
        '.message'
      );
      displayMessage(0, '.score');
    }
  }

  //Again button Functionality
  document.querySelector('.again').addEventListener('click', function () {
    displayMessage(score, '.score');
    score = 25;
    secretNumber = ranRange();
    document.querySelector('.guess').value = '';
    displayMessage('Start guessing...', '.message');
    displayMessage('?', '.number');
    displayMessage('Guess My Number!', '.head');
    //Re-style screen
    document.querySelector('body').style.backgroundColor =
      'rgba(12, 69, 107, 0.699)';
    document.querySelector('.number').style.width = '15rem';
    document.querySelector('.number').style.backgroundColor = 'black';
  });
});
