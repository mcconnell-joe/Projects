const btnOpenContact = document.querySelectorAll(".show-contact-modal");
const btnCloseContact = document.querySelector(".close-modal-contact");
const overlay = document.querySelector(".overlay");
const contactModal = document.querySelector(".contact-modal");

const btnOpenAbout = document.querySelector("#about-nav");
const btnOpenPrivacy = document.querySelector("#privacy-nav");
const privacyModal = document.querySelector(".modal-privacy");
const aboutModal = document.querySelector(".modal-about");
const btnClosePrivacy = document.querySelector(".close-modal-privacy");
const btnCloseAbout = document.querySelector(".close-modal-about");

const closeContactModal = function () {
  contactModal.classList.add("hidden");
  overlay.classList.add("hidden");
};
const openContactModal = function () {
  contactModal.classList.remove("hidden");
  overlay.classList.remove("hidden");
};
const openAboutModal = function () {
  aboutModal.classList.remove("hidden");
  overlay.classList.remove("hidden");
};
const openPrivacyModal = function () {
  privacyModal.classList.remove("hidden");
  overlay.classList.remove("hidden");
};
const closeAboutModal = function () {
  aboutModal.classList.add("hidden");
  overlay.classList.add("hidden");
};
const closePrivacyModal = function () {
  privacyModal.classList.add("hidden");
  overlay.classList.add("hidden");
};
btnOpenContact.forEach((btn) =>
  btn.addEventListener("click", openContactModal)
);
btnCloseContact.addEventListener("click", closeContactModal);
overlay.addEventListener("click", closeContactModal);
btnOpenPrivacy.addEventListener("click", openPrivacyModal);
btnOpenAbout.addEventListener("click", openAboutModal);
btnCloseAbout.addEventListener("click", closeAboutModal);
btnClosePrivacy.addEventListener("click", closePrivacyModal);

/////////  SMOOTH SCROLLING BEHAVIOR /////
//#region
const backToTopBtn = document.querySelector(".top-link");
const backToTopProject = document.querySelector(".project-top");
const topNav = document.querySelector(".top-nav");

backToTopProject.addEventListener("click", function (e) {
  e.preventDefault();
  topNav.scrollIntoView({ behavior: "smooth" });
});

backToTopBtn.addEventListener("click", function (e) {
  e.preventDefault();
  topNav.scrollIntoView({
    behavior: "smooth",
  });
});
//#endregion

//#region ///Making toTop appear after header is out of view
const toTopBtn = document.querySelector("#pop-up-top");
const bottom = document.querySelector(".bottom-scroll");

const header = document.querySelector(".header");
const toTop = function (entries) {
  const [entry] = entries;
  if (entry.isIntersecting) {
    toTopBtn.classList.add("hidden");
    return;
  }
  toTopBtn.classList.remove("hidden");
};
const headerObserver = new IntersectionObserver(toTop, {
  root: null,
  threshold: 0,
});
const toTopBtm = function (entries) {
  const [entry] = entries;
  if (entry.isIntersecting) {
    toTopBtn.classList.add("hidden");
    return;
  }
  toTopBtn.classList.remove("hidden");
};
const bottomObserver = new IntersectionObserver(toTopBtm, {
  root: null,
  threshold: 1,
});
bottomObserver.observe(bottom);
headerObserver.observe(header);
//Make button scroll back to the top
toTopBtn.addEventListener("click", function (e) {
  e.preventDefault();
  topNav.scrollIntoView({ behavior: "smooth" });
});
//#endregion
