"use strict";

const form = document.querySelector(".form");
const containerWorkouts = document.querySelector(".exercises");
const inputType = document.querySelector(".input-type");
const inputDistance = document.querySelector(".input-distance");
const inputDuration = document.querySelector(".input-duration");
const inputCadence = document.querySelector(".input-cadence");
const inputElevation = document.querySelector(".input-elevation");
const resetBtn = document.querySelector("#reset-link");
//Global map variables:
let map, mapEvent;

class Exercise {
  date = new Date();
  id = (Date.now() + "").slice(-10);
  constructor(coords, distance, duration) {
    console.log(this.date);
    this.distance = distance;
    this.duration = duration;
    this.coords = coords; // [lat, lng]
  }
  _setDescription() {
    //prettier-ignore
    const months = ["January","February","March","April","May","June","July",
    "August","September","October","November","December"];
    console.log(this.date);
    console.log(this.date.getDate());
    this.description = `${this.type[0].toUpperCase()}${this.type.slice(1)} on ${
      months[this.date.getMonth()]
    } ${this.date.getDate()}
    `;
  }
  _calculatePace() {
    this.pace = this.duration / this.distance;
    return this.pace;
  }
}

class Running extends Exercise {
  type = "running";
  constructor(coords, distance, duration, cadence) {
    super(coords, distance, duration);
    this.cadence = cadence;
    this._calculatePace();
    this._setDescription();
  }
}

class Cycling extends Exercise {
  type = "cycling";
  constructor(coords, distance, duration, elevationDif) {
    super(coords, distance, duration);
    this.elevationDif = elevationDif;
    this._setDescription();
    this.calculateSpeed();
  }
  //MI per HR
  calculateSpeed() {
    this.speed = this.distance / (this.duration / 60);
    return this.speed;
  }
}

class Hiking extends Exercise {
  type = "hiking";
  constructor(coords, distance, duration, elevationDif) {
    super(coords, distance, duration);
    this.elevationDif = elevationDif;
    this._setDescription();
    this._calculatePace();
  }
}
class App {
  //Private Variables
  _map;
  _mapEvent;
  _workouts = [];
  _mapZoom = 13;

  constructor() {
    this._getPosition();
    //Get data from local storage
    this._getLocalStorage();
    form.addEventListener("submit", this._newWorkout.bind(this));
    inputType.value = "running";
    inputType.addEventListener("change", this._toggleElevationField);
    containerWorkouts.addEventListener("click", this._moveToPopUp.bind(this));
  }
  _getPosition() {
    swal(
      "Notice",
      "Cannot get location on http server using creators home town",
      "warning"
    );
    const pos = {
      coords: {
        latitude: 47.65405664722081,
        longitude: -117.42324829101564,
      },
    };
    this._loadMap(pos);
    // if (navigator.geolocation) {
    //   console.log(navigator.geolocation);
    //   navigator.geolocation.getCurrentPosition(
    //     this._loadMap.bind(this),
    //     function () {
    //       swal("Error", "Could not get your location.", "error");
    //     }
    //   );
    // }
  }

