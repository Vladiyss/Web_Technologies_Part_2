function isEmpty(value)
{
    var val = value.trim();
    if (val.length == 0) {
        return false;
    }
    return true;
}

function checkForm() {
    var allFilled = document.getElementById("ru").checked || document.getElementById("by").checked;
    allFilled = allFilled && isEmpty(document.getElementById("certificate").value);
    allFilled = allFilled && isEmpty(document.getElementById("state_lan").value);
    if (allFilled) {
        document.getElementById("submit").disabled = 0;
    }
    else {
        document.getElementById("submit").disabled = 1;
    }
}