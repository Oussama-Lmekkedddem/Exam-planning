$(document).ready(function () {
    $("#ByFiliere").click(function () {
        if ($("#ByFiliereOption").hasClass("d-none")) {
            $("#ByFiliereOption").removeClass("d-none");
        } else {
            $("#ByFiliereOption").addClass("d-none");
        }
        $("#ByDepartementOption").addClass("d-none");
    });

    $("#ByDepartement").click(function () {
        if ($("#ByDepartementOption").hasClass("d-none")) {
            $("#ByDepartementOption").removeClass("d-none");
        } else {
            $("#ByDepartementOption").addClass("d-none");
        }
        $("#ByFiliereOption").addClass("d-none");
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const addButtons = document.querySelectorAll(".add-btn");

    addButtons.forEach(button => {
        button.addEventListener("click", function () {
            const enseignantId = this.getAttribute("data-id");
            const row = this.closest("tr");
            const enseignantName = row.querySelector(".td1:nth-child(2)").innerText;

            const listEnseignants = document.getElementById("List-Enseignants");
            const newEnseignant = `
                <div class="col-sm-6 mt-1">
                    <div class="d-flex justify-content-between align-items-center">
                        <input type="hidden" name="enseignants" value="${enseignantId}">
                        <input type="text" class="form-control" value="${enseignantName}" readonly>
                        <button class="btn remove-btn text-left"><i class="fas fa-times"></i></button>
                    </div>
                </div>`;
            listEnseignants.insertAdjacentHTML('beforeend', newEnseignant);

            this.disabled = true;

            listEnseignants.lastElementChild.querySelector(".remove-btn").addEventListener("click", function () {
                button.disabled = false;
                this.closest(".col-sm-6").remove();
            });

        });
    });
});





$(document).ready(function () {

    $('#updateButton').on('click', function () {
        $('.update-group-table').removeClass('d-none');
        $('.btn-remove').removeClass('d-none');
    });
});




document.addEventListener("DOMContentLoaded", function () {
    const updateTableGroup = document.querySelector('#update-table-group tbody');

    // Function to update row numbers
    function updateRowNumbers() {
        const rows = document.querySelectorAll('#update-table-group tbody tr');
        rows.forEach((row, index) => {
            row.querySelector('td').textContent = index + 1;
        });
    }

    // Function to disable buttons if ID exists in update-table-group
    function disableMatchingUpdateButtons() {
        const existingIds = new Set();
        document.querySelectorAll('#update-table-group input[name="enseignants"]').forEach(input => {
            existingIds.add(input.value);
        });

        document.querySelectorAll('#dtBasicExample .update-btn').forEach(button => {
            const enseignantId = button.getAttribute('data-id');
            if (existingIds.has(enseignantId)) {
                button.disabled = true;
                button.classList.add('disabled');
            } else {
                button.disabled = false;
                button.classList.remove('disabled');
            }
        });
    }

    // Attach click event listener to the parent table for remove buttons using event delegation
    updateTableGroup.addEventListener('click', function (event) {
        if (event.target.closest('.btn-remove')) {
            const button = event.target.closest('.btn-remove');
            button.closest('tr').remove();
            updateRowNumbers();
            disableMatchingUpdateButtons(); // Re-check the buttons to enable if needed
        }
    });

    // Attach click event listeners to the update buttons
    document.querySelectorAll(".update-btn").forEach(button => {
        button.addEventListener("click", function () {
            const enseignantId = this.getAttribute("data-id");
            const row = this.closest("tr");
            const enseignantName = row.children[1].innerText;
            const enseignantDepartement = row.children[2].innerText;
            const enseignantFiliere = row.children[3].innerText;

            const newEnseignant = `
                <tr>
                    <input type="hidden" name="enseignants" value="${enseignantId}">
                    <td></td>
                    <td>${enseignantName}</td>
                    <td>${enseignantDepartement}</td>
                    <td>${enseignantFiliere}</td>
                    <td><button class="btn btn-remove" type="button"><i class="fas fa-times"></i></button></td>
                </tr>
            `;
            updateTableGroup.insertAdjacentHTML('beforeend', newEnseignant);
            updateRowNumbers();
            this.disabled = true;
            this.classList.add('disabled');

        });
    });

    disableMatchingUpdateButtons();
});
