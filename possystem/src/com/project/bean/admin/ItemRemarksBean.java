package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.bo.interfaces.IItemRemarksBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ItemRemarksFunctionListModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductcategoryModel;


@ManagedBean(name = "itemRemarksBean")
@SessionScoped
public class ItemRemarksBean {

	private String remarksName;

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	//ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");


	private ItemRemarksFunctionListModel remarksFunction=new ItemRemarksFunctionListModel();
	private Integer remarksListId;
	private Integer categoryId;
	private Boolean buttonIsClicked=false;
	
	private List<ItemRemarksListModel> selectedRemarksList=new ArrayList<ItemRemarksListModel>();
	private List<ItemRemarksFunctionListModel> unSelectedRemarksList=new ArrayList<ItemRemarksFunctionListModel>();
	
	List<SelectItem> productUnAsssignedRemarksList;
	List<SelectItem> productAsssignedRemarksList;
	
	private List<ItemRemarksFunctionListModel> assignedRemarksList=new ArrayList<ItemRemarksFunctionListModel>();
	
	private List<ItemRemarksListModel> unAssignedRemarksList=new ArrayList<ItemRemarksListModel>();

	
	private List<ItemRemarksListModel> allRemarksList=new ArrayList<ItemRemarksListModel>();


	private List<ItemRemarksFunctionListModel> remarksList=new ArrayList<ItemRemarksFunctionListModel>();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IItemRemarksBO itemRemarksBO=objectMapController.getItemRemarksBO();
	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");


	public String getRemarksName() {
		return remarksName;
	}

	public void setRemarksName(String remarksName) {
		this.remarksName = remarksName;
		
	}

	public Boolean getButtonIsClicked() {
		return buttonIsClicked;
	}

	public void setButtonIsClicked(Boolean buttonIsClicked) {
		this.buttonIsClicked = buttonIsClicked;
	}

	public Integer getRemarksListId() {
		return remarksListId;
	}

