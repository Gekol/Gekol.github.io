let isStorageSupport = true;
let goods = ``;

try {
    goods = localStorage.getItem(`goods`);
    if (goods == null) {
        goods = 0;
        localStorage.setItem(`goods`, goods);
    } else {
        goods = parseInt(goods);
    }
} catch (err) {
    isStorageSupport = false;
}

document.querySelectorAll(`.modal-close`).forEach(element => {
    element.addEventListener(`click`, function (event) {
        event.preventDefault();
        event.target.parentNode.parentNode.classList.toggle(`visually-hidden`);
        event.target.parentNode.parentNode.classList.remove(`modal-error`);
    });
});

let leaveFeedbackButton = document.querySelector(`.leave-feedback-show`);

if (leaveFeedbackButton) {
    leaveFeedbackButton.addEventListener(`click`, function (event) {
        event.preventDefault();
        document.querySelector(`.leave-feedback.visually-hidden`).classList.toggle(`visually-hidden`);
    });
}

let buyButtons = document.querySelectorAll(`.buy-button`);

buyButtons.forEach(element => {
    element.addEventListener(`click`, function (event) {
        let boughtBlock = document.querySelector(`.item-added-block`);
        if (boughtBlock) {
            boughtBlock.classList.toggle(`visually-hidden`);
        }
    })
});

let leftArrow = document.querySelector(`.leftArrow`);

function leftArrowClick(event) {
    event.preventDefault();
    let currentElem = document.querySelector(`.opened`);
    let slide = document.querySelector(`.slides .perforators:not(.visually-hidden)`);
    if (currentElem !== currentElem.parentNode.firstElementChild) {
        let prev = document.querySelector(`.opened`).previousElementSibling;
        currentElem.classList.toggle(`opened`);
        prev.classList.toggle(`opened`);
        slide.previousElementSibling.classList.toggle(`visually-hidden`);
        slide.classList.toggle(`visually-hidden`);
    }
}

if (leftArrow) {
    leftArrow.addEventListener(`keypress`, function (event) {
        if (event.keyCode === 13) {
            leftArrowClick(event)
        }
    });
    leftArrow.addEventListener(`click`, leftArrowClick);
}

function rightArrowClick(event) {
    event.preventDefault();
    let currentElem = document.querySelector(`.opened`);
    let slide = document.querySelector(`.slides .perforators:not(.visually-hidden)`);
    if (currentElem !== currentElem.parentNode.lastElementChild) {
        let next = document.querySelector(`.opened`).nextElementSibling;
        currentElem.classList.toggle(`opened`);
        next.classList.toggle(`opened`);
        slide.nextElementSibling.classList.toggle(`visually-hidden`);
        slide.classList.toggle(`visually-hidden`);
    }
}

let rightArrow = document.querySelector(`.rightArrow`);
if (rightArrow) {
    rightArrow.addEventListener(`keypress`, function (event) {
        if (event.keyCode === 13) {
            rightArrowClick(event);
        }
    });
    rightArrow.addEventListener(`click`, rightArrowClick);
}

function changeSlide(event, index) {
    let slides = document.querySelectorAll(`.perforators`);
    let slideTitles = document.querySelectorAll(`.slide-title`);
    slides.forEach((currentSlide, currentIndex) => {
        let currentTitle = slideTitles[currentIndex];
        if (currentIndex === index) {
            currentSlide.classList.remove(`visually-hidden`);
            currentTitle.classList.remove(`visually-hidden`);
        } else {
            currentSlide.classList.add(`visually-hidden`);
            currentTitle.classList.add(`visually-hidden`);
        }
    });

    sliderButtons.forEach(currentButton => {
        currentButton.classList.remove(`opened`);
    });
    event.target.classList.add(`opened`);
}

let sliderButtons = document.querySelectorAll(`.slider-page`);
if (sliderButtons) {
    sliderButtons.forEach((element, index) => {
        element.addEventListener(`keypress`, function (event) {
            if (event.keyCode === 13) {
                changeSlide(event, index);
            }
        });
        element.addEventListener(`click`, function (event) {
            changeSlide(event, index);
        });
    });
}

document.querySelectorAll(`.service-button`).forEach((element, index) => {
    element.addEventListener(`click`, function (event) {
        document.querySelectorAll(`.service-button`).forEach(currentButton => {
            currentButton.disabled = false;
        });
        element.disabled = true;
        let blocks = document.querySelectorAll(`.service-detail`);
        blocks.forEach(element => {
            element.parentElement.classList.add(`visually-hidden`);
        });
        blocks[index].parentNode.classList.remove(`visually-hidden`);
    })
});

document.querySelector(`.search input`).addEventListener(`focus`, function (event) {
    document.querySelector(`.search-block`).style.backgroundColor = `#FFFFFF`;
});

document.querySelector(`.search input`).addEventListener(`blur`, element => {
    document.querySelector(`.search-block`).style.backgroundColor = `#293449`;
});

let miniMap = document.querySelector(`.mini-map-block`);

function showMap(event) {
    if (event.keyCode === 13 || event.button === 0) {
        event.preventDefault();
        document.querySelector(`.interactive-map-block`).classList.toggle(`visually-hidden`);
    }
}

if (miniMap) {
    miniMap.addEventListener(`click`, showMap);
    miniMap.addEventListener(`keypress`, showMap)
}

let feedbackForm = document.querySelector(`.write-us`);
if (feedbackForm) {
    feedbackForm.addEventListener(`submit`, function (event) {
        event.preventDefault();
        let name = document.querySelector(`.user_name`).value;
        let strings = name.split(` `);
        let alphabet = /([абвгдежзиёйцукгншщзхъфыпролэячсмитьбю])+/;
        if (!(strings.length === 2 && strings[0].length > 0 && strings[1].toLowerCase().length > 0 && strings[0].match(alphabet) && strings[1].toLowerCase().match(alphabet))) {
            feedbackForm.parentElement.classList.remove(`modal-error`);
            feedbackForm.parentElement.classList.add(`modal-error`);
            alert(`Incorrect name`);
        } else {
            feedbackForm.classList.remove(`modal-error`);
        }
    })
}

document.querySelectorAll(`.manufacturer-filter label`).forEach(element => {
    element.addEventListener(`keypress`, function (event) {
        if (event.keyCode === 13) {
            event.target.previousElementSibling.checked = !event.target.previousElementSibling.checked;
        }
    })
});

document.querySelectorAll(`.accumulator-filter label`).forEach(element => {
    element.addEventListener(`keypress`, function (event) {
        if (event.keyCode === 13) {
            event.target.previousElementSibling.checked = true;
        }
    })
});

const registerForm = document.querySelector(`.register-form`);
const registerButton = document.querySelector(`.register`);
if (registerButton !== null) {
    registerButton.addEventListener(`click`, function (evt) {
        evt.preventDefault();
        registerForm.classList.toggle(`visually-hidden`);
    });
}

const loginForm = document.querySelector(`.login-form`);
const loginButton = document.querySelector(`.login`);
if (loginButton !== null) {
    loginButton.addEventListener(`click`, function (evt) {
        evt.preventDefault();
        loginForm.classList.toggle(`visually-hidden`);
    });
}


const addBookButton = document.querySelector(`.addBook`);
const addBookForm = document.querySelector(`.book-form`);
if (addBookButton !== null) {
    console.log(addBookButton);
    console.log(addBookForm);
    addBookButton.addEventListener(`click`, function (evt) {
        evt.preventDefault();
        addBookForm.classList.toggle(`visually-hidden`);
    })
}
