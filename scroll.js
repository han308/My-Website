$(document).ready(function(){
    checkPosition();
    function checkPosition() {
        if ($(window).width() < 1200) {
            var sOffset = $(".sidebar").offset().top;
            $(window).scroll(function() {
                var scrollYpos = $(document).scrollTop();
                if (scrollYpos > sOffset - 80) {
                    $(".sidebar").css({
                        'top': '80px',
                        'position': 'fixed'
                    });
                } else {
                    $(".sidebar").css({
                        'top': 'auto',
                        'position': 'absolute'
                    });
                }
            });
        } else if($(window).width()>1200){
            var sOffset = $(".sidebar").offset().top;
            $(window).scroll(function() {
                var scrollYpos = $(document).scrollTop();
                if (scrollYpos > sOffset - 80) {
                    $(".sidebar").css({
                        'top': '80px',
                        'position': 'fixed'
                    });
                } else {
                    $(".sidebar").css({
                        'top': 'auto',
                        'position': 'absolute'
                    });
                }
            });
        }
    }
});