  _loadMap(position) {
    const { latitude } = position.coords;
    const { longitude } = position.coords;
    const coords = [latitude, longitude];
    this._map = L.map("map").setView(coords, this._mapZoom);

    L.marker(coords)
      .bindPopup()
      .setPopupContent("Your Location")
      .addTo(this._map);
    L.tileLayer("https://{s}.tile.openstreetmap.fr/hot//{z}/{x}/{y}.png", {
      attribution:
        '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(this._map);
    // handling clicks on map //
    this._map.on("click", this._showForm.bind(this));

    this._workouts.forEach((work) => {
      this.renderWorkoutMarker(work);
    });
    resetBtn.addEventListener("click", this.reset);
  }
  _showForm(mapE) {
    this._mapEvent = mapE;
    form.classList.remove("hidden");
    inputDistance.focus();
  }
  _hideForm() {
    this._clearInputs();
    form.style.display = "none";
    form.classList.add("hidden");
    setTimeout(() => (form.style.display = "grid"), 1000);
  }
  _toggleElevationField() {
    if (inputType.value === "cycling" || inputType.value === "hiking") {
      inputElevation.closest(".form-row").classList.remove("form-row-hidden");
      inputCadence.closest(".form-row").classList.add("form-row-hidden");
    } else {
      inputElevation.closest(".form-row").classList.add("form-row-hidden");
      inputCadence.closest(".form-row").classList.remove("form-row-hidden");
    }
  }

  _newWorkout(e) {
    const validInput = (...inputs) =>
      inputs.every((inp) => Number.isFinite(inp));
    const inputsPositive = (...inputs) => inputs.every((inp) => inp > 0);
    e.preventDefault();
    // Get the form data
    const type = inputType.value;
    const distance = +inputDistance.value;
    const duration = +inputDuration.value;
    const { lat, lng } = this._mapEvent.latlng;
    let exercise;
    // Validate data

    //Create associated object
    if (type === "running") {
      const cadence = +inputCadence.value;
      const values = [distance, duration, cadence];
      if (!validInput(...values) || !inputsPositive(...values)) {
        this._clearInputs();
        return invalidInput();
      }
      exercise = new Running([lat, lng], distance, duration, cadence);
    }
    if (type === "cycling") {
      const elevationDif = +inputElevation.value;
      const values = [distance, duration, elevationDif];
      if (!validInput(...values) || !inputsPositive(...values)) {
        this._clearInputs();
        return invalidInput();
      }
      exercise = new Cycling([lat, lng], distance, duration, elevationDif);
    }
    if (type === "hiking") {
      const elevationDif = +inputElevation.value;
      const values = [distance, duration, elevationDif];
      if (!validInput(...values) || !inputsPositive(...values)) {
        this._clearInputs();
        return invalidInput();
      }
      exercise = new Hiking([lat, lng], distance, duration, elevationDif);
    }

    function invalidInput() {
      return swal("Invalid Input", "Inputs must be a positive number", "error");
    }

    // Add new object to the exercise array
    this._workouts.push(exercise);
    //render exercise on map as marker
    this.renderWorkoutMarker(exercise);
    //render workout on the list
    this._renderExercise(exercise);
    // Hide form and  CLEAR INPUT FIELDS //
    this._clearInputs();
    // DISPLAY MAP MARKER //
    this._hideForm();
    // Set Local Storage to All Workouts //
    this._setLocalStorage();
  }

  _clearInputs() {
    inputDistance.value =
      inputDuration.value =
      inputCadence.value =
      inputElevation.value =
        "";
  }
  renderWorkoutMarker(exercise) {
    L.marker(exercise.coords)
      .addTo(this._map)
      .bindPopup(
        L.popup({
          maxWidth: 250,
          minWidth: 100,
          autoClose: false,
          closeOnClick: false,
          className: `${exercise.type}-popup`,
        })
      )
      .setPopupContent(
        `${
          exercise.type === "running"
            ? "🏃‍♂️"
            : exercise.type === "cycling"
            ? "🚴‍♀️"
            : "🎒"
        } ${exercise.description}`
      )
      .openPopup();
  }

  _renderExercise(exercise) {
    let html = `
    <li class="exercise exercise-${exercise.type}" data-id="${exercise.id}">
      <h2 class="exercise-title">${exercise.description}</h2>
      <div class="exercise-details">
          <span class="exercise-icon">${
            exercise.type === "running"
              ? "🏃‍♂️"
              : exercise.type === "cycling"
              ? "🚴‍♀️"
              : "🎒"
          }</span>
          <span class="exercise-value">${exercise.distance}</span>
          <span class="exercise-unit">mi</span>
      </div>
      <div class="exercise-details">
          <span class="exercise-icon">⏱</span>
          <span class="exercise-value">${exercise.duration}</span>
          <span class="exercise-unit">min</span>
      </div>`;

    if (exercise.type === "running") {
      html += `
      <div class="exercise-details">
        <span class="exercise-icon">⚡️</span>
        <span class="exercise-value">${exercise.pace.toFixed(1)}</span>
        <span class="exercise-unit">min/ML</span>
      </div>
      <div class="exercise-details">
        <span class="exercise-icon">🦶🏼</span>
        <span class="exercise-value">${exercise.cadence}</span>
        <span class="exercise-unit">spm</span>
      </div>
    </li>`;
    }
    if (exercise.type === "cycling") {
      html += `
      <div class="exercise-details">
        <span class="exercise-icon">⚡️</span>
        <span class="exercise-value">${exercise.speed.toFixed(1)}</span>
        <span class="exercise-unit">mi/h</span>
      </div>
      <div class="exercise-details">
        <span class="exercise-icon">⛰</span>
        <span class="exercise-value">${exercise.elevationDif}</span>
        <span class="exercise-unit">m</span>
      </div>
  </li>`;
    }
    if (exercise.type === "hiking") {
      html += `
      <div class="exercise-details">
          <span class="exercise-icon">⚡️</span>
          <span class="exercise-value">${exercise.pace.toFixed(1)}</span>
          <span class="exercise-unit">mi/h</span>
      </div>
      <div class="exercise-details">
          <span class="exercise-icon">🗻</span>
          <span class="exercise-value">${exercise.elevationDif}</span>
          <span class="exercise-unit">m</span>
      </div>
    </li>`;
    }
    form.insertAdjacentHTML("afterend", html);
  }
  _moveToPopUp(e) {
    const workoutEL = e.target.closest(".exercise");
    console.log(workoutEL);

    if (!workoutEL) return;

    const workout = this._workouts.find(
      (work) => work.id === workoutEL.dataset.id
    );
    console.log(workout);

    this._map.setView(workout.coords, this._mapZoom, {
      animate: true,
      pan: {
        duration: 1,
      },
    });
  }

  _setLocalStorage() {
    localStorage.setItem("workouts", JSON.stringify(this._workouts));
  }
  _getLocalStorage() {
    const data = JSON.parse(localStorage.getItem("workouts"));
    if (!data) return;
    this._workouts = data;
    this._workouts.forEach((work) => {
      this._renderExercise(work);
    });
  }
  reset() {
    localStorage.removeItem("workouts");
    location.reload();
  }
}

/// Starting Application at Runtime.
const app = new App();
