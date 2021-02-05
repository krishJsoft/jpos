package com.project.bo.interfaces;

import java.util.List;

import com.project.model.sale.sales.DiscountModel;
import com.project.model.sale.sales.DiscountremarksModel;

public interface IDiscountBO {

	List<DiscountModel> fetchDiscountList(int branchId) throws Exception;

	List<DiscountremarksModel> fetchDiscountRemarksList(int branchId) throws Exception;

}
