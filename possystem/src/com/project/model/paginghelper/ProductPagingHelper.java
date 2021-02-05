package com.project.model.paginghelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IProductBO;
import com.project.model.datamodel.ProductModel;

public class ProductPagingHelper extends LazyDataModel<ProductModel> {

	private static final long serialVersionUID = 1L;
	private List<ProductModel> productObj;

	List<Integer> categoryId;
	Integer supplierId;
	String barcode;
	String status;
	IProductBO productBO;
	Integer totalCount = 0;
	Integer branchRecordId;
	String productName;
	String brandName;
	int[] searchResultIDList;
	Integer quantityonHand;
	String salesType;

	public ProductPagingHelper(List<Integer> categoryId, Integer supplierId,
			String barcode, String status, IProductBO productBO,Integer branchRecordId,String productName,Integer quantityonHand,String brandName,String salesType) {
		super();

		this.categoryId = categoryId;
		this.supplierId = supplierId;
		this.barcode = barcode;
		this.status = status;
		this.productBO = productBO;
		this.branchRecordId=branchRecordId;
		this.productName=productName;
		this.quantityonHand=quantityonHand;
		this.brandName=brandName;
		this.salesType=salesType;
		setRowCount(totalCount);
	}
	
	
	@Override
	public List<ProductModel> load(int start, int howMany,String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
		try {			
			productObj = productBO.getProductList(null,categoryId, supplierId, barcode, status, start, howMany,branchRecordId,productName,quantityonHand,brandName,salesType);	
			
			if (getRowCount() <= 0) {
				totalCount = productBO.getProductCount(categoryId, supplierId, barcode, status,branchRecordId,productName,quantityonHand,brandName,salesType);
				setRowCount(totalCount);
			}	
			
			if(sortField != null) {  			     
			  Collections.sort(productObj, new ProductLazySorter(sortField, sortOrder));    
			    }    			
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = productBO.getProductCount(categoryId, supplierId, barcode, status,branchRecordId,productName);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			productObj = productBO.getProductList(searchResultIDList,null, null, null, null, start, howMany,branchRecordId,null);	
			if (sortField != null) {
				Collections.sort(productObj, new ProductLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productObj;
	}

	@Override
	public Object getRowKey(ProductModel productdata) {
		return productdata.getProductId();
	}

	@Override
	public ProductModel getRowData(String productId) {
		Integer id = Integer.valueOf(productId);

		for (ProductModel product : productObj) {
			if (id.equals(product.getProductId())) {
				return product;
			}
		}

		return null;
	}
	
	
    

}