	public void setRemarksListId(Integer remarksListId) {
		this.remarksListId = remarksListId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public ItemRemarksFunctionListModel getRemarksFunction() {
		return remarksFunction;
	}

	public void setRemarksFunction(ItemRemarksFunctionListModel remarksFunction) {
		this.remarksFunction = remarksFunction;
	}

	public List<ItemRemarksFunctionListModel> getRemarksList() {
		return remarksList;
	}

	public void setRemarksList(List<ItemRemarksFunctionListModel> remarksList) {
		this.remarksList = remarksList;
	}

	public List<ItemRemarksListModel> getSelectedRemarksList() {
		return selectedRemarksList;
	}

	public void setSelectedRemarksList(List<ItemRemarksListModel> selectedRemarksList) {
		this.selectedRemarksList = selectedRemarksList;
	}

	public List<ItemRemarksFunctionListModel> getUnSelectedRemarksList() {
		return unSelectedRemarksList;
	}

	public void setUnSelectedRemarksList(List<ItemRemarksFunctionListModel> unSelectedRemarksList) {
		this.unSelectedRemarksList = unSelectedRemarksList;
	}

	public List<SelectItem> getProductUnAsssignedRemarksList() {
		return productUnAsssignedRemarksList;
	}

	public void setProductUnAsssignedRemarksList(List<SelectItem> productUnAsssignedRemarksList) {
		this.productUnAsssignedRemarksList = productUnAsssignedRemarksList;
	}

	public List<SelectItem> getProductAsssignedRemarksList() {
		return productAsssignedRemarksList;
	}

	public void setProductAsssignedRemarksList(List<SelectItem> productAsssignedRemarksList) {
		this.productAsssignedRemarksList = productAsssignedRemarksList;
	}

	

	public List<ItemRemarksFunctionListModel> getAssignedRemarksList() {
		return assignedRemarksList;
	}

	public void setAssignedRemarksList(List<ItemRemarksFunctionListModel> assignedRemarksList) {
		this.assignedRemarksList = assignedRemarksList;
	}

	public List<ItemRemarksListModel> getUnAssignedRemarksList() {
		return unAssignedRemarksList;
	}

	public void setUnAssignedRemarksList(List<ItemRemarksListModel> unAssignedRemarksList) {
		this.unAssignedRemarksList = unAssignedRemarksList;
	}

	public List<ItemRemarksListModel> getAllRemarksList() {
		return allRemarksList;
	}

	public void setAllRemarksList(List<ItemRemarksListModel> allRemarksList) {
		this.allRemarksList = allRemarksList;
	}

	public void changeButtonStatus() {
		if(this.getButtonIsClicked()==false) {
			this.setButtonIsClicked(true);
		}else {
			this.setButtonIsClicked(false);
		}
	}
	
	public void createNewRemarksName() {
		try{
			boolean valid=true;
			if(factoryBean.checkIsNullAssignMessage(this.getRemarksName(), "itemRemarks.label.newName", "remarkNewName")) {
				valid=false;
			}
			if(valid) {
				boolean saveSuccess=itemRemarksBO.createRemarksName(this.getRemarksName(),loginBean.getBranch().getBranchId());
				if(saveSuccess) {
					this.setRemarksName(null);
					this.setButtonIsClicked(false);
					CommonListBeanInfo.getItemRemarksLIst();
					
				}
			}
			this.loadAllRemarksList();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void getRemarksListAll() {
		try{
			this.remarksList=itemRemarksBO.fetchItemRemarksFunctionList(null, null, loginBean.getBranch().getBranchId(), null);
		}catch(Exception ex) {
			
		}
	}
	
	public void createRemarks() {
		try{
			boolean saveSuccess=false;
			BranchModel branch=new BranchModel();
			branch.setBranchId(loginBean.getBranch().getBranchId());
			remarksFunction.setBranch(branch);
			
			ItemRemarksListModel itemRemarksList=new ItemRemarksListModel(); 
			itemRemarksList.setRemarksListID(this.getRemarksListId());
			remarksFunction.setRemarks(itemRemarksList);
			
			ProductcategoryModel productCategory=new ProductcategoryModel();
			
			
			List<Integer> validCatId=productCategoryBO.getValidCategoryIdWithProducts();
			for(Integer catId:validCatId) {
				ProductcategoryModel obj=productCategoryBO.getProductcategoryDetails(catId);
				ProductcategoryModel validObj=productCategoryBO.getProductcategoryDetails(catId);

			/*	if(obj.getLevel()==3) {
					ProductcategoryModel parentObj=productCategoryBO.getProductcategoryDetails(obj.getParentCategoryId());
					if(parentObj.getCategoryId()==this.categoryId) {
						productCategory.setCategoryId(obj.getParentCategoryId());
						remarksFunction.setProductcategory(productCategory);
						saveSuccess=itemRemarksBO.createRemarks(remarksFunction);
					}					
				}*/
				
				while(obj.getLevel()>3) {
					obj=productCategoryBO.getProductcategoryDetails(obj.getParentCategoryId());
				
					/*if(obj.getLevel()==3) {
						productCategory.setCategoryId(validObj.getParentCategoryId());
						remarksFunction.setProductcategory(productCategory);
						saveSuccess=itemRemarksBO.createRemarks(remarksFunction);
					}
					*/
				}
				
				
				if(obj.getLevel()==3) {
					ProductcategoryModel parentObj=productCategoryBO.getProductcategoryDetails(obj.getParentCategoryId());
					if(parentObj.getCategoryId()==this.categoryId) {
						if(!itemRemarksBO.remarksCategoryIsExist(this.getRemarksListId(),validObj.getParentCategoryId(),loginBean.getBranch().getBranchId())) {
							productCategory.setCategoryId(validObj.getParentCategoryId());
							remarksFunction.setProductcategory(productCategory);
							saveSuccess=itemRemarksBO.createRemarks(remarksFunction);
						}
						
					}					
				}
			}
			
			if(saveSuccess) {
				getRemarksListAll();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadAllRemarksList() {
		try {
			this.allRemarksList=itemRemarksBO.fetchItemRemarksList(null,loginBean.getBranch().getBranchId());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

	
	public void loadAndSorttPickList() {
		try {
			unAssignedRemarksList=new ArrayList();
			for(ItemRemarksListModel data: allRemarksList) {
				unAssignedRemarksList.add(data);
			}
			assignedRemarksList=new ArrayList();
			//productAsssignedRemarksList=null;
			//productUnAsssignedRemarksList=null;
			//List<SelectItem> unassignedRemarksList;
			List<ItemRemarksListModel> listToREmove=new ArrayList<ItemRemarksListModel>();
			
			assignedRemarksList= itemRemarksBO.fetchItemRemarksFunctionList(null, null, loginBean.getBranch().getBranchId(), this.getCategoryId());
			
			if(assignedRemarksList.size()>0) {
		//		List<Integer> indexToRemove=new ArrayList<Integer>();
				for(int i=0;i<unAssignedRemarksList.size();i++) {
					for(int k=0;k<assignedRemarksList.size();k++) {

						if(assignedRemarksList.get(k).getRemarks().getRemarksListID()==unAssignedRemarksList.get(i).getRemarksListID()) {
							listToREmove.add(unAssignedRemarksList.get(i));
						//	indexToRemove.add(i);
							break;
						}
					}
				}
				for(ItemRemarksListModel data:listToREmove) {
					unAssignedRemarksList.remove(data);
				}
				//for(int i=0;i<indexToRemove.size();i++) {
				//	unAssignedRemarksList.remove(indexToRemove.get(i).intValue());
				//}
			}
			
		
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadRemarksPickList(ActionEvent event) {
		this.categoryId=(Integer) event.getComponent().getAttributes().get("categoryId");
		loadAndSorttPickList();
	}
	
	public void updatePickList(Object pickList1,Object pickList2) {

		
	}
	
	public void addItemToPickList(){
		try {
			
				boolean success=false;
				List<ItemRemarksFunctionListModel> objList=new ArrayList<ItemRemarksFunctionListModel>();
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(loginBean.getBranch().getBranchId());
				
				ProductcategoryModel categoryObj=new ProductcategoryModel();
				categoryObj.setCategoryId(this.getCategoryId());
				
				for(ItemRemarksListModel data:selectedRemarksList) {
				
					ItemRemarksFunctionListModel obj=new ItemRemarksFunctionListModel();	

					ItemRemarksListModel itemRemarksList=new ItemRemarksListModel(); 
					itemRemarksList.setRemarksListID(data.getRemarksListID());
					itemRemarksList.setRemarksName(data.getRemarksName());
					
					obj.setRemarks(itemRemarksList);
					obj.setBranch(branchObj);
					
					obj.setProductcategory(categoryObj);
					
					objList.add(obj);	
					
				}
				this.selectedRemarksList.clear();
				success=itemRemarksBO.createRemarksFunctionList(objList);
				loadAndSorttPickList();

		}catch(Exception ex) {
        	ex.printStackTrace();
		}
	}
	
	public void removeItemFromPickList() {
		try {
			boolean success=false;
			List<ItemRemarksFunctionListModel> objList=new ArrayList<ItemRemarksFunctionListModel>();
			
			BranchModel branchObj=new BranchModel();
			branchObj.setBranchId(loginBean.getBranch().getBranchId());
			
			ProductcategoryModel categoryObj=new ProductcategoryModel();
			categoryObj.setCategoryId(this.getCategoryId());
			
			for(ItemRemarksFunctionListModel data:unSelectedRemarksList) {
			
				ItemRemarksFunctionListModel obj=new ItemRemarksFunctionListModel();	
				
				ItemRemarksListModel itemRemarksList=new ItemRemarksListModel(); 
				itemRemarksList.setRemarksListID(data.getRemarks().getRemarksListID());
				itemRemarksList.setRemarksName(data.getRemarks().getRemarksName());
				
				obj.setRemarksID(data.getRemarksID());
				
				obj.setRemarks(itemRemarksList);
				obj.setBranch(branchObj);
				
				obj.setProductcategory(categoryObj);
				
				objList.add(obj);	
				
			}
			this.unSelectedRemarksList.clear();;
			success=itemRemarksBO.deleteRemarksFunctionList(objList);
			loadAndSorttPickList();

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void removeRemarks(ActionEvent event) {
		try {

			Integer remarksId = (Integer) event.getComponent().getAttributes()
					.get("remarksItemId");
			
			boolean deleteSuccess=itemRemarksBO.deleteRemarks(remarksId);
			if(deleteSuccess) {
				getRemarksListAll();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
