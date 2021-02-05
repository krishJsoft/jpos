package com.project.dao.interfaces;

import com.project.model.his.Productcategory;

public interface IProductcategoryonlineDAO {

	Productcategory fetchOnlineCategoryDetails(Integer categoryId, String name, Integer level, String status,
			Integer onlineBranchId) throws Exception;

	boolean createNewProductcategory(Productcategory categoryObj)throws Exception;

}
