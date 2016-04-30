(function () {
    $('#changePassword').on('click', function () {
		var currentPassword = $('#currentPassword').val(),
            newPassword = $('#password').val();
        var changePassword = {
			currentPassword : currentPassword,
            newPassword : newPassword
        };
        $.ajax({
            type: 'POST',
            url: '/changepass',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(changePassword),
            success: function () {
				$('#currentPassword').val('');
                $('#password').val('');
                alert('Password changed successfully!');
            },
            error: function (error) {
                alert('There was an error while trying to change your password');
            }
        });
    });

    $('#changeEmail').on('click', function () {
        var email = $('#email').val();
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
