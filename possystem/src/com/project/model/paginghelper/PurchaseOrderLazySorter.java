package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.project.model.datamodel.purchase.PurchaseorderModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Aug 2013
 * 
 */

public class PurchaseOrderLazySorter implements Comparator<PurchaseorderModel> {

	private String sortField;
	private SortOrder sortOrder;

	public PurchaseOrderLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(PurchaseorderModel t1, PurchaseorderModel t2) {
		try {
			Field f = PurchaseorderModel.class.getDeclaredField(this.sortField);
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
