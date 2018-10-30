function checkPassword() {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirm-password').value;

    if (password == confirmPassword) {
        document.getElementById("error-message").innerHTML = "";        
    } else {
        document.getElementById("error-message").innerHTML = "Password does not match";
    }
}

function view(tag) {
    var element = document.getElementById(tag);
    if ("none" == element.style.display) {
        document.getElementById(tag).style.display = "block";
    } else {
        document.getElementById(tag).style.display = "none";
    }
}

function validate() {
    var categories = document.getElementsByName('category');
    var releaseDate = new Date(document.getElementById('releaseDate').value);
    if ( releaseDate < new Date() ) {
        document.getElementById('dateDiv').innerHTML = "";
        for (var i=0; i<categories.length; i++) {
            if (categories[i].checked) {
                document.getElementById('categoryDiv').innerHTML = "";
                return true;
            }
        }
        document.getElementById('categoryDiv').innerHTML = "Please select any Category";
        return false;
    } else {
        document.getElementById('dateDiv').innerHTML = "Release date cannot be in Future";
        return false;
    }
}

function checkDvdIsSelected() {
    var dvds = document.getElementsByName('id');
    for (var i=0; i<dvds.length; i++) {
        if (dvds[i].checked) {
            return true;
        }
    }
    alert("Please select any DVD");
    return false;
}
