(function () {
    $('#changePassword').on('click', function () {
        var username = $('#username').val(),
            password = $('#password').val(),
            user = {
                username: username,
                password: password
            };
        $.ajax({
            type: 'POST',
            url: '/changepass',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            success: function () {
                $('#password').val('');
                alert('Password changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change your password');
            }
        });
    });

    $('#changeEmail').on('click', function () {
        var username = $('#username').val(),
            email = $('#email').val(),
            user = {
                username: username,
                password: '',
                email: email
            };
        $.ajax({
            type: 'POST',
            url: '/changeemail',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            success: function () {
                $('#email').val('');
                alert('Email changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change your email');
            }
        });
    });
})();