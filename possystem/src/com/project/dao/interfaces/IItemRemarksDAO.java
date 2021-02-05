package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Itemremarksfunctionlist;
import com.project.model.his.Itemremarkslist;
import com.project.model.his.Productcategory;

public interface IItemRemarksDAO {

	List<Itemremarksfunctionlist> fetchItemRemarksFunctionList(Integer remarksId, Integer remarksListId, Integer branchId,
			Integer categoryId) throws Exception;

	List<Itemremarkslist> fetchItemRemarksList(Integer remarksId, Integer branchId) throws Exception;

	List<Productcategory> fetchRemarksItemCategory() throws Exception;

	boolean createRemarks(Itemremarksfunctionlist remarks);

	boolean createRemarksName(Itemremarkslist remarksNameObj);

	Itemremarksfunctionlist fetchSelectedItemRemarksList(Integer remarksId, Integer remarksListId, Integer categoryId,
			Integer branchId);

	boolean deleteRemarks(Itemremarksfunctionlist ItemRemarksFunctionList);

	boolean remarksCategoryIsExist(Integer remarksListId, int categoryId, int branchId);

	boolean createRemarksFunctionList(List<Itemremarksfunctionlist> remarksFunctionList);

	boolean deleteRemarksFunctionList(List<Itemremarksfunctionlist> remarksFunctionList);

}
