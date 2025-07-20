const specialtySelect = document.getElementById('specialtySelect');
const optionalSubjectSelect = document.getElementById('optionalSubject');
const optionalCoef = document.getElementById('optionalCoef');

let currentCoefs = {};

specialtySelect.addEventListener('change', function () {
    const specialtyId = this.value;
    if (!specialtyId) return;

    fetch('/get-coefs/' + specialtyId)
        .then(res => res.json())
        .then(coefs => {
            currentCoefs = coefs;

            // Встановлюємо коефіцієнти для обов’язкових
            ['ukr_mova', 'math', 'history'].forEach(sub => {
                const row = document.querySelector(`.subject-row[data-subject="${sub}"]`);
                const coefInput = row.querySelector('.coef');
                coefInput.value = coefs[sub] || '';
            });

            // Додатковий — встановити, якщо вже вибрано
            const selected = optionalSubjectSelect.value;
            if (selected && coefs[selected]) {
                optionalCoef.value = coefs[selected];
            } else {
                optionalCoef.value = '';
            }
        });
});

// Коли змінюється додатковий предмет — оновити коефіцієнт
optionalSubjectSelect.addEventListener('change', function () {
    const subject = this.value;
    optionalCoef.value = currentCoefs[subject] || '';
});
