
$(document).ready(function () {
    $('#dtBasicExample').DataTable();
    $('.dataTables_length').addClass('bs-select');
});

dTable=$('#myTable').DataTable({
    "bLengthChange": false,
    "lengthMenu": [4],
    "columnDefs": [
        {"className": "dt-center", "targets": "_all"}
    ],
    "dom":"lrtip"
});
$('#myCustomSearchBox').keyup(function(){
    dTable.search($(this).val()).draw();
})


var saveButton = document.getElementById("saveButton");
var submitButton = document.getElementById("btn-submit");

saveButton.addEventListener("click", function() {
    submitButton.click();
});


var submitButtonupdate = document.getElementById("update-btn-submit");

saveButton.addEventListener("click", function() {
    submitButtonupdate.click();
});


$(document).ready(function () {

    $('#updateButton').on('click', function () {
        $('.second-buttons').removeClass('d-none');
        $('.first-buttons').addClass('d-none');
        $('#EmpInfForm input').prop('readonly', false);
        $('#EmpInfForm input').prop('required', true);
    });
});



var savebackButton = document.getElementById("backButton");
var submitbacktButton = document.getElementById("btn-back-submit");

savebackButton.addEventListener("click", function() {
    submitbacktButton.click();
});
