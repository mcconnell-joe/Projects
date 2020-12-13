'use strict';
//Grab all elements needed to change or add functionality
const player0El = document.querySelector('.player--0');
const player1El = document.querySelector('.player--1');
const scoreP1El = document.querySelector('#score--0');
const scoreP2El = document.querySelector('#score--1');
const currScP0 = document.getElementById('current--0');
const currScP1 = document.getElementById('current--1');

//Dice && btn manipulation
const diceEl = document.querySelector('.dice');
const rollBtn = document.querySelector('.btn--roll');
const holdBtn = document.querySelector('.btn--hold');
const newBtn = document.querySelector('.btn--new');

const setCurrentSc = function (str, str2) {
  document.getElementById(`${str}--${activePlayer}`).textContent = `${str2}`;
};

const switchPlayer = function () {
  activePlayer = activePlayer === 0 ? 1 : 0;
  player0El.classList.toggle('player--active');
  player1El.classList.toggle('player--active');
};

let score, currScore, activePlayer, playing;

const init = function () {
  score = [0, 0];
  currScore = 0;
  activePlayer = 0;
  playing = true;

  scoreP1El.textContent = 0;
  scoreP2El.textContent = 0;
  currScP0.textContent = 0;
  currScP1.textContent = 0;

  score[0] = 0;
  score[1] = 0;

  diceEl.classList.add('hidden');
  player0El.classList.remove('player--winner');
  player1El.classList.remove('player--winner');
  player0El.classList.add('player--active');
  player1El.classList.remove('player--active');
};
const displayMessage = function (str) {
  document.getElementById(`name--${activePlayer}`).textContent = str;
};

init();

rollBtn.addEventListener('click', function () {
  if (playing) {
    //1. Generate a random dice roll
    const dice = Math.trunc(Math.random() * 6) + 1;
    console.log(dice);
    //2. Display dice
    diceEl.classList.remove('hidden');
    diceEl.src = `dice-${dice}.png`;
    //3. Check for rolled 1: if true, switch to next player
    if (dice !== 1) {
      //add dice to the current score
      currScore += dice;
      setCurrentSc('current', currScore);
    } else {
      currScore = 0;
      setCurrentSc('current', currScore);
      //Switch to the next player
      switchPlayer();
    }
  }
});

holdBtn.addEventListener('click', function () {
  if (playing) {
    score[activePlayer] += currScore;
    setCurrentSc('score', score[activePlayer]);

    if (score[activePlayer] >= 50) {
      playing = false;
      document
        .querySelector(`.player--${activePlayer}`)
        .classList.add('player--winner');
      document
        .querySelector(`.player--${activePlayer}`)
        .classList.remove('player--active');
      diceEl.classList.toggle('hidden');
    } else {
      currScore = 0;
      setCurrentSc('current', currScore);
      switchPlayer();
    }
  }
});

newBtn.addEventListener('click', function () {
  init();
});
