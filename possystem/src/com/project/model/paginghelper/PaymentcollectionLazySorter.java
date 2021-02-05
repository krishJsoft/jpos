package com.project.model.paginghelper;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.project.model.payment.collection.PaymentcollectionModel;
/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Jan 2014
 * 
 */

public class PaymentcollectionLazySorter implements Comparator<PaymentcollectionModel> {

	private String sortField;
	private SortOrder sortOrder;

	public PaymentcollectionLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(PaymentcollectionModel t1, PaymentcollectionModel t2) {
		try {
			Field f = PaymentcollectionModel.class.getDeclaredField(this.sortField);
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



