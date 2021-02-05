
/**
 * This js contains two method 
 * 1)generateReport(reportName) : Requesting to load the report content in div.
 * 2)downloadReport(format): Downloading the current report in given format.
 */


//this will hold the currently loaded report name.
currentReportName="";
	/**
	 * This method is responsible for loading the reports in the report div.
	 * @param localReportName
	 */
	function generateReport(reportName) {
	
		//	here relative url is given if relative url is not working try giving full url
		var reporturl ="/AlphaBranch/loadReport?ReportName="+reportName+"&ReportFormat=html";
		
		$("#reportData").html("Loading...<br><img src='/AlphaBranch/images/loading.gif' align='middle' >");
		
        $('#reportData').load(reporturl ,function(response, status, xhr) {
        	
          if (status == "error") {
		    var msg = "Sorry but there was an error getting details ! ";
			$("#reportData").html(msg + xhr.status + " " + xhr.statusText);
		  }
	    });
        
        currentReportName=reportName;
	}
	
	
	
	function generatePurchaseRequestListReport(reportName,referenceNo,branchId,dateFrom,dateTo,status) {
		
		alert(referenceNo);
		alert(branchId);
		alert(dateFrom);
		alert(dateTo);
		alert(status);
		
		
		//	here relative url is given if relative url is not working try giving full url
		var reporturl ="/AlphaHQ/loadReport?ReportName="+reportName+"&ReportFormat=html&referenceNo="+referenceNo+"&branchId="+branchId+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&status="+status;
		
		$("#reportData").html("Loading...<br><img src='/AlphaHQ/images/loading.gif' align='middle' >");
		
        $('#reportData').load(reporturl ,function(response, status, xhr) {
        	
          if (status == "error") {
		    var msg = "Sorry but there was an error getting details ! ";
			$("#reportData").html(msg + xhr.status + " " + xhr.statusText);
		  }
	    });
        
        currentReportName=reportName;
	}
	
	/**
	 * Download report function
	 * 
	 * @param format
	 */
	function downloadReport(format){
		//alert(format);
		if(currentReportName==""){
			//alert("Please Select the report.");
			//return;
			currentReportName="stock/hqstockList.rptdesign";
		}			
		
		//here relative url is given if relative url is not working try giving full url
		var reporturl ="/AlphaBranch/loadReport?ReportName="+currentReportName+"&ReportFormat="+format;
		window.location.href = reporturl;
		
	}
	
	
	function previewBirtReport(){		
		alert();		
        var url="http://#{facesContext.externalContext.request.serverName}:#{facesContext.externalContext.request.serverPort}"+"/AlphaBranch/report/reportViewer.jsp";
        window.open(url,'Help','top=80,left=140,width=900,height=700,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1,true');
     }
	
	
