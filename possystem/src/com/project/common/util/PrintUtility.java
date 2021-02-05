package com.project.common.util;

import java.awt.print.PrinterJob;

import javax.print.PrintService;

public class PrintUtility {
	
	
	public static  PrintService findPrintService(String printerName)
    {
        for (PrintService service : PrinterJob.lookupPrintServices())
        {
            if (service.getName().equalsIgnoreCase(printerName))
                return service;
        }

        return null;
    }
	
	
}
