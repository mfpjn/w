$(document).ready(function () {
    var $i = 0;
        $('#login-button').click(function () {
            if ($i == 1) {
                var windowHeight = $(window).height();
                $('.overlay').css('height', '0');
                $i = 0;
            } else {
                var windowHeight = $(window).height();
                $('#login-overlay').css('height', windowHeight);
                $i = 1;
            }
        })
    $('#register-button').click(function () {
        if ($i == 1) {
            var windowHeight = $(window).height();
            $('.overlay').css('height', '0');
            $i = 0;
        } else {
            var windowHeight = $(window).height();
            $('#register-overlay').css('height', windowHeight);
            $i = 1;
        }
    })
    r
});

$(window).on('resize', function () {
    var linksWidth = 0;
    $('.footer-link').each(function(index) {
        linksWidth += parseInt($(this).width(), 10);
    });
    var footerWidth = $('#footer-container').width()-linksWidth;
    var linkSpacing = footerWidth/7;
    $('.footer-link').css('margin-right', linkSpacing);
    $('.footer-link').last().css('margin-right', '0');
}).resize();

$(window).on('load', function(){
    $('.article-tile').find('.article-name').css('visibility', 'hidden');
    $('.article-tile').mouseover( function () {
        $(this).find('.article-name').css('visibility', 'visible');
    })
    $('.article-tile').mouseleave( function () {
        $(this).find('.article-name').css('visibility', 'hidden');
    })
})