'use strict';

/// MODAL WINDOW BEHAVIOR ///
//#region ///MODAL VARIABLES
const contactModal = document.querySelector('.contact-modal');
const modal = document.querySelector('.modal');
const overlay = document.querySelector('.overlay');
const btnCloseModal = document.querySelector('.close-modal');
const btnOpenModal = document.querySelector('.show-modal');
const btnOpenContact = document.querySelectorAll('.show-contact-modal');
const btnCloseContact = document.querySelector('.close-modal-contact');
const btnOpenAbout = document.querySelector('#about-nav');
const btnOpenPrivacy = document.querySelector('#privacy-nav');
const privacyModal = document.querySelector('.modal-privacy');
const aboutModal = document.querySelector('.modal-about');
const btnClosePrivacy = document.querySelector('.close-modal-privacy');
const btnCloseAbout = document.querySelector('.close-modal-about');

const closeModal = function () {
  modal.classList.add('hidden');
  overlay.classList.add('hidden');
};
const openModal = function () {
  modal.classList.remove('hidden');
  overlay.classList.remove('hidden');
};
const closeContactModal = function(){
  contactModal.classList.add('hidden');
  overlay.classList.add('hidden');
}
const openContactModal = function(){
  contactModal.classList.remove('hidden');
  overlay.classList.remove('hidden');
}
const openAboutModal = function(){
  aboutModal.classList.remove('hidden');
  overlay.classList.remove('hidden');
}
const openPrivacyModal = function(){
  privacyModal.classList.remove('hidden');
  overlay.classList.remove('hidden');
}
const closeAboutModal = function(){
  aboutModal.classList.add('hidden');
  overlay.classList.add('hidden');
}
const closePrivacyModal = function(){
  privacyModal.classList.add('hidden');
  overlay.classList.add('hidden');
}
btnOpenContact.forEach(btn => btn.addEventListener('click', openContactModal));
btnOpenModal.addEventListener('click', openModal);
btnCloseModal.addEventListener('click', closeModal);
overlay.addEventListener('click', closeModal);
btnCloseContact.addEventListener('click', closeContactModal);
overlay.addEventListener('click', closeContactModal);
btnOpenPrivacy.addEventListener('click', openPrivacyModal);
btnOpenAbout.addEventListener('click', openAboutModal);
btnCloseAbout.addEventListener('click', closeAboutModal);
btnClosePrivacy.addEventListener('click', closePrivacyModal);
//#endregion /// END MODAL


/////////  SMOOTH SCROLLING BEHAVIOR /////
//#region  
const backToTopBtn = document.querySelector('.top-link');


backToTopBtn.addEventListener('click', function(e){
  e.preventDefault();
  const top = document.querySelector('.top-nav');
  top.scrollIntoView({behavior: 'smooth'});
});
//#endregion

