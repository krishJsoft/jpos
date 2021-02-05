package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.project.model.sale.sales.branchsale.BranchsaleModel;


public class BranchsaleSorter implements Comparator<BranchsaleModel>{

	private String sortField;
	private SortOrder sortOrder;

	public BranchsaleSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}
	
	@Override
	public int compare(BranchsaleModel t1, BranchsaleModel t2) {
		try {
			Field f = BranchsaleModel.class.getDeclaredField(this.sortField);
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
