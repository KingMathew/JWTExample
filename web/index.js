$(document).ready(function () {
    getToken();
});

function getToken() {
    var params = {
        "username": "admin",
        "password": "123"
    };
    $.ajax({
        url: "LogIn",
        type: "POST",
        data: params
    }).done(function (response) {        
        var jsonToken = parseJwt(response);
        console.log(jsonToken);
    });
}

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}