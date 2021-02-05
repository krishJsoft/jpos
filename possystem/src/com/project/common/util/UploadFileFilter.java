package com.project.common.util;

import java.io.File;
import java.io.FileFilter;

/*
 *  @author Gopal
 * @version 1.0
 * @since 21 July 2013
 * 
 */

public class UploadFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		
		String extName=getFileExtension(pathname.getName());
		
		if ( pathname.isDirectory()  
    			|| pathname.getName().equalsIgnoreCase("Thumbs.db")    			
    			|| extName.equalsIgnoreCase("tmp") ) {
			return false;
    	} else {
    		return true;
    	}
	}
	
	
	 public  String getFileExtension(String filename)
	    {
	    if (filename == null) {
	                return null;
	    }
	    int lastUnixPos = filename.lastIndexOf('/');
	    int lastWindowsPos = filename.lastIndexOf('\\');
	    int indexOfLastSeparator = Math.max(lastUnixPos, lastWindowsPos);
	    int extensionPos = filename.lastIndexOf('.');
	    int lastSeparator = indexOfLastSeparator;
	    int indexOfExtension = lastSeparator > extensionPos ? -1 : extensionPos;
	    int index = indexOfExtension;
	    if (index == -1) {
	      return "";
	    } else {
	      return filename.substring(index + 1);
	    }
	    }    
	 
	 

}
