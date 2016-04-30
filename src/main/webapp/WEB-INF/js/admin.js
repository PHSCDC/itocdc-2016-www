(function () {
    $('#changePassword').on('click', function () {
        var username = $('#pass-username').val(),
            password = $('#password').val(),
            user = {
                username: username,
                password: password
            };
        $.ajax({
            type: 'POST',
            url: '/changepassadmin',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            success: function () {
                $('#pass-username').val('');
                $('#password').val('');
                alert('Password changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change the user\'s password');
            }
        });
    });

    $('#changeEmail').on('click', function () {
        var username = $('#email-username').val(),
            email = $('#email').val(),
            user = {
                username: username,
                password: '',
                email: email
            };
        $.ajax({
            type: 'POST',
            url: '/changeemailadmin',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            success: function () {
                $('#email-username').val('');
                $('#email').val('');
                alert('Email changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change the user\'s email');
            }
        });
    });

    $('#change-download').on('click', function () {
        var link = $('#new-download-link').val();
        $.ajax({
            type: 'get',
            url: '/change_download_link',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {to: link},
            success: function () {
                $('#new-download-link').val('');
                alert('Download link changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change the download link');
            }
        });
    });
})();
