/**
 * 
 */

$(document).ready(function(){
	
	$('.nBtn').on('click', function(event){
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		
		$('.myForm #exampleModal').modal();
		
		 
		
		
	});		
		
	
});