package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.ItemRemarksFunctionListModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductcategoryModel;

public interface IItemRemarksBO {

	List<ItemRemarksFunctionListModel> fetchItemRemarksFunctionList(Integer id, Integer remarksListId,  Integer branchId,Integer categoryId) throws Exception;

	List<ItemRemarksListModel> fetchItemRemarksList(Integer id, Integer branchId) throws Exception;

	List<ProductcategoryModel> fetchRemarksItemCategory() throws Exception;

	boolean createRemarks(ItemRemarksFunctionListModel remarks);

	boolean createRemarksName(String remarksName, Integer branchId);

	boolean deleteRemarks(Integer remarksId);


	boolean remarksCategoryIsExist(Integer remarksListId, int categoryId, int branchId);

	boolean createRemarksFunctionList(List<ItemRemarksFunctionListModel> remarksFunctionList);

	boolean deleteRemarksFunctionList(List<ItemRemarksFunctionListModel> remarksFunctionList);

}
