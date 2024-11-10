document.addEventListener('DOMContentLoaded', () => {
    document.body.addEventListener('click', event => {
        const selectBtn = event.target.closest('.select-btn');
        if (selectBtn) {
            selectBtn.classList.toggle("open");
        }

        const item = event.target.closest('.item');
        if (item) {
            item.classList.toggle("checked");
            updateButtonText(item);
            toggleInput(item, getRowNumber(item));
        }
    });
});

function updateButtonText(item) {
    const row = item.closest('tr');
    const checkedItems = row.querySelectorAll('.item.checked');
    const btnText = row.querySelector('.btn-text');
    btnText.innerText = checkedItems.length > 0 ? `${checkedItems.length} Selected` : "Select Supervisors";
}

function toggleInput(item, rowNumber) {
    const isChecked = item.classList.contains('checked');
    const input = item.querySelector('input[type="hidden"]');
    const row = item.closest('tr');
    const rowItems = row.querySelectorAll('.item.checked');

    if (isChecked && !input) {
        const newInput = document.createElement('input');
        newInput.setAttribute('type', 'hidden');
        newInput.setAttribute('value', item.dataset.id);
        item.appendChild(newInput);
    } else if (!isChecked && input) {
        input.remove();
    }

    // Update all input names based on checked items in the row
    rowItems.forEach((checkedItem, index) => {
        const inputElement = checkedItem.querySelector('input[type="hidden"]');
        if (inputElement) {
            inputElement.setAttribute('name', `supervisors-${rowNumber}-${index + 1}`);
        }
    });
}

function getRowNumber(item) {
    const row = item.closest('tr');
    if (row) {
        const th = row.querySelector('th[scope="row"]');
        if (th) {
            return th.getAttribute('data-row');
        }
    }
    return 0;
}


document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('table-body');

    tableBody.addEventListener('click', event => {
        const deleteButton = event.target.closest('.deleteRow');
        if (deleteButton) {
            const row = deleteButton.closest('tr');
            if (row) {
                row.remove();
                updateRowNumbers();
            }
        }
    });
});


function updateRowNumbers() {
    const rows = document.querySelectorAll('#table-body tr');
    rows.forEach((row, index) => {
        const rowNumberCell = row.querySelector('th[scope="row"]');
        const selects = row.querySelectorAll('select');

        const newRowNumber = index + 1;

        // Update the row number text
        rowNumberCell.textContent = newRowNumber;

        // Update the data-row attribute
        row.querySelector('th').setAttribute('data-row', newRowNumber);

        // Update the name attribute of all select elements in the row
        selects.forEach(select => {
            const fieldName = select.getAttribute('name');
            select.setAttribute('name', fieldName.replace(/\d+/, newRowNumber));
        });
    });
}












document.addEventListener('DOMContentLoaded', function() {
    const examDateInput = document.getElementById('exameDate');
    const universityYearInput = document.getElementById('University-year');
    const semesterSelect = document.getElementById('semester');
    const typeelementSelect = document.getElementById('elementtype');
    const changeSemesterButton = document.getElementById('change-semester');
    const changetypeelementButton = document.getElementById('change-type-element');

    function determineSemester(selectedDate) {
        const month = selectedDate.getMonth();
        return (month >= 3 && month <= 7) ? 'Spring' : 'Autumn';
    }

    function determineTypeElement(selectedDate) {
        const month = selectedDate.getMonth();
        if (month === 8 || month === 9 || month === 1 || month === 0) {
            return 'DS1';
        }
        if (month === 10 || month === 11 || month === 3 || month === 4) {
            return 'DS2';
        }
        if (month === 0 || month === 5) {
            return 'Exam';
        }
        else {
            return 'Unknown';
        }

    }

    examDateInput.addEventListener('change', function() {
        const selectedDate = new Date(examDateInput.value);
        const currentYear = selectedDate.getFullYear();
        const nextYear = currentYear + 1;
        const universityYear = `${currentYear}/${nextYear}`;
        universityYearInput.value = universityYear;

        const semester = determineSemester(selectedDate);
        semesterSelect.value = semester;

        const typeelement = determineTypeElement(selectedDate);
        typeelementSelect.value = typeelement;
    });


    changeSemesterButton.addEventListener('click', function() {
        semesterSelect.removeAttribute('disabled');
    });

    changetypeelementButton.addEventListener('click', function() {
        typeelementSelect.removeAttribute('disabled');
    });

});


document.getElementById('educationalelement').addEventListener('change', function() {
    var elementId = this.value;
    var elementType = this.options[this.selectedIndex].getAttribute('data-type');
    var elementEnseignantId = this.options[this.selectedIndex].getAttribute('data-enseignant-id');

    var coordinatorSelect = document.getElementById('coordinator');
    coordinatorSelect.value = elementEnseignantId;

    var expectedDurationInput = document.getElementById('expectedduration');
    var actualDurationInput = document.getElementById('actualduration');

    if (elementType === 'Module') {
        expectedDurationInput.value = '120'
        actualDurationInput.value = '120';
    } else if (elementType === 'Element') {
        expectedDurationInput.value = '90';
        actualDurationInput.value = '90';
    }
});




var saveButton = document.getElementById("saveButton");
var submitButton = document.getElementById("btn-submit");

saveButton.addEventListener("click", function() {

    document.getElementById("elementtype").disabled = false;
    document.getElementById("semester").disabled = false;
});


function displayFileName(inputId, labelId) {
    const input = document.getElementById(inputId);
    const label = document.getElementById(labelId);
    if (input.files.length > 0) {
        label.innerText = input.files[0].name;
    } else {
        label.innerText = "Choose file";
    }
}