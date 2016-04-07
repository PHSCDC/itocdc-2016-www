(function () {
    $('#post-comment-button').on('click', function () {
        var author = $('#username').val(),
            commentText = $('#comment').val(),
            comment = {
                comment: commentText,
                author: author
            };
        $.ajax({
            type: 'POST',
            url: '/addcomment',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(comment),
            success: function () {
                $('#comment').val('');
                window.location.reload();
            },
            error: function (error) {
                alert('There was an error while trying to save your comment.');
            }
        });
    });
})();