$(window).load(function(){
    $(".jumbotron").css("visibility", "visible");
    var slideCount = $('.jumbotron .container ul li img').length;
    var slideWidth = $('.jumbotron .container ul li img').width();
    var slideHeight = $('.jumbotron .container ul li').height();
    var sliderUlWidth = slideCount * slideWidth;

    $('.jumbotron .container').css({ width: slideWidth, height: slideHeight });
    
    $('.jumbotron .container ul').css({ width: sliderUlWidth, height: slideHeight, marginLeft: - slideWidth });
    
    $('.jumbotron .container ul li:last-child').prependTo('.jumbotron .container ul');
    function moveLeft() {
        $('.jumbotron .container ul').animate({
            left: slideWidth
        }, 500, function () {
            $('.jumbotron .container ul li:last-child').prependTo('.jumbotron .container ul');
            $('.jumbotron .container ul').css('left', '');
        });
    };

function moveRight() {
    $('.jumbotron .container ul').animate({
        left: -slideWidth
    }, 500, function () {
            $('.jumbotron .container ul li:first-child').appendTo('.jumbotron .container ul');
            $('.jumbotron .container ul').css('left', '');
    });
};

$('a.control_prev').click(function () {
    moveLeft();
});

$('a.control_next').click(function () {
    moveRight();
});

});  