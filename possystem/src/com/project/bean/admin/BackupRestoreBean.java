package com.project.bean.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.project.bo.interfaces.ICommonListBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.util.Backup;
import com.project.common.util.UploadFileFilter;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 08 Aug 2014
 * 
 */

@ManagedBean(name = "backupRestoreBean")
@SessionScoped
public class BackupRestoreBean {

	
	@ManagedProperty("#{commonListBO}")
	private ICommonListBO commonListBO; // injected Spring defined service

	public ICommonListBO getCommonListBO() {
		return commonListBO;
	}

	public void setCommonListBO(ICommonListBO commonListBO) {
		this.commonListBO = commonListBO;
	}	
	
	private StreamedContent file;

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	
	public String getBackup()
	{
		String mysqlpath="";
		String anyParam="";
		Process run = null;
		Configuration config = Configuration.getConfiguration();
		String hostname = config.getValue(IConfiguration.hostname);
		String port = config.getValue(IConfiguration.port);
		String user = config.getValue(IConfiguration.user);
		String password = config.getValue(IConfiguration.password);
		String db = config.getValue(IConfiguration.db);
		String URL  = "jdbc:mysql://"+hostname+":"+port+"/";
		Backup b = new Backup();
		UploadFileFilter fileFilter = new UploadFileFilter();
		try
		{
			//mysqlpath=commonListBO.getDatabasePath(anyParam);
			//mysqlpath = mysqlpath + "bin\\";
			
			//System.out.println(mysqlpath);
          //  run = Runtime.getRuntime().exec(mysqlpath + "mysqldump --host=" + host + " --port=" + port + " --user=" + user + " --password=" + password + "  " + "--skip-comments --skip-triggers " + db);
			   String backuppath= this.getServeletContx().getRealPath("")	+ File.separator + "backup" + File.separator;
			   
			  // backuppath="c:\\backup";
			   byte[] data = b.getData(hostname, port, user,   password, db,URL).getBytes();
		       File filedst = new File(backuppath+"\\"+db+".zip");
		       FileOutputStream dest = new FileOutputStream(filedst);
		       ZipOutputStream zip = new ZipOutputStream(
		       new BufferedOutputStream(dest));
		       zip.setMethod(ZipOutputStream.DEFLATED);
		       zip.setLevel(Deflater.BEST_COMPRESSION);
		       zip.putNextEntry(new ZipEntry("data.sql"));
		       zip.write(data);
		       zip.close();
		       dest.close();
		       
		   	
	    		String documentName =db;	    		
	    		InputStream stream =  new FileInputStream(filedst);
	            file = new DefaultStreamedContent(stream, fileFilter.getFileExtension(filedst.getName()), documentName); 
		}
		catch(Exception e)
		{
			
		}
		
		return mysqlpath;
	}
	
	
	public void databaseBackup()
	{
		getBackup();
	}
	
	public ServletContext getServeletContx()
	{
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext(); 	

		return servletContext;
	}
	
	
}
