let currentCoefficients = {};

function loadCoefficients() {
    const specialtyId = document.getElementById('specialty').value;
    if (!specialtyId) return;

    fetch('/get-coefs/' + specialtyId)
        .then(response => response.json())
        .then(data => {
            currentCoefficients = data;

            // заповнення коефіцієнтів для обов'язкових предметів
            document.getElementById('ukr_mova_coef').textContent = `(коеф.: ${data.ukr_mova})`;
            document.getElementById('math_coef').textContent = `(коеф.: ${data.math})`;
            document.getElementById('history_coef').textContent = `(коеф.: ${data.history})`;

            // заповнення коефіцієнта для додаткового предмета
            updateAdditionalSubjectCoefficient();
        })
        .catch(error => console.error('Помилка:', error));
}

function updateAdditionalSubjectInput() {
    const subjectSelect = document.getElementById('additionalSubject');
    const container = document.getElementById('additionalSubjectInputContainer');
    const label = document.getElementById('additionalSubjectLabel');

    if (subjectSelect.value) {
        const subjectName = subjectSelect.options[subjectSelect.selectedIndex].text;
        label.textContent = subjectName + ':';
        container.style.display = 'block';
        updateAdditionalSubjectCoefficient();
    } else {
        container.style.display = 'none';
    }
}

function updateAdditionalSubjectCoefficient() {
    const subjectSelect = document.getElementById('additionalSubject');
    if (!subjectSelect.value || !currentCoefficients[subjectSelect.value]) {
        document.getElementById('additionalSubject_coef').textContent = '';
        return;
    }

    document.getElementById('additionalSubject_coef').textContent =
        `(коефіцієнт: ${currentCoefficients[subjectSelect.value]})`;
}

function calculateScore() {
    const specialtyId = document.getElementById('specialty').value;
    if (!specialtyId) {
        alert('Будь ласка, оберіть спеціальність');
        return;
    }

    //  обов'язкові предметів
    const ukrMova = document.getElementById('ukr_mova').value;
    const math = document.getElementById('math').value;
    const history = document.getElementById('history').value;

    if (!ukrMova || !math || !history) {
        alert('Будь ласка, введіть бали для всіх обов\'язкових предметів');
        return;
    }

    // перевірка додаткового предмета
    const additionalSubject = document.getElementById('additionalSubject').value;
    const additionalSubjectScore = document.getElementById('additionalSubjectScore').value;

    if (!additionalSubject || !additionalSubjectScore) {
        alert('Будь ласка, оберіть та введіть бал для додаткового предмета');
        return;
    }

    // об'єкт з балами
    const subjectScores = {
        ukr_mova: parseFloat(ukrMova),
        math: parseFloat(math),
        history: parseFloat(history)
    };

    // додавання додаткового предмета
    subjectScores[additionalSubject] = parseFloat(additionalSubjectScore);

    // відправка на сервер
    fetch('/calc-score', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            specialtyId: specialtyId,
            subjectScores: subjectScores
        })
    })
        .then(response => response.json())
        .then(result => {
            const resultElement = document.getElementById('result');
            resultElement.textContent = `Ваш вступний бал: ${result.toFixed(2)}`;
            resultElement.style.display = 'block';
        })
        .catch(error => {
            console.error('Помилка:', error);
            alert('Сталася помилка під час розрахунку');
        });
}