package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Discount;
import com.project.model.his.Discountremarks;

public interface IDiscountDAO {

	List<Discount> fetchDiscountList(int branchId) throws Exception;

	List<Discountremarks> fetchDiscountRemarksList(int branchId) throws Exception;

}
