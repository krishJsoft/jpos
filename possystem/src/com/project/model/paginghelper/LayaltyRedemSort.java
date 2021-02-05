package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.project.model.redemption.LoyaltyredemptionbreakdownModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;

public class LayaltyRedemSort implements Comparator<LoyaltyredemptionbreakdownModel> {

	
	SortOrder sortOrder;

	@SuppressWarnings("static-access")
	public LayaltyRedemSort() {
		
		this.sortOrder = sortOrder.DESCENDING;
	}

	public int compare(LoyaltyredemptionbreakdownModel t1, LoyaltyredemptionbreakdownModel t2) {
		try {
			Field f = LoyaltyredemptionbreakdownModel.class.getDeclaredField("sno");
			f.setAccessible(true);

			Object value1 = f.get(t1);
			Object value2 = f.get(t2);

			int cmp = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? cmp : -1 * cmp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
