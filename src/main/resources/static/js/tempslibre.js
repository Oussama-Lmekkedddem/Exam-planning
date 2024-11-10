document.addEventListener("DOMContentLoaded", function() {
    const daysOfWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const timeSlots = ['8:00 - 10:00', '10:00 - 12:00', '14:00 - 16:00', '16:00 - 18:00'];
    const tbody = document.getElementById('calendar-body');

    daysOfWeek.forEach(day => {
        const tr = document.createElement('tr');
        const th = document.createElement('th');
        th.textContent = day;
        th.classList.add('text-center');
        tr.appendChild(th);

        timeSlots.forEach((slot, index) => {
            const td = document.createElement('td');
            const id = ((daysOfWeek.indexOf(day) * timeSlots.length) + index);
            td.id = 'case-' + id;
            td.classList.add('time-slot');

            const div = document.createElement('div');
            div.classList.add('full-div', 'd-none');
            td.appendChild(div);

            tr.appendChild(td);
        });

        tbody.appendChild(tr);
    });

    var tableau = document.getElementById('templibre-intervale');
    var idTempsLibres = [];
    var intervallesExistes = [];

    for (var i = 1; i < tableau.rows.length; i++) {
        var row = tableau.rows[i];
        var idTempsLibre = row.cells[0].innerText.trim();
        var intervalExiste = row.cells[1].innerText.trim() === 'Oui' ? 1 : 0;

        idTempsLibres.push(idTempsLibre);
        intervallesExistes.push(intervalExiste);
    }
    toggleDivVisibility(intervallesExistes);
    createInputs(idTempsLibres, intervallesExistes);

    const fullTds = document.querySelectorAll('.time-slot');
    fullTds.forEach((td) => {
        td.addEventListener('click', () => {
            const divId = td.id;
            updateIntervallesExistes(divId);
            toggleDivVisibility(intervallesExistes);
            createInputs(idTempsLibres, intervallesExistes);
        });
    });

    function updateIntervallesExistes(id) {
        const index = parseInt(id.split('-')[1]);
        const td = document.getElementById(id);
        const element = td.firstElementChild;
        if (element.classList.contains('d-none')) {
            intervallesExistes[index] = 0;
        } else {
            intervallesExistes[index] = 1;
        }
    }

    function toggleDivVisibility(intervallesExistes) {
        intervallesExistes.forEach((exist, index) => {
            const id = 'case-' + index;
            const tdElement = document.getElementById(id);
            const divElement = tdElement.querySelector('.full-div');

            if (exist === 0) {
                divElement.classList.remove('d-none');
            } else {
                divElement.classList.add('d-none');
            }
        });
    }

    function createInputs(idTempsLibres, intervallesExistes) {
        var inputsPlace = document.getElementById('inputs-temp-libre-place');

        // Clear any existing inputs
        inputsPlace.innerHTML = '';

        // Iterate over the lists and create input elements
        for (var i = 0; i < idTempsLibres.length; i++) {
            var id = idTempsLibres[i];
            var intervalExist = intervallesExistes[i];
            intervalExist = intervalExist ? 'true' : 'false';

            // Create input for id
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'tempslibres_Id';
            inputId.value = id;

            // Create input for intervalExist
            var inputIntervalExist = document.createElement('input');
            inputIntervalExist.type = 'hidden';
            inputIntervalExist.name = 'tempslibres_intervalExiste';
            inputIntervalExist.value = intervalExist;

            // Append the input elements to the inputsPlace element
            inputsPlace.appendChild(inputId);
            inputsPlace.appendChild(inputIntervalExist);
        }
    }
});
