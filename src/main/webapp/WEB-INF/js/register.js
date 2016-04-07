(function () {
    $('#registerButton').on('click', function () {
        var username = $('#username').val(),
            password = $('#password').val(),
            email = $('#email').val(),
            user = {
                username: username,
                password: password,
                email: email
            };
        $.ajax({
            type: 'POST',
            url: '/register',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            success: function () {
                window.location = '/login?registered=true';
            },
            error: function () {
                $('#registerFail').show();
            }
        });
    });
})();