$(document).ready(function(){
			$("#profile").addClass("active");
	$(window).scroll(function() {
		var profileOff = $("#introduction").offset().top - $(window).scrollTop();
	    if(profileOff < 80){
	        $("#edu").removeClass("active");
	        $("#job").removeClass("active");
	        $("#project").removeClass("active");
	        $("#profile").addClass("active");
	    }
		var educationOff = $("#education").offset().top - $(window).scrollTop();
	    if(educationOff < 80){
	        $("#profile").removeClass("active");
	        $("#job").removeClass("active");
	        $("#project").removeClass("active");
	        $("#edu").addClass("active");
	    }
	    var jobOff = $("#work").offset().top - $(window).scrollTop();
	    if(jobOff < 80){
	        $("#profile").removeClass("active");
	        $("#edu").removeClass("active");
	        $("#project").removeClass("active");
	        $("#job").addClass("active");
	    }
	    var projectOff = $("#projects").offset().top -  ($(window).scrollTop() + $(window).height());
	    console.log($("#projects").offset().top);
	    if(projectOff == -752){
	        $("#profile").removeClass("active");
	        $("#edu").removeClass("active");
	        $("#job").removeClass("active");
	        $("#project").addClass("active");
	    }

	});
});