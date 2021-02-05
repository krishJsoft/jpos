package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Batchmomenthistry;
import com.project.model.his.Productbatch;

public interface IProductbatchDAO {
	
	public boolean createProductbatch(Productbatch productbatch) throws Exception;

	public boolean updateProductbatch(Productbatch productbatch) throws Exception;

	public boolean deleteProductbatch(Productbatch productbatch) throws Exception;	
		
	public Productbatch getProductbatchDetails(Integer batchId) throws Exception;
	
	public List<Productbatch> getProductbatchDetails(String batchNo,String deliveryOrderNo , Integer productId,String status,Date dateFrom, Date dateTo,Integer branchRecordId,String productCode,Date expairyDate) throws Exception;	
	
	
	
	public boolean createBatchmomenthistry(Batchmomenthistry productbatch) throws Exception;

	public boolean updateBatchmomenthistry(Batchmomenthistry productbatch) throws Exception;

	public boolean deleteBatchmomenthistry(Batchmomenthistry productbatch) throws Exception;	
		
	public Batchmomenthistry getBatchmomenthistryDetails(Integer batchId) throws Exception;
	
	public List<Batchmomenthistry> getBatchmomenthistryDetails(String batchNo,Integer productId,String status,Integer branchRecordId,String productCode,Date expdate) throws Exception;	
	
}
