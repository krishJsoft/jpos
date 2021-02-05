function onlyNumbers(evt) {

	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
	return commonNumericKey(charCode);
	return false;
}

function submitByEnter(e) {

		
		
	if (e.keyCode == 13) {
		document.getElementById("addEditPosForm:addButton").click();
		return false;
	}
	
	else if (e.keyCode == 35) {
		document.getElementById("addEditPosForm:resetButton").click();
		return false;
	}

	else {
		return true;
	}
}

function onlyChar(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 65 && charCode <= 90)
			|| (charCode >= 97 && charCode <= 122) || charCode == 32
			|| charCode == 64 || charCode == 46 || charCode == 47
			|| charCode == 39)
		return true;
	return commonkey(charCode);
	return false;
}

function ltrim(str) {
	for ( var k = 0; k < str.length && isWhitespace(str.charAt(k)); k++)
		;
	return str.substring(k, str.length);
}
function rtrim(str) {
	for ( var j = str.length - 1; j >= 0 && isWhitespace(str.charAt(j)); j--)
		;
	return str.substring(0, j + 1);
}
function trimFun(componentId) {
	var str = document.getElementById(componentId).value;
	document.getElementById(componentId).value = ltrim(rtrim(str));
}

function alphaNumbers(evt, componentId) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57)
			|| (charCode >= 65 && charCode <= 90)
			|| (charCode >= 97 && charCode <= 122)) {
		return true;
	}
	return commonNumericKey(charCode);
	return false;
}

function mouseOutRichRow(row) {
	if (row.highlight != true) {
		row.style.backgroundColor = '#FFFFFF';
	}
}
function mouseOverRichRow(row) {
	if (row.highlight != true) {
		row.oldColor = row.style.backgroundColor;
		row.style.backgroundColor = '#FFFF8A';
	}
}

function validateLast() {

	var i = 0;
	var totalAmount = 0.0;
	var table = document.getElementById('addTreatmentForm:dataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {
		var lastval = parseFloat(document
				.getElementById('addTreatmentForm:dataTable:' + i
						+ ':testamont').value);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		totalAmount = totalAmount + lastval;
		document.getElementById('addTreatmentForm:dataTable:totalAmount').value = totalAmount;

	}

}

function invoiceAllocatedTotal() {

	var i = 0;
	var totalAmount = 0.0;
	var table = document
			.getElementById('addTreatmentCollectionForm:invoicedataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {
		var lastval = parseFloat(document
				.getElementById('addTreatmentCollectionForm:invoicedataTable:'
						+ i + ':amontinvoice').value);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		totalAmount = totalAmount + lastval;
		document
				.getElementById('addTreatmentCollectionForm:invoicedataTable:invoicereceivedtotalAmount').value = totalAmount;

	}

}

function panelAllocatedTotal() {

	var i = 0;
	var totalAmount = 0.0;
	var table = document.getElementById('addTreatmentCollectionForm:dataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {
		var lastval = parseFloat(document
				.getElementById('addTreatmentCollectionForm:dataTable:' + i
						+ ':panelreceivedamount').value);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		totalAmount = totalAmount + lastval;
		document
				.getElementById('addTreatmentCollectionForm:dataTable:panelreceivedtotalAmount').value = totalAmount;

	}

}

function validateLastPanelAmountLimit() {

	var i = 0;
	var totalAmount = 0.0;
	var count = 0;
	var panelTotalAmountLimit = parseFloat(document
			.getElementById('addTreatmentCollectionForm:collectionAmount').value);

	if (isNaN(panelTotalAmountLimit)) {
		panelTotalAmountLimit = 0;
	}

	var table = document.getElementById('addTreatmentCollectionForm:dataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {

		var bilamont = parseFloat(document
				.getElementById('addTreatmentCollectionForm:dataTable:' + i
						+ ':paneloutstandingAmount').value);

		var lastval = parseFloat(document
				.getElementById('addTreatmentCollectionForm:dataTable:' + i

				+ ':panelreceivedamount').value);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		else if (lastval > bilamont) {
			lastval = 0;
			document.getElementById('addTreatmentCollectionForm:dataTable:' + i
					+ ':panelreceivedamount').value = 0.0;
			// document.getElementById('addTreatmentCollectionForm:addTreatmentPanel:').value
			// = 'Enter Valid Amount';

		}

		totalAmount = totalAmount + lastval;

		if (panelTotalAmountLimit < totalAmount) {
			if (count == 0) {
				alert("Received Amount Total is more than Collection Amount , please adjust the Received Amount");
				count = 1;
			}

		} else {
			document
					.getElementById('addTreatmentCollectionForm:dataTable:panelreceivedtotalAmount').value = totalAmount;
		}

	}

}

