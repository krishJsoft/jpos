package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.ISalesorderDAO;
import com.project.dao.interfaces.ISalesorderonlineDAO;
import com.project.model.his.Quotation;
import com.project.util.StringConstants;

@Repository("salesOrderOnlineRepository")
public class SalesorderonlineDAO implements ISalesorderonlineDAO{
	
	public static Logger log = LoggerFactory.getLogger(SalesorderonlineDAO.class);
	long l = 0;
	private EntityManager em = null;
	
	@PersistenceContext(unitName="onlinedb")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	
}
