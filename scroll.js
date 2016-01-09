$(document).ready(function(){
    checkPosition();
    function checkPosition() {
        if ($(window).width() < 1200) {
            var sOffset = $(".sidebar").offset().top;
            $(window).scroll(function() {
                var scrollYpos = $(document).scrollTop();
                if (scrollYpos > sOffset - 60) {
                    $(".sidebar").css({
                        'top': '61px',
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
                if (scrollYpos > sOffset - 60) {
                    $(".sidebar").css({
                        'top': '61px',
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
