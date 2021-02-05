package com.project.bean.onlineorder;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ProductcategoryModel;

@ManagedBean(name = "datasyncBean")
@SessionScoped
public class DatasyncBean {
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();
	IProductBO productBO=objectMapController.getProductBO();	
	
	public void syncData() {
		try {
			Integer syncStatus=1;
			Boolean online=true;
			List<ProductcategoryModel> productcategorysynchlist=productCategoryBO.findByProductcategoryList2(null, "1", loginBean.getBranch().getBranchId(),online,syncStatus);
			Boolean createSuccess=false;
			if(productcategorysynchlist.size()>0) {
				List<ProductcategoryModel> onlineNewCategory=new ArrayList<ProductcategoryModel>();
				List<ProductcategoryModel> onlineUpdatedCategory=new ArrayList<ProductcategoryModel>();

				Integer level=1;
				String status="1";
			
				
				ProductcategoryModel onlineMainCategory=productCategoryBO.fetchOnlineCategoryDetails(null,null,level,status,loginBean.getBranch().getOnlineBranchId());
				for(ProductcategoryModel data:productcategorysynchlist) {
					ProductcategoryModel obj=new ProductcategoryModel();
					obj.setCode(data.getCode());
					obj.setDescription(data.getDescription());
					obj.setName(data.getName());
					obj.setParentCategoryId(onlineMainCategory.getCategoryId());
					obj.setStatus(data.getStatus());
					obj.setParentCategoryName(data.getParentCategoryName());
					obj.setLevel(data.getLevel());
					
					obj.setOnline(data.getOnline());
					obj.setSyncStatus(0);
					
					BranchModel branchObj=new BranchModel();
					branchObj.setBranchId(loginBean.getBranch().getOnlineBranchId());
					obj.setBranch(branchObj);
					
					if(data.getSyncStatus()==1) {//new category
						createSuccess=productCategoryBO.createNewOnlineProductcategory(obj);
					}else if(data.getSyncStatus()==2) { // updated category
						
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
