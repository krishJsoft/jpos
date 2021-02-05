
function numpadKey(str){
	
	
	var inputArea = $(':focus');
	
	if(inputArea.is("input")){
		var selected=getSelectionText();
		var isHighlight=0; 
		if(selected[0]!=selected[1]){

			isHighlight=1;
		}
		var prevValue=$(inputArea).prop('value');
		
		if(str=='del'){
			if(isHighlight==1){
				var val1=prevValue.substring(0,selected[0]);
				var val2=prevValue.substring(selected[1]);
				
				newValue=val1+""+val2;
				

				newValue=0;
				
			}else{
				if(prevValue.length>0){
					newValue=prevValue.substring(0,prevValue.length-1);
				}
				
			}
			
		}
		else{
			if(isHighlight==1){
				var val1=prevValue.substring(0,selected[0]);
				var val2=prevValue.substring(selected[1]);
				newValue=val1+str+val2;
		
				console.log(newValue+ " : "+str);
			}else{
				newValue=prevValue+str;
				
			}
		}
		if(newValue==0 || newValue=="" || newValue=="."){
			newValue=1;
		}
		$(inputArea).val("");
		$(inputArea).val(newValue);
		
		
	}

}
$(document).ready(function() {

	$('#numpadKey button').on('mousedown', 
	    /** @param {!jQuery.Event} event */ 
	    function(event) {
		alert('asd');
	        event.preventDefault();
	    }
	);
});