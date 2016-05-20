$(document).ready(function(){
    if ($(window).width() < 1200) {
        $("#socialButton").empty();

        var html = "<div class='col-xs-12'>";
        html += "<ul>"
        html += "<li><a class='btn btn-lg btn-social-icon btn-github' href = 'https://github.com/han308' target='_blank'><span class='fa fa-github'></span></a></li>";
        html += "<li><a class='btn btn-lg btn-social-icon btn-instagram' href = 'https://www.instagram.com/xu_han_jack/' target='_blank'><span class='fa fa-instagram'></span></a></li>";
        html += "<li><a class='btn btn-lg btn-social-icon btn-linkedin' href = 'https://www.linkedin.com/in/xu-jack-han-a87194a4'_blank'><span class='fa fa-linkedin'></span></a></li>"
        html += "</ul>";
        html += "</div>"
        $("#socialButton").append(html);

    } 
/*
    else if($(window).width()>1200){

        var html = '<div class="col-xs-4 col-xs-offset-2">';
        html += "<ul>";
        html += '<li><a class="btn btn-lg btn-block btn-social btn-github" href = "https://github.com/han308" target="_blank"><span class="fa fa-github"></span> Find me on GitHub</a></li>';
        html += '<li><a class="btn btn-lg btn-block btn-social btn-instagram" href = "https://www.instagram.com/xu_han_jack/" target="_blank"><span class="fa fa-instagram"></span> Follow me on Instagram</a></li>';
        html += '</ul>';
        html += '</div>';
        html += '<div class="col-xs-4">';
        html += '<ul><li><a class="btn btn-lg btn-block btn-social btn-linkedin" href = "https://www.linkedin.com/in/xu-jack-han-a87194a4" target="_blank"><span class="fa fa-linkedin"></span> Connect me on LinkedIn</a></li></ul>';
        html += '</div>';
    }
    */
});