function validateLastInvoiceAmountLimit() {

	var i = 0;
	var count = 0;
	var totalAmount = 0.0;

	var invoiceTotalAmountLimit = parseFloat(document
			.getElementById('addTreatmentCollectionForm:collectionAmount').value);

	if (isNaN(invoiceTotalAmountLimit)) {
		invoiceTotalAmountLimit = 0;
	}
	var table = document
			.getElementById('addTreatmentCollectionForm:invoicedataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {

		var bilamont = parseFloat(document
				.getElementById('addTreatmentCollectionForm:invoicedataTable:'
						+ i + ':invoiceoutstandingAmount').value);

		var lastval = parseFloat(document
				.getElementById('addTreatmentCollectionForm:invoicedataTable:'
						+ i

						+ ':amontinvoice').value);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		else if (lastval > bilamont) {
			lastval = 0;
			document
					.getElementById('addTreatmentCollectionForm:invoicedataTable:'
							+ i + ':amontinvoice').value = 0.0;
			// document.getElementById('addTreatmentForm:addTreatmentPanel:').value
			// = 'Enter Valid Amount';

		}

		totalAmount = totalAmount + lastval;

		if (invoiceTotalAmountLimit < totalAmount) {
			if (count == 0) {
				alert("Received Amount Total is more than Collection Amount , please adjust the Allocated Amount");
				count = 1;
			}

		} else {
			document
					.getElementById('addTreatmentCollectionForm:invoicedataTable:invoicereceivedtotalAmount').value = totalAmount;
		}

	}

}

function validateLastApportionAmountLimit() {

	var i = 0;
	var count = 0;
	var totalAmount = 0.0;
	var currentbalanceAmount = 0.0;

	var table = document
			.getElementById('listCollectionApportionForm:dataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {

		var apportionbillAmount = parseFloat(document
				.getElementById('listCollectionApportionForm:dataTable:' + i
						+ ':apportionbillAmount').value);

		// alert(apportionbillAmount);
		var apportionamountPaid = parseFloat(document
				.getElementById('listCollectionApportionForm:dataTable:' + i
						+ ':apportionamountPaid').value);

		// alert(apportionamountPaid);

		var apportionbalanceamount = parseFloat(document
				.getElementById('listCollectionApportionForm:dataTable:' + i

				+ ':apportionbalanceamount').value);

		// alert(apportionbalanceamount);

		if (isNaN(apportionbalanceamount)) {
			apportionbalanceamount = 0;
		}

		else {
			currentbalanceAmount = apportionbillAmount - apportionamountPaid;
		}

		if (apportionbalanceamount > currentbalanceAmount) {
			apportionbalanceamount = 0;
			document.getElementById('listCollectionApportionForm:dataTable:'
					+ i + ':apportionbalanceamount').value = 0.0;
		}

		totalAmount = totalAmount + apportionbalanceamount;

		document
				.getElementById('listCollectionApportionForm:dataTable:apportionbalancetotalAmount').value = totalAmount;

	}

}




function customerSummary() {

	var i = 0;
	var count = 0;
	var totalAmount = 0.0;
	var totalQuantity = 0.0;
	var normalPriceTotal = 0.0;
	var purchasePriceTotal = 0.0;	
	var marginTotal = 0.0;
	var totalQuantity = 0.0;

	var table = document.getElementById('addEditSalesForm:salesTable');
	var rows = table.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
	
	
	 
	for ( var i = 0; i < rows.length; i++) {

		 alert(documentgetElementById('addEditSalesForm:salesTable:' + i + ':totalQuantity').value);
		 totalQuantity =totalQuantity+ parseFloat(documentgetElementById('addEditSalesForm:salesTable:' + i + ':totalQuantity').value);
		
		
		 normalPriceTotal =normalPriceTotal+ parseFloat(documentgetElementById('addEditSalesForm:salesTable:' + i + ':normalPriceTotal').value);

		 purchasePriceTotal =purchasePriceTotal+ parseFloat(documentgetElementById('addEditSalesForm:salesTable:' + i + ':purchasePriceTotal').value);

		 marginTotal =marginTotal+ parseFloat(documentgetElementById('addEditSalesForm:salesTable:' + i + ':marginTotal').value);

		 margin =margin+ parseFloat(documentgetElementById('addEditSalesForm:salesTable:' + i + ':margin').value);

		 alert(totalQuantity);
		 alert(normalPriceTotal);
		 alert(purchasePriceTotal);
		 alert(marginTotal);
		 alert(margin);

	}
	 alert(totalQuantity);
	 alert(normalPriceTotal);
	 alert(purchasePriceTotal);
	 alert(marginTotal);
	 alert(margin);

	document.getElementById('addEditSalesForm:salesTable:totalQuantity1').value = totalQuantity;
	document.getElementById('addEditSalesForm:salesTable:normalPriceTotal1').value = normalPriceTotal;
	document.getElementById('addEditSalesForm:salesTable:dataTable:purchasePriceTotal1').value = purchasePriceTotal;
	document.getElementById('addEditSalesForm:salesTable:marginTotal1').value = marginTotal;
	document.getElementById('addEditSalesForm:salesTable:margin1').value = margin;
}



function keyPressOnlyAmt(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57) || charCode == 46 || charCode == 8) {
		var str = textValue.split('.');
		if (str.length == 2) {
			if (str[1].length >= 2) {
				return false;
			} else {
				return true;
			}
			return false;
		}
		return true;
	}
	return commonkey(charCode);
	return false;
}

