/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.print;

import java.awt.Color;
import java.io.*;
import java.net.*;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.sql.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;

import com.project.bean.sales.sale.PosBean;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.factory.BeanContext;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author admin
 */
public class PrintKot extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * 
     * 
     */
	
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,ISalesorderBO salesOrderBO)
            throws ServletException, IOException {        
        try {
        	
        	String salesOrderId="0";
        	 SalesorderModel salesOrder = new SalesorderModel();
        	if(request.getParameter("salesOrderId")!=null)
        	{
        	salesOrderId= request.getParameter("salesOrderId").toString();        	
        	
        	salesOrder = salesOrderBO.getSalesorderPosDetails(Integer.parseInt(salesOrderId), "3");
        	//salesOrder = salesOrderBO.getSalesorderPosDetails(70, "3");
        	
        	List<SalesorderModel> dataList = new ArrayList<SalesorderModel>();
        	dataList.add(salesOrder);
        	Map parameters = new HashMap();
			parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            ServletContext context = this.getServletConfig().getServletContext();
           
            
           // String reportPath = context.getRealPath("/report/posBill.jasper");
            //JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataList.get(0).getSalesorderbreakdowns());
            
            String reportPath = context.getRealPath("/report/newBillReport.jasper");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
            
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            if (jasperPrint != null) {
                response.setContentType("application/octet-stream");
                ServletOutputStream ouputStream = response.getOutputStream();

                ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
                oos.writeObject(jasperPrint);
                oos.flush();
                oos.close();

                ouputStream.flush();
                ouputStream.close();
                response.flushBuffer();



            }

            }


            //con.close();
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.write(e.toString());
            out.close();

        }

    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	HttpSession httpSession = httpRequest.getSession();
      
        
        WebApplicationContext springContext =WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ISalesorderBO salesOrderBO =(ISalesorderBO)springContext.getBean("salesOrderBO");
       // PosBean posBean = (PosBean) springContext.getBean("posBean");
        processRequest(request, response,salesOrderBO);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
    	 

         WebApplicationContext springContext =WebApplicationContextUtils.getWebApplicationContext(getServletContext());
         ISalesorderBO salesOrderBO =(ISalesorderBO)springContext.getBean("salesOrderBO");
         //PosBean posBean = (PosBean) springContext.getBean("posBean");
        processRequest(request, response,salesOrderBO);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
