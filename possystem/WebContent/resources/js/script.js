var sec = 0;
var min = "0" + 0;
var t;
var charLen = 0;
document.onkeypress = stopKey;

function popup(url, windowName, width, height) {
	var features = 'width=' + width + ', height=' + height
			+ ',scrollbars=yes,resizable=yes,status=no';
	window.open(url, windowName, features);
}

function printBill() {
	var location = document.getElementById('location').value;
	var billDate = document.getElementById('billDate').value;
	var referenceNo = document.getElementById('referenceNo').value;
	var mobileNo = document.getElementById('mobileNo').value;
	var paymentType = document.getElementById('paymentType').value;
	var customerName = document.getElementById('customerName').value;
	var invoiceAmount = document.getElementById('invoiceAmount').value;
	var csrName = document.getElementById('csrName').value;
	var creditCardNo = document.getElementById('creditCardNo').value;
	var chequeNo = document.getElementById('chequeNo').value;
	var bankName = document.getElementById('bankName').value;
	document.getElementById('BillPrint').printBill(location, billDate,
			referenceNo, mobileNo, paymentType, creditCardNo, chequeNo,
			bankName, customerName, invoiceAmount, csrName);
	window.close();
}

function printingBill() {
	var printData = document.getElementById('printData').value;
	// alert(printData);
	document.getElementById('BillPrint').printBill(printData);
	window.close();
}

function stopKey(evt) {
	// alert('hi');
	var evt = ((evt) ? evt : null);
	if (evt != null) {
		var node = (evt.target) ? evt.target
				: ((evt.srcElement) ? evt.srcElement : null);
		if ((evt.keyCode == 8) && (node.type != 'text')
				&& (node.type != 'textarea')) {
			return false;
		}
	}
}

function onlyAlpha(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 65 && charCode <= 90)
			|| (charCode >= 97 && charCode <= 122))
		return true;
	return commonkey(charCode);
	return false;
}

function nameValueField(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	// var keycode = e.keyCode;
	// alert(keycode);
	if ((charCode >= 65 && charCode <= 90)
			|| (charCode >= 97 && charCode <= 122) || charCode == 32
			|| charCode == 64 || charCode == 46 || charCode == 47
			|| charCode == 39)
		return true;
	return commonkey(charCode);
	return false;
}

// First two character Alpha or number other's only number..
function twoAlphaNumbers(evt, componentId) {
	var e = evt;
	var compLen = document.getElementById(componentId).value.length;
	var charCode = e.which || e.keyCode;
	if (compLen <= 1) {
		if ((charCode >= 65 && charCode <= 90)
				|| (charCode >= 97 && charCode <= 122)) {
			return true;
		}
	} else if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
	return commonkey(charCode);
	return false;
}

// Field allow only two Alpha only other's number..
function fieldAllowTwoAlphaOnly(evt, componentId) {
	var e = evt;
	var compLen = document.getElementById(componentId).value;
	var charCode = e.which || e.keyCode;
	var lastVal = compLen.charAt(compLen.length - 1);

	if (compLen == 0) {
		charLen = 0;
	}

	if (charCode == 8 || charCode == 9) {
		if (isNaN(lastVal))
			charLen = charLen - 1;
		return true;
	}

	if (((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122))
			&& charLen < 2) {
		charLen = charLen + 1;
		return true;
	}

	if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
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

function onlyAlphaNumbers(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	// alert(charCode);
	if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
	if ((charCode >= 97 && charCode <= 122)) {
		return true;
	}
	if ((charCode >= 65 && charCode <= 90)) {
		return true;
	}
	return commonNumericKey(charCode);
	return false;
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

function onlyNumbers(evt) {
	var e = evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57)) {
		return true;
	}
	return commonNumericKey(charCode);
	return false;
}

function keyPressOnlyAmt(evt, componentId) {
	// alert(evt);
	var e = evt;
	var charCode = e.which || e.keyCode;
	var textValue = document.getElementById(componentId).value;
	// alert(textValue);
	if ((charCode >= 48 && charCode <= 57) || charCode == 46) {
		var str = textValue.split('.');
		// alert(str[1]+" : "+str.length);

		if (str.length == 2) {
			// alert(" length : "+str[1].length)
			if (str[1].length >= 2) {
				// alert(str.length);
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

function changeAmountFormat(componentId) {
	var textValue = document.getElementById(componentId).value;
	var str = textValue.split('.');
	if (str.length == 2) {
		// alert(" length : "+str[1].length)
		if (str[1].length >= 2) {
			str = str[0] + '.' + str[1];
		} else {
			str = str[0] + '.' + str[1] + '0';
		}
	} else {
		str = str + '.00';
	}
	document.getElementById(componentId).value = str
}

// default usage methods.... shankar
function commonkey(charCode) {
	// alert(charCode); || charCode == 39 || charCode == 37
	if (charCode == 8 || charCode == 46 || charCode == 9) {
		return true;
	}
	return false;
}

function commonNumericKey(charCode) {
	if (charCode == 8 || charCode == 9) {
		return true;
	}
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
function isWhitespace(charToCheck) {
	var whitespaceChars = " \t\n\r\f";
	return (whitespaceChars.indexOf(charToCheck) != -1);
}

function radioButton(radio) {
	var id = radio.name.substring(radio.name.lastIndexOf(':'));
	var el = radio.form.elements;
	for ( var i = 0; i < el.length; i++) {
		if (el[i].name.substring(el[i].name.lastIndexOf(':')) == id) {
			el[i].checked = false;
		}
	}
	radio.checked = true;
}

function convertUpperCase(componentId) {
	var txt;
	txt = document.getElementById(componentId).value;
	if (txt != null && txt != '') {
		document.getElementById(componentId).value = txt.toUpperCase();
	}
}

function convertAmount(txtValue) {
	var val = parseInt(txtValue);
	return val + '.00';
}

function onlyAmount(evt) {
	// alert("shamkar");
	var e = event || evt;
	var charCode = e.which || e.keyCode;
	if ((charCode >= 48 && charCode <= 57) || charCode == 46) {
		return true;
	}

	return false;
}

function validateLast(html, row) {
	var lastval = document.getElementById('addTreatmentForm:dataTable:' + row
			+ ':sunp1').value;
	alert("html is " + html + " and row is " + row + " and lastval is "
			+ lastval);
}

function enterValue(value) {

	var iamgeId = document.getElementById('barcodeForm:barinput').value;
	if (iamgeId.length <= 5) {
		document.getElementById('barcodeForm:barinput').value = iamgeId + value;
	}

}