function oneAlphaOrAllNumbers(evt, componentId) {
	var e = evt;
	var compLen = document.getElementById(componentId).value.length;
	var charCode = e.which || e.keyCode;
	if (compLen < 1) {
		if ((charCode >= 65 && charCode <= 90)
				|| (charCode >= 97 && charCode <= 122)
				|| (charCode >= 48 && charCode <= 57)) {
			return true;
		}
	} else if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
	return commonkey(charCode);
	return false;
}

function validate(evt) {
	var e = evt || window.event;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57) || charCode == 46 || charCode == 8
			|| charCode == 9) {
		return true;
	}
	return false;
}

function validateonlyNumber(evt) {
	var e = evt || window.event;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57) ||  charCode == 8
			|| charCode == 9) {
		return true;
	}
	return false;
}

function validat2e(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]|\./;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}

function enterValue(value) {

	var iamgeId = document.getElementById('barcodeForm:barinput').value;
	if (iamgeId.length <= 5) {
		document.getElementById('barcodeForm:barinput').value = iamgeId + value;
	}

}

function DisplayTime() {

	if (!document.all && !document.getElementById)
		return;
	dateElement = document.getElementById ? document.getElementById("curDate")
			: document.all.tick2;
	timeElement = document.getElementById ? document.getElementById("curTime")
			: document.all.tick2;
	dayElement = document.getElementById ? document.getElementById("curDay")
			: document.all.tick2;

	var CurrentDate = new Date();
	var date = CurrentDate.getDate();
	var month = CurrentDate.getMonth();
	month = month + 1;
	var year = CurrentDate.getFullYear();
	var dayName = "";
	var day = CurrentDate.getDay();
	if (day == 0) {
		dayName = "Sunday";
	} else if (day == 1) {
		dayName = "Monday";
	} else if (day == 2) {
		dayName = "Tuesday";
	} else if (day == 3) {
		dayName = "Wednesday";
	} else if (day == 4) {
		dayName = "Thursday";
	} else if (day == 5) {
		dayName = "Friday";
	} else if (day == 6) {
		dayName = "Saturday";
	}

	var hours = CurrentDate.getHours();
	var minutes = CurrentDate.getMinutes();
	var seconds = CurrentDate.getSeconds();
	var DayNight = "PM";
	if (hours < 12)
		DayNight = "AM";
	if (hours > 12)
		hours = hours - 12;
	if (hours == 0)
		hours = 12;
	if (minutes <= 9)
		minutes = "0" + minutes;
	if (seconds <= 9)
		seconds = "0" + seconds;
	var currentDate = " " + date + ":" + month + ":" + year;
	var currentTime = " " + hours + ":" + minutes + ":" + seconds + " "
			+ DayNight;
	var currentDay = dayName;

	dateElement.innerHTML = "<font style='font-family:verdana, arial,tahoma;font-size:17px;color:#ff5900; text-align:left; font-weight:normal;'>"
			+ currentDate + "</b>";
	timeElement.innerHTML = "<font style='font-family:verdana, arial,tahoma;font-size:17px;color:#ff5900; text-align:left; font-weight:normal;'>"
			+ currentTime + "</b>";
	dayElement.innerHTML = "<font style='font-family:verdana, arial,tahoma;font-size:17px;color:#ff5900; text-align:left; font-weight:normal;'>"
			+ currentDay + "</b>";

	setTimeout("DisplayTime()", 1000);
}
window.onload = DisplayTime;

function checkSalesReturnQuantity() {

	var i = 0;
	var totalAmount = 0.0;
	var table = document.getElementById('salesReturnForm:salesReturnDataTable');
	var rows = table.getElementsByTagName("tbody")[0]
			.getElementsByTagName("tr");
	for ( var i = 0; i < rows.length; i++) {
		var lastval = parseFloat(document
				.getElementById('salesReturnForm:salesReturnDataTable:' + i
						+ ':adjustmentquantity').value);
		// alert("lastval"+lastval);
		var soldquantity = parseFloat(document
				.getElementById('salesReturnForm:salesReturnDataTable:' + i
						+ ':soldquantity').value);
		// alert("soldquantity"+soldquantity);

		if (isNaN(lastval)) {
			lastval = 0;
		}

		else if (lastval > soldquantity) {
			lastval = 0;
			document.getElementById('salesReturnForm:salesReturnDataTable:' + i
					+ ':adjustmentquantity').value = lastval;
		}

		// document.getElementById('salesReturnForm:salesReturnDataTable:' + i +
		// ':adjustmentquantity').value = lastval;

	}

}

google.load("elements", "1", {
	packages : "transliteration"
});


function onLoad() {
	var options = {
		sourceLanguage : 'en',
		destinationLanguage : [ 'ta' ],
		shortcutKey : 'ctrl+g',
		transliterationEnabled : true
	};
	var control = new google.elements.transliteration.TransliterationControl(
			options);
	var ids = [ "transl2" ];
	control.makeTransliteratable(ids);
	control.showControl('translControl');
}
google.setOnLoadCallback(onLoad);





function maximizeme() {

	var x =screen.height;	
	//$('#mymasterpanel').css({'height': x});
	//alert(x);
}
	