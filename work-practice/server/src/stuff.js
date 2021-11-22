


const letterCombinations = function(digits) {
  if(digits.trim() === '') {
    return [];
  }
  const table = {
    2: ['a', 'b', 'c'],
    3: ['d', 'e', 'f'],
    4: ['g', 'h', 'i'],
    5: ['j', 'k', 'l'],
    6: ['m', 'n', 'o'],
    7: ['p', 'q', 'r', 's'],
    8: ['t', 'u', 'v'],
    9: ['w', 'x', 'y', 'z'],
  };
  let answer = table[digits[0]];
  let newAnswer = [];
  for(let x=1; x < digits.length; x++) {
    const digitValues = table[digits[x]];
    if(x !== 1) {
      answer = newAnswer;
      newAnswer = [];
    }
    for(let i =0; i < answer.length; i++) {
      let string = answer[i];
      for(let val of digitValues) {
        const addedString = `${string}${val}`;
        newAnswer.push(addedString);
      }
    }
  }

  return newAnswer;


};
console.log(letterCombinations("234"));