package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;

public class DoctorsPrescriptionsLazySorter implements Comparator<DoctorsPrescriptionsModel> {

	private String sortField;
	private SortOrder sortOrder;

	public DoctorsPrescriptionsLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(DoctorsPrescriptionsModel t1, DoctorsPrescriptionsModel t2) {
		try {
			Field f = DoctorsPrescriptionsModel.class.getDeclaredField(this.sortField);
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
