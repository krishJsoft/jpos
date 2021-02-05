package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.bo.interfaces.IItemRemarksBO;
import com.project.dao.interfaces.IItemRemarksDAO;
import com.project.dao.interfaces.IProductCategoryDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ItemRemarksFunctionListModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.his.Branch;
import com.project.model.his.ExpensesList;
import com.project.model.his.Itemremarksfunctionlist;
import com.project.model.his.Itemremarkslist;
import com.project.model.his.Productcategory;
import com.project.model.sale.sales.ExpensesListModel;

@Service("itemRemarksBO")
public class ItemRemarksBOImpl implements IItemRemarksBO {
	public static Logger log = LoggerFactory.getLogger(ItemRemarksBOImpl.class);
	
	@Resource(name="itemRemarksRespository")
	private IItemRemarksDAO itemRemarksRespository;
	

	@Override
	public List<ItemRemarksFunctionListModel> fetchItemRemarksFunctionList(Integer remarksId, Integer remarksListId, Integer branchId,
			Integer categoryId) throws Exception{
		
			List<ItemRemarksFunctionListModel> objList=new ArrayList<ItemRemarksFunctionListModel>();
		try {
			for(Itemremarksfunctionlist data:itemRemarksRespository.fetchItemRemarksFunctionList(remarksId, remarksListId, branchId, categoryId)) {
				ItemRemarksFunctionListModel obj=new ItemRemarksFunctionListModel();
				obj.setRemarksID(data.getId());
				
				ItemRemarksListModel remarksListObj=new ItemRemarksListModel(); 
				remarksListObj.setRemarksListID(data.getRemarks().getId());
				remarksListObj.setRemarksName(data.getRemarks().getRemarksName());
				obj.setRemarks(remarksListObj);
				
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				ProductcategoryModel categoryObj=new ProductcategoryModel();
				categoryObj.setCategoryId(data.getProductcategory().getCategoryId());
				categoryObj.setName(data.getProductcategory().getName());
				obj.setProductcategory(categoryObj);
				
				objList.add(obj);
			}
			
		}catch(Exception ex){
			log.info("Error in fetchItemRemarksFunctionList ItemRemarksBO "+ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public List<ItemRemarksListModel> fetchItemRemarksList(Integer id, Integer branchId) throws Exception {
		List<ItemRemarksListModel> objList=new ArrayList<ItemRemarksListModel>();
		try {
			for(Itemremarkslist data:itemRemarksRespository.fetchItemRemarksList(id, branchId)) {
				ItemRemarksListModel obj=new ItemRemarksListModel();
				obj.setRemarksListID(data.getId());
				obj.setRemarksName(data.getRemarksName());
				
			
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				objList.add(obj);
			}
			
		}catch(Exception ex){
			log.info("Error in fetchItemRemarksList ItemRemarksBO "+ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public List<ProductcategoryModel> fetchRemarksItemCategory() throws Exception {
		List<ProductcategoryModel> objList=new ArrayList<ProductcategoryModel>();
		try {
			for(Productcategory data:itemRemarksRespository.fetchRemarksItemCategory()) {
				ProductcategoryModel obj=new ProductcategoryModel();
				obj.setCategoryId(data.getCategoryId());
				obj.setName(data.getName());
			
				
				
				objList.add(obj);
			}
		}catch(Exception ex){
			log.info("Error in fetchRemarksItemCategory ItemRemarksBO "+ex);
			throw ex;
		}
		return objList;
		
	}

	@Override
	public boolean createRemarks(ItemRemarksFunctionListModel remarks) {
		boolean saveSuccess=false;
		try {
			Itemremarksfunctionlist obj=new Itemremarksfunctionlist();
			
			Itemremarkslist remarksListObj=new Itemremarkslist();
			remarksListObj.setId(remarks.getRemarks().getRemarksListID());
			obj.setRemarks(remarksListObj);
			
			Productcategory categoryObj=new Productcategory();
			categoryObj.setCategoryId(remarks.getProductcategory().getCategoryId());
			obj.setProductcategory(categoryObj);
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(remarks.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			saveSuccess=itemRemarksRespository.createRemarks(obj);
			
		}catch(Exception ex){
			log.info("Error in createRemarks ItemRemarksBO "+ex);
			throw ex;
		}
		return saveSuccess;
	}

	@Override
	public boolean createRemarksName(String remarksName, Integer branchId) {
		boolean saveSuccess=false;
		try {
			Itemremarkslist obj=new Itemremarkslist();
			obj.setRemarksName(remarksName);
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(branchId);
			obj.setBranch(branchObj);
			
			saveSuccess=itemRemarksRespository.createRemarksName(obj);
			
		}catch(Exception ex){
			log.info("Error in createRemarksName ItemRemarksBO "+ex);
			throw ex;
		}
		return saveSuccess;
	}

	@Override
	public boolean deleteRemarks(Integer remarksId) {
		boolean deleteSuccess=false;
		try {
			Itemremarksfunctionlist obj=itemRemarksRespository.fetchSelectedItemRemarksList(remarksId,null,null,null);
			deleteSuccess=itemRemarksRespository.deleteRemarks(obj);
		}catch(Exception ex){
			log.info("Error in deleteRemarks ItemRemarksBO "+ex);
			throw ex;
		}
		return deleteSuccess;
	}

	@Override
	public boolean remarksCategoryIsExist(Integer remarksListId, int categoryId,int branchId) {
		boolean exist=false;
		try {
			exist=itemRemarksRespository.remarksCategoryIsExist(remarksListId,categoryId,branchId);
		}catch(Exception ex){
			log.info("Error in remarksCategoryIsExist ItemRemarksBO "+ex);
			throw ex;
		}
		return exist;
	}

	@Override
	public boolean createRemarksFunctionList(List<ItemRemarksFunctionListModel> remarksFunctionList) {
		boolean success=false;
		try {
			List<Itemremarksfunctionlist> objList=new ArrayList<Itemremarksfunctionlist>();
			for(ItemRemarksFunctionListModel data:remarksFunctionList) {
				Itemremarksfunctionlist obj=new Itemremarksfunctionlist();
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(data.getRemarks().getRemarksListID());
				obj.setRemarks(remarksObj);
				
				Productcategory categoryObj=new Productcategory();
				categoryObj.setCategoryId(data.getProductcategory().getCategoryId());
				obj.setProductcategory(categoryObj);
				
				Branch branchObj=new Branch();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				objList.add(obj);

			}
			
			
			success=itemRemarksRespository.createRemarksFunctionList(objList);
			
		}catch(Exception ex){
			log.info("Error in createRemarksFunctionList ItemRemarksBO "+ex);
			throw ex;
		}
		return success;
	}

	@Override
	public boolean deleteRemarksFunctionList(List<ItemRemarksFunctionListModel> remarksFunctionList) {
		boolean success=false;
		try {
			List<Itemremarksfunctionlist> objList=new ArrayList<Itemremarksfunctionlist>();
			for(ItemRemarksFunctionListModel data:remarksFunctionList) {
				Itemremarksfunctionlist obj=new Itemremarksfunctionlist();
				obj.setId(data.getRemarksID());
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(data.getRemarks().getRemarksListID());
				obj.setRemarks(remarksObj);
				
				Productcategory categoryObj=new Productcategory();
				categoryObj.setCategoryId(data.getProductcategory().getCategoryId());
				obj.setProductcategory(categoryObj);
				
				Branch branchObj=new Branch();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				objList.add(obj);

			}
			
			
			success=itemRemarksRespository.deleteRemarksFunctionList(objList);
			
		}catch(Exception ex){
			log.info("Error in deleteRemarksFunctionList ItemRemarksBO "+ex);
			throw ex;
		}
		return success;
	}
	
}
