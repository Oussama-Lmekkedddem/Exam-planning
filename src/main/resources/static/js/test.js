var saveButton = document.getElementById("saveButton");
var submitButton = document.getElementById("btn-submit");

saveButton.addEventListener("click", function() {

    document.getElementById("elementtype").disabled = false;
    document.getElementById("semester").disabled = false;
    submitButton.click();
});
