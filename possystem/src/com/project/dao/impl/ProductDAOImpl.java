package com.project.dao.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IProductDAO;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Productbundle;
import com.project.model.his.Productprice;
import com.project.model.his.Productsetlist;
import com.project.model.his.Productsupplier;
import com.project.model.his.Supplier;
import com.project.util.ConvertUtil;
import com.project.util.StringConstants;
import com.google.common.primitives.Ints;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 10 July 2013
 * 
 */

@Repository("productRepository")
public class ProductDAOImpl implements IProductDAO {
	
	public static Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
	long l = 0;
	private EntityManager em = null;
	
	 
	@PersistenceContext(unitName=StringConstants.HIS_SERVER_PERSISTENCE_NAME)
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProductAll() throws Exception {		
		List<Product> data = null;
		try {
			String hslQuery = "from Product p ";
			
			//String hslQuery = "SELECT p from Product p  inner join fetch p.productsuppliers ps inner join fetch ps.supplier inner join fetch p.productcategory  ";

			Query query = em.createQuery(hslQuery);
			 data = query.getResultList();			
		} catch (Exception e) {
			log.info("Error in findByProductAll ProductDAOImpl " + e);
			throw e;
		}
		return data;
	}
	/*
	public List<Productbranchlink> getCustomProductList(Integer branchId) throws Exception{
		List<Productbranchlink> data = null;
		try {
			String hslQuery = "from Productbranchlink pb  inner join fetch pb.product p WHERE pb.branch.branchId=:branchId AND p.isCustomList=1";
			
			Query query = em.createQuery(hslQuery);
			//query.setParameter("branchId", branchId);
			 data = query.getResultList();			
		} catch (Exception e) {
			log.info("Error in findByProductAll ProductDAOImpl " + e);
			throw e;
		}
		return data;
	}
*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findProduct() throws Exception {		
		List<Product> data = null;
		List<Object[]> result=null;
		try {
			String hslQuery = "select p.productId,p.barcode,p.productName,p.uom from Product p ";
			Query query = em.createQuery(hslQuery);
			result = query.getResultList();			
		} catch (Exception e) {
			log.info("Error in findByProductAll ProductDAOImpl " + e);
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbranchlink> findByProductListAll(Integer branchId) throws Exception {		
		List<Productbranchlink> data = null;
		try {
			String hslQuery = "from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc where pb.quantityonHand<pb.quantityonalert and pb.branch.branchId=:branchId";
			Query query = em.createQuery(hslQuery).setParameter("branchId", branchId);
			 data = query.getResultList();			
		} catch (Exception e) {
			log.info("Error in findByProductbranchlinkListAll ProductDAOImpl " + e);
			throw e;
		}
		return data;
	}
	
	
	
	@Override
	public int getProductCount(List<Integer> categoryId, Integer supplierId,String barcode, String status,Integer branchId,String productName,Integer quantityonHand,String brandName,String salesType) throws Exception {
		int countResult = 0;
		int[] retList = null;
		try {
			
			StringBuilder buf = new StringBuilder(
					"SELECT COUNT(p) FROM Productbranchlink p   WHERE 1 = 1 ");

			
			if (categoryId !=null && categoryId.size() !=0) {
				buf.append("  and p.product.productcategory.categoryId in (:categoryId) ");
			}
			
		
			if (supplierId !=null && supplierId !=0) 
			{
				//buf.append(" AND p.productsuppliers.supplier.supplierId =  :supplierId  ");
			}			
			if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.product.barcode =:barcode ");
			}
			
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.product.salesType =:salesType ");
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.product.status =:status ");
			}
			
			if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
			{
				buf.append(" AND  p.product.productName like :productName ");
			}
			if (branchId !=null && branchId !=0) 
			{
				buf.append(" AND  p.branch.branchId =:branchId ");
			}	
			if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.product.brandName like :brandName ");
			}
			if (quantityonHand !=null && quantityonHand !=0) 
			{
				buf.append(" AND  p.quantityonHand<p.quantityonalert ");
			}	
			
			
			
			Query query = em.createQuery(buf.toString());

			if (categoryId !=null && categoryId.size()!=0) 
			{
				query.setParameter("categoryId", categoryId);
			}			
			if (supplierId !=null && supplierId !=0) 
			{
				//query.setParameter("supplierId", supplierId);
			}
			if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				query.setParameter("barcode", barcode);
			}	
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesType", salesType);
			}	
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}
			if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
			{
				query.setParameter("productName", productName+"%");
			}
			if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
			{
				query.setParameter("brandName", brandName+"%");	
			}	
			
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}				
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			

			//List<Integer> retData = query.getResultList();
			//retList = Ints.toArray(retData);		
			
			
			}
		
			catch (Exception e) {
			log.info("Error in getProductbranchlinkCount of ProductDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}
	
	
	@Override
	public List<Productbranchlink> getProductListReport(
			List<Integer> categoryId, Integer supplierId, String barcode,
			String status, Integer branchId, String productName,String brandName)
			throws Exception {
		List<Productbranchlink> data = null;
		
		try {			
		StringBuilder buf = new StringBuilder(
				"SELECT pb from Productbranchlink pb  inner join "
				+ "fetch pb.product p "
				+ "inner join fetch "
				+ "p.productcategory pc WHERE 1 = 1 "
				+ " AND  p.online !=1 ");
		
		if (categoryId !=null && categoryId.size() !=0) {
			buf.append("  and pc.categoryId  in (:categoryId) ");
		}
					
		if (supplierId !=null && supplierId !=0) 
		{
			//buf.append(" AND s.supplierId =  :supplierId  ");
		}	
		if (branchId !=null && branchId !=0) 
		{
			buf.append(" AND pb.branch.branchId =  :branchId  ");
		}	
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.barcode =:barcode ");
		}
		if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.brandName like :brandName ");
		}
		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
		{
			buf.append(" AND  p.productName like :productName ");
		}
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.status =:status ");
		}			
		buf.append("ORDER BY p.productName");		
		Query query = em.createQuery(buf.toString());

		if (categoryId !=null && categoryId.size()!=0) 
		{
			query.setParameter("categoryId", categoryId);
		}			
		if (supplierId !=null && supplierId !=0) 
		{
			//query.setParameter("supplierId", supplierId);
		}
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}	
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			query.setParameter("barcode", barcode);
		}	
		if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
		{
			query.setParameter("brandName", brandName+"%");	
		}	
		
		
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
		{
			query.setParameter("productName", productName+"%");
		}
		
		data = query.getResultList();
		
		
		}
	
		catch (Exception e) {
		log.info("Error in getProductbranchlinkList of ProductDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbranchlink> getProductList(int[] ids,List<Integer> categoryId, Integer supplierId,
			String barcode, String status, int start, int howMany,Integer branchId,String productName,Integer quantityonHand,String brandName,String salesType)
			throws Exception {
			List<Productbranchlink> data = null;
		

			try {			
			StringBuilder buf = new StringBuilder(
					"SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc WHERE 1 = 1 "
					+ " ");
			
			if (categoryId !=null && categoryId.size() !=0) {
				buf.append("  and pc.categoryId  in (:categoryId) ");
			}
						
			if (supplierId !=null && supplierId !=0) 
			{
				//buf.append(" AND s.supplierId =  :supplierId  ");
			}	
			if (branchId !=null && branchId !=0) 
			{
				buf.append(" AND pb.branch.branchId =  :branchId  ");
			}	
			

			if (ids !=null && ids.length !=0) {
				buf.append("  and pb.productBranchLinkId in (:ids) ");
			}
			
			if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.barcode =:barcode ");
			}
			
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.salesType =:salesType ");
			}
			
			if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
			{
				buf.append(" AND  p.productName like :productName ");
			}
			if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.brandName like :brandName ");
			}
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.status =:status ");
			}			
			if (quantityonHand !=null && quantityonHand !=0) 
			{
				buf.append(" AND  pb.quantityonHand<pb.quantityonalert ");
			}	
			
			Query query = em.createQuery(buf.toString());

			if (categoryId !=null && categoryId.size()!=0) 
			{
				query.setParameter("categoryId", categoryId);
			}			
			if (supplierId !=null && supplierId !=0) 
			{
				//query.setParameter("supplierId", supplierId);
			}
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}	
			if (ids !=null && ids.length !=0) {
				query.setParameter("ids", ConvertUtil.getIntegerList(ids));
			}
			if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				query.setParameter("barcode", barcode);
			}	
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesType", salesType);
			}	
			if (brandName != null && brandName != "" && (!brandName.equalsIgnoreCase("0")) && (!brandName.equalsIgnoreCase(""))) 
			{
				query.setParameter("brandName", brandName+"%");	
			}	
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}		
			if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase("")))  
			{
				query.setParameter("productName", "%"+productName+"%");
			}
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			data = query.getResultList();
			
			//setPrice();
			//setAddProduct();
			//setAddProductPrice();
			}
		
			catch (Exception e) {
			log.info("Error in getProductbranchlinkList of ProductDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}

	
	
	
	@Override
	public List<Productbranchlink> getProductListUsingLikeProductCode(String productCode,Integer branchId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Productbranchlink getProductDetails(int productId,Integer branchId) throws Exception {
		Productbranchlink retData = null;
		String sqlQuery = " SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc    WHERE p.productId = :productId and pb.branch.branchId=:branchId ";
		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getSingleResult();
		} catch (Exception e) {
			log.info("Error in getProductDetails ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	/*@Override
	public Productbranchlink getProductDetails(int productId,Integer branchId) throws Exception {
		Productbranchlink retData = null;
		String sqlQuery = " SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc  inner join fetch p.productsuppliers ps join fetch ps.supplier   WHERE p.productId = :productId and pb.branch.branchId=:branchId ";
		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getSingleResult();
		} catch (Exception e) {
			log.info("Error in getProductDetails ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	*/

	
	@Override
	public Productbranchlink getProductPriceDetails(int productId,Integer branchId) throws Exception {
		Productbranchlink retData = null;
		String sqlQuery = "SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc inner join fetch p.productprices ps   WHERE p.productId = :productId and pb.branch.branchId=:branchId ";
		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductPriceDetails ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productprice> getSortedProductpriceByBarcode(String barCode,Integer branchId)
			throws Exception {
		List<Productprice> retData = null;
		String sqlQuery = "select pp from Productprice pp inner join fetch pp.product WHERE pp.barCode = :barCode and pp.branch.branchId=:branchId order by pp.quantityFrom asc";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("barCode", barCode).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			log.info("Error in getSortedProductpriceByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Productprice> getSortedProductpriceBarcode(int productId,Integer branchId)
			throws Exception {
		List<Productprice> retData = null;
		String sqlQuery = "select pp from Productprice pp  WHERE pp.product.productId = :productId and pp.branch.branchId=:branchId order by pp.quantityFrom asc";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			log.info("Error in getSortedProductpriceByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbundle> getProductbundleList(int productId,Integer branchId)	throws Exception {
		List<Productbundle> retData = null;
		String sqlQuery = "select pp from Productbundle pp  inner join fetch pp.product p WHERE pp.productm.productId = :productId and pp.branch.branchId=:branchId ";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			log.info("Error in getProductbundleList ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	@Override
	public boolean createNewProduct(Product Product) throws Exception {
		try {
			em.persist(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProduct ProductDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateProduct(Product Product) throws Exception {
		try {
			em.merge(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProduct ProductDAOImpl " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean deleteProduct(Product Product) throws Exception {
		try {
			em.remove(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProduct ProductDAOImpl " + e);
			throw e;
		}
	}

	
	@Transactional
	@Override
	public boolean deleteProductbranchlink(Productbranchlink Product) throws Exception {
		try {
			em.remove(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProduct ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	@Override
	public boolean createNewProductbranchlink(Productbranchlink productbranchlink) throws Exception {
		try {
			em.persist(productbranchlink);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProductbranchlink ProductDAOImpl " + e);
			throw e;
		}
	}
	
	@Override
	public boolean createNewProductprice(Productprice productprice) throws Exception {
		try {
			em.persist(productprice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProductprice ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public boolean createNewProductsupplier(Productsupplier productsupplier) throws Exception {
		try {
			if(productsupplier.getProductsupplierId()!=0)
			{
			em.merge(productsupplier);			
			}
			else
			{
			em.persist(productsupplier);
			}			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProductsupplier ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Productbranchlink getProductDetailsByBarcode(String barcode,Integer branchId) throws Exception {
		Productbranchlink retData = null;
		//String sqlQuery = "select t from Product t inner join fetch t.productsuppliers ps join fetch ps.supplier inner join fetch t.productcategory   WHERE t.barcode = :barcode ";
		String sqlQuery = "SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productsuppliers ps inner join fetch ps.supplier inner join fetch p.productcategory  WHERE p.barcode = :barcode and pb.branch.branchId=:branchId ";

		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("barcode", barcode).setParameter("branchId", branchId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductDetailsByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findProductExites(String productName) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Product p where p.productName = :productName";
			Query query = em.createQuery(hslQuery).setParameter("productName", productName);
			@SuppressWarnings("unchecked")
			List<Product> productNameExits = query.getResultList();
			if (productNameExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findProductExites ProductDAOImpl " + e);
			throw e;
		}
		return exits;
	}

	@Override
	public boolean findbarcodeExites(String barcode) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Product p where p.barcode = :barcode";
			Query query = em.createQuery(hslQuery).setParameter("barcode", barcode);
			@SuppressWarnings("unchecked")
			List<Product> barcodeExits = query.getResultList();
			if (barcodeExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findbarcodeExites ProductDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbranchlink> getProductList(String barcode,String productName,Integer branchId) throws Exception {
		List<Productbranchlink> data = null;
		try {			
		StringBuilder buf = new StringBuilder("SELECT pb FROM Productbranchlink pb inner join fetch pb.product p inner join fetch p.productcategory inner join fetch p.productprices ps WHERE   1 = 1 ");		

		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.barcode like :barcode ");
		}
		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.productName like :productName ");
		}
		if (branchId !=null && branchId !=0) 
		{
			buf.append(" AND pb.branch.branchId =  :branchId  ");
		}	
		
		Query query = em.createQuery(buf.toString());
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			query.setParameter("barcode", barcode+"%");	
		}	
		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase(""))) 
		{
			query.setParameter("productName", productName+"%");	
		}	
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);	
		}	
	
				
		data = query.getResultList();
		}
	
		catch (Exception e) {
		log.info("Error in getProductList By String of ProductDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbranchlink> getProductBranchStockList(String barcode,String productName,Integer branchId) throws Exception {
		List<Productbranchlink> data = null;
		try {			
		StringBuilder buf = new StringBuilder("SELECT pb FROM Productbranchlink pb inner join fetch pb.product p inner join fetch pb.branch br inner join fetch p.productcategory  WHERE   1 = 1 AND pb.branch.branchId!=1 ");		
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.barcode = :barcode ");
		}
		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  p.productName = :productName ");
		}
		if (branchId !=null && branchId !=0) 
		{
			buf.append(" AND pb.branch.branchId =  :branchId  ");
		}	
		
		Query query = em.createQuery(buf.toString());
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			query.setParameter("barcode", barcode);	
		}	
		
		if (productName != null && productName != "" && (!productName.equalsIgnoreCase("0")) && (!productName.equalsIgnoreCase(""))) 
		{
			query.setParameter("productName", productName);	
		}	
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);	
		}	
		
				
		data = query.getResultList();
		}
	
		catch (Exception e) {
		log.info("Error in getProductList By String of ProductDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productsupplier> getProductSuppliersByBarcode(String barcode,Integer productId) throws Exception {
		List<Productsupplier> retData = null;
		
		StringBuilder buf = new StringBuilder("select ps from Productsupplier ps join fetch ps.supplier inner join fetch ps.product p WHERE   1 = 1 and ps.supplier.supplierId!=1" );	
		try {
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.barcode =:barcode ");
			}
		
		if (productId != null && productId != 0 ) 
		{
			buf.append(" AND  p.productId =:productId ");
		}
		
		buf.append(" order by  ps.purchasePrice asc ");
		
		
		Query query = em.createQuery(buf.toString());
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			query.setParameter("barcode", barcode);	
		}	
		
		if (productId != null && productId != 0 ) 
		{
			query.setParameter("productId", productId);	
		}
		retData = query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in getProductDetailsByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productsupplier> getProductPriceSuppliers(String barcode,Integer productId,Integer supplierId) throws Exception {
		List<Productsupplier> retData = null;
		
		StringBuilder buf = new StringBuilder("select ps from Productsupplier ps join fetch ps.supplier s inner join fetch ps.product p WHERE   1 = 1 ");	
		try {
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.barcode =:barcode ");
			}
		
		if (productId != null && productId != 0 ) 
		{
			buf.append(" AND  p.productId =:productId ");
		}
		if (supplierId != null && supplierId != 0 ) 
		{
			buf.append(" AND  s.supplierId =:supplierId ");
		}
		
		Query query = em.createQuery(buf.toString());
		
		if (barcode != null && barcode != "" && (!barcode.equalsIgnoreCase("0")) && (!barcode.equalsIgnoreCase(""))) 
		{
			query.setParameter("barcode", barcode);	
		}	
		
		if (productId != null && productId != 0 ) 
		{
			query.setParameter("productId", productId);	
		}
		if (supplierId != null && supplierId != 0 ) 
		{
			query.setParameter("supplierId", supplierId);	
		}
		
		retData = query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in getProductDetailsByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productprice> getProductpriceByBarcode(String barCode,Integer branchId)
			throws Exception {
		List<Productprice> retData = null;
		String sqlQuery = "select pp from Productprice pp inner join fetch pp.product WHERE pp.barCode = :barCode and pp.branch.branchId=:branchId ";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("barCode", barCode).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			log.info("Error in getProductpriceByBarcode ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Transactional
	@Override
	public boolean deleteProductPrice(Productprice productprice)
			throws Exception {
		try {
			em.remove(productprice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProductPrice ProductDAOImpl " + e);
			throw e;
		}
	}
	@Override
	public boolean updateProductPrice(Productprice productprice) throws Exception {
		try {
			em.merge(productprice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductPrice ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Productprice getProductPriceDetailsbyId(int productPriceId) throws Exception {
		Productprice retData = null;
		String sqlQuery = "select t from Productprice t inner join fetch t.product    WHERE t.productPriceId = :productPriceId ";
		try {
			retData = (Productprice) em.createQuery(sqlQuery).setParameter("productPriceId", productPriceId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductPriceDetailsbyId ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Transactional
	@Override
	public boolean deleteProductsupplier(Productsupplier productsupplier)
			throws Exception {
		try {
			em.remove(productsupplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProductsupplier ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Productsupplier getProductSupplierDetailsbyId(Integer productsupplierId) throws Exception {
		Productsupplier retData = null;
		String sqlQuery = "select t from Productsupplier t  WHERE t.productsupplierId = :productsupplierId ";
		try {
			retData = (Productsupplier) em.createQuery(sqlQuery).setParameter("productsupplierId", productsupplierId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductSupplierDetailsbyId ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	
	
	@Override
	public boolean deleteProductbunlde(Productbundle productbundle)	throws Exception {
		try {
			em.remove(productbundle);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProductbunlde ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Productbundle getProductbunldeDetailsbyId(Integer productbundleId) throws Exception {
		Productbundle retData = null;
		String sqlQuery = "select t from Productbundle t  WHERE t.productbundleId = :productbundleId ";
		try {
			retData = (Productbundle) em.createQuery(sqlQuery).setParameter("productbundleId", productbundleId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductbunldeDetailsbyId ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	@Transactional
	public void setPrice()
	{
		List<Product> data = null;
		try {
			String hslQuery = "from Product p ";
			Query query = em.createQuery(hslQuery);
			 data = query.getResultList();		
			 
			 for(Product p:data)
			 {				 
				   String hslQuery1 = "from Productsupplier ps where ps.product.productId=:productId ";
				  // Query query1 = em.createQuery(hslQuery1);
				   List<Productsupplier> s =  em.createQuery(hslQuery1).setParameter("productId", p.getProductId()).getResultList();
				   for(Productsupplier supplierObj:s)
					 {				 	
					//supplierObj.setPurchasePrice(p.getPurchasePrice());
					em.merge(supplierObj);
					 }
				
			 }
		} catch (Exception e) {
			log.info("Error in findByProductListAll ProductDAOImpl " + e);
			
		}
		
	}
	
	
	
	@Transactional
	public void setAddProduct()
	{
		List<Product> data = null;
		List<Branch> branchdata = null;
		try {
			
			 String hslbranchQuery = "from Branch b ";
			 Query branchquery = em.createQuery(hslbranchQuery);
			 branchdata = branchquery.getResultList();		
			 
			 
			 
			String hslQuery = "from Product p ";
			Query query = em.createQuery(hslQuery);
			 data = query.getResultList();		
			 
			 
			 for(Branch b:branchdata)
			 {			 
			 for(Product p:data) {
				  
				Productbranchlink pbl = new Productbranchlink();
				//pbl.setBatchNo(p.getBatchNo());
				
				pbl.setBranch(b);
				
				/*pbl.setDoctorPrice(p.getDoctorPrice());
				pbl.setFullredemptionpoint(p.getFullredemptionpoint());
				pbl.setHalfredemptionpoint(p.getHalfredemptionpoint());
				pbl.setNormalPrice(p.getNormalPrice());
				
				pbl.setProduct(p);
				
				pbl.setPurchasePrice(p.getPurchasePrice());
				pbl.setQuantityonalert(p.getQuantityonalert());
				pbl.setQuantityonHand(p.getQuantityonHand());
				pbl.setRedemAmount(p.getRedemAmount());
				pbl.setSalesrepPrice(p.getSalesrepPrice());*/
								
			   	em.persist(pbl);
				
			 }
			 }
		} catch (Exception e) {
			log.info("Error in findByProductListAll ProductDAOImpl " + e);
			
		}
		
	}
	
	
	

	@Transactional
	public void setAddProductPrice()
	{
		List<Product> data = null;
		List<Branch> branchdata = null;
		try {
			
			 String hslbranchQuery = "from Branch b ";
			 Query branchquery = em.createQuery(hslbranchQuery);
			 branchdata = branchquery.getResultList();				 
			 
			String hslQuery = "from Product p ";
			Query query = em.createQuery(hslQuery);
			 data = query.getResultList();	
			 
			 for(Branch b:branchdata)
			 {			 
				 if(b.getBranchId()!=1)
				 {
				 for(Product product:data) {
				  
			        Productprice newPrice = new Productprice();
					newPrice.setDiscountAmount(new BigDecimal(0.00));					
					newPrice.setQuantityFrom(new BigDecimal(1.00));					
					newPrice.setUom(product.getUom());	
					newPrice.setCreatedBy(product.getCreatedBy());
					newPrice.setLastModifiedDate(new Date());
					newPrice.setBarCode(product.getBarcode());
					newPrice.setTotalQuantity(new BigDecimal(0.00));
					newPrice.setProduct(product);
					newPrice.setBranch(b);								
					em.persist(newPrice);
				 }
				
			 }
			 }
		} catch (Exception e) {
			log.info("Error in findByProductListAll ProductDAOImpl " + e);
			
		}
		
	}
	
	
	@Override
	public boolean updateProductbranchlink(Productbranchlink productbranchlink)
			throws Exception {
		try {
			em.merge(productbranchlink);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductbranchlink ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Productbranchlink getProductbranchlinkDetails(Integer productBranchLinkId)
			throws Exception {
		Productbranchlink retData = null;		
		String sqlQuery = "SELECT pb from Productbranchlink pb  WHERE pb.productBranchLinkId=:productBranchLinkId ";
		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("productBranchLinkId", productBranchLinkId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductbranchlinkDetails ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	@Override
	public Productbranchlink getProductbranchlinkMasterDetails(int productId,Integer branchId) throws Exception {
		Productbranchlink retData = null;		
		String sqlQuery = "SELECT pb FROM Productbranchlink pb inner join fetch pb.product p  WHERE p.productId=:productId and pb.branch.branchId=:branchId ";
		try {
			retData = (Productbranchlink) em.createQuery(sqlQuery).setParameter("productId", productId).setParameter("branchId", branchId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductbranchlinkMasterDetails ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Transactional
	public void addProductIntoQuickList(List<Integer> pID) throws Exception{
		try {
			
			StringBuilder buf = new StringBuilder("UPDATE Product p SET p.isCustomList =1 ");
		for(int i=0;i<pID.size();i++) {
				if(i==0)
					buf.append("WHERE p.productId=:productId"+i+" ");
				else {
					buf.append("OR p.productId=:productId"+i+" ");	
				}
							
			}
		
			Query query = em.createQuery(buf.toString());
			
			
			for(int i=0;i<pID.size();i++) {
				query.setParameter("productId"+i,pID.get(i));
			}
			
			int result = query.executeUpdate();
			
				
		} catch (Exception ex) {
			log.info("Error in addProductIntoQuickList ProductDAOImpl " + ex);
			throw ex;
		}
	}
	
	@Transactional
	public void removeProductFromQuickList(List<Integer> pID) throws Exception{
try {
			
			StringBuilder buf = new StringBuilder("UPDATE Product p SET p.isCustomList =0 ");
		for(int i=0;i<pID.size();i++) {
				if(i==0)
					buf.append("WHERE p.productId=:productId"+i+" ");
				else {
					buf.append("OR p.productId=:productId"+i+" ");	
				}
							
			}
		
			Query query = em.createQuery(buf.toString());
			
			
			for(int i=0;i<pID.size();i++) {
				query.setParameter("productId"+i,pID.get(i));
			}
			
			int result = query.executeUpdate();
			
		} catch (Exception ex) {
			log.info("Error in removeProductFromQuickList ProductDAOImpl " + ex);
			throw ex;
		}
	}
	@Override
	public List<Productbranchlink> getHotListProduct(Integer branchId) {
		List<Productbranchlink> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"SELECT pb from Productbranchlink pb  inner join fetch pb.product p inner join fetch p.productcategory pc WHERE pb.branch.branchId =  :branchId AND p.isCustomList=1 ");
		
		
		buf.append("ORDER BY p.productName ");	
		Query query = em.createQuery(buf.toString());

		query.setParameter("branchId", branchId);
		
		
		data = query.getResultList();
		
		
		}
	
		catch (Exception e) {
		log.info("Error in getHotListProduct of ProductDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	@Override
	public List<Productsetlist> getProductSetList(Integer productId) throws Exception {
		List<Productsetlist> retData = null;
		String sqlQuery = "SELECT ps FROM Productsetlist ps "
				+ "INNER JOIN FETCH ps.product p "
				+ "INNER JOIN FETCH p.productcategory pc "
				+ "WHERE ps.productset.productId = :productId ";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("productId", productId).getResultList();
		} catch (Exception e) {
			log.info("Error in getProductSetList ProductDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Transactional
	@Override
	public boolean deleteSetItemList(Productsetlist productsetModel) throws Exception {
		try {
			em.remove(productsetModel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSetItemList ProductDAOImpl " + e);
			throw e;
		}
	}
	
	
}

