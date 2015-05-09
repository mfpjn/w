$(document).ready(function () {
    $('#login-button').click(function () {
        var windowHeight = $(window).height();
        $('#login-overlay').css('height', windowHeight)
    })
});

