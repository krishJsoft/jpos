package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.project.model.sale.sales.SalesorderbreakdownModel;


public class PosSalesSorder implements Comparator<SalesorderbreakdownModel> {

	
	SortOrder sortOrder;

	@SuppressWarnings("static-access")
	public PosSalesSorder() {
		
		this.sortOrder = sortOrder.DESCENDING;
	}

	public int compare(SalesorderbreakdownModel t1, SalesorderbreakdownModel t2) {
		try {
			Field f = SalesorderbreakdownModel.class.getDeclaredField("sno");
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


