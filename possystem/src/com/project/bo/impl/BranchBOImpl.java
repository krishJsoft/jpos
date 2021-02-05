package com.project.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.bo.interfaces.IBranchBO;
import com.project.bo.util.EmailProcess;
import com.project.dao.interfaces.IBranchDAO;
import com.project.dao.interfaces.ICommonListDAO;
import com.project.dao.interfaces.IProductCategoryDAO;
import com.project.dao.interfaces.IProductDAO;
import com.project.dao.interfaces.IRoleDAO;
import com.project.dao.interfaces.IStaffDAO;
import com.project.dao.interfaces.RoleFunctionLinkDAO;
import com.project.modal.report.ReturnvalueModal;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.his.Autonum;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Department;
import com.project.model.his.Function;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Productcategory;
import com.project.model.his.Productprice;
import com.project.model.his.Productsupplier;
import com.project.model.his.Role;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Taxmaster;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

@Service("branchBO")
public class BranchBOImpl implements IBranchBO {
	
	public static Logger log = LoggerFactory.getLogger(BranchBOImpl.class);

	@Resource(name = "branchRepository")
	private IBranchDAO branchRepository;
	
	@Resource(name = "RoleRepository")
	private IRoleDAO RoleRepository;
	
	@Resource(name = "RoleFunctionLinkRepository")
	private RoleFunctionLinkDAO RoleFunctionRepository;
	
	@Resource(name = "commonListRepository")
	private ICommonListDAO commonListRepository;
	
	@Resource(name = "staffRepository")
	private IStaffDAO staffRepository;
	
	@Resource(name = "productRepository")
	private IProductDAO productRepository;
	
	@Resource(name = "productCategoryRepository")
	private IProductCategoryDAO productCategoryRepository;
	
	@Transactional
	@Override
	public boolean createNewBranch(BranchModel branch) throws Exception {
		boolean saveSuccess = false;
		Branch branchObj = new Branch();
		
		List<Productsupplier> supplierList = new ArrayList<Productsupplier>();
		List<Productprice> newPriceList  = new ArrayList<Productprice>(); 
		List<Productbranchlink> productbranchlinks= new ArrayList<Productbranchlink>(); 
		List<ProductpriceModel> pricelist = new ArrayList<ProductpriceModel>();
		
		try {
			
			
			branchObj.setBranchCode(branch.getBranchCode());			
			branchObj.setBranchName(branch.getBranchName().toUpperCase());			
			
			branchObj.setCreatedBy(branch.getCreatedBy());
			branchObj.setCreatedDate(branch.getCreatedDate());
			branchObj.setEmailAddress(branch.getEmailAddress());
			branchObj.setFaxNo(branch.getFaxNo());
			branchObj.setFixedLicenseFee(branch.getFixedLicenseFee());
			branchObj.setFranchiseeFee(branch.getFranchiseeFee());
			branchObj.setIsHQ(branch.getIsHQ());
			branchObj.setLastModifiedDate(branch.getLastModifiedDate());
			branchObj.setAddress(branch.getAddress().toUpperCase());
			branchObj.setCity(branch.getCity().toUpperCase());
			branchObj.setCountry(branch.getCountry().toUpperCase());
			branchObj.setPhoneNo(branch.getPhoneNo());
			branchObj.setPostCode(branch.getPostCode());
			branchObj.setState(branch.getState());
			branchObj.setBranchtype("0");
			branchObj.setStatus(branch.getStatus());
			branchObj.setContactPerson(branch.getContactPerson());
			branchObj.setCustomersalesPoint(branch.getCustomersalesPoint());
			branchObj.setCustomersalesValue(branch.getCustomersalesValue());
			branchObj.setRedemPoint(branch.getRedemPoint());
			branchObj.setRedemValue(branch.getRedemValue());
			branchObj.setTotalPurchaseTax(new BigDecimal(0.00));
			branchObj.setTotalSalesTax(new BigDecimal(0.00));
			
			ReturnvalueModal returnVal=new ReturnvalueModal();
			
			returnVal = branchRepository.createNewBranch(branchObj);	
			if(returnVal.isSuccess())
			{
				// 1 Create New Role
				
				Role role = new Role();				
				role.setRoleName("Administrator");
				role.setRoleDescription("Administrator");
				role.setCreatedDate(new Date());
				role.setCreatedBy("");
				role.setCreatedDate(new Date());				
				role.setBranch(branchObj);
				RoleRepository.save(role);				
				
				// 2 Create Role Privileges				
				
				List<Function> privilages = RoleRepository.findbyRoleModuleListAll();
				for (Function priv : privilages) {
					
					Rolefunctionlink roleFunctionslink = new Rolefunctionlink();
					Function function = new Function();
					function.setFunctionId(priv.getFunctionId());
					roleFunctionslink.setFunction(function);
					roleFunctionslink.setRole(role);
					RoleFunctionRepository.save(roleFunctionslink);
				}
				
				// 3 Create New Department
				
				Department dptModel = new Department();					
				dptModel.setDepartmentName("Admin");		
				dptModel.setDescription("Administrator");
				dptModel.setBranch(branchObj);
				commonListRepository.saveDepartment(dptModel);
				
				// 4 Create New Administrator User
				
				Branchstaffmember staffObj = new Branchstaffmember();				
				staffObj.setDesignation("Administrator");
				staffObj.setEmailAddress(branch.getEmailAddress());
				staffObj.setForceReset("1");
				staffObj.setInvalidAttempts(0);
				staffObj.setStatus("1");
				staffObj.setCommission(String.valueOf(false));
				staffObj.setFirstName("Admin");
				staffObj.setStaffCode(returnVal.getPrimaryId()+"_admin");
				//staffObj.setPassword(branch.getBranchuserpassword());
				staffObj.setPassword("admin");
				
				staffObj.setBranch(branchObj);
				staffObj.setRole(role);
				staffObj.setDepartment(dptModel);
				staffRepository.createNewStaff(staffObj);
				
				// 5 productCategory creation, replaced product creation
				Productcategory mainCategory=new Productcategory();
				mainCategory.setCode("01");
				mainCategory.setDescription("Main");
				mainCategory.setName("Main");
				mainCategory.setParentCategoryId(0);
				mainCategory.setParentCategoryName("Min");
				mainCategory.setStatus("1");
				mainCategory.setLevel(1);
				mainCategory.setOnline(false);
				mainCategory.setStatus("1");
				mainCategory.setBranch(branchObj);
				
				productCategoryRepository.createNewProductcategory(mainCategory);
				
				Productcategory foodCategory=new Productcategory();
				foodCategory.setCode("02");
				foodCategory.setName("FOOD");
				foodCategory.setParentCategoryId(mainCategory.getCategoryId());
				foodCategory.setParentCategoryName("Main");
				foodCategory.setStatus("1");
				foodCategory.setLevel(2);
				foodCategory.setOnline(false);
				foodCategory.setStatus("1");
				foodCategory.setBranch(branchObj);
				productCategoryRepository.createNewProductcategory(foodCategory);

				Productcategory drinksCategory=new Productcategory();
				drinksCategory.setCode("02");
				drinksCategory.setName("DRINKS");
				drinksCategory.setParentCategoryId(mainCategory.getCategoryId());
				drinksCategory.setParentCategoryName("Main");
				drinksCategory.setStatus("1");
				drinksCategory.setLevel(2);
				drinksCategory.setOnline(false);
				drinksCategory.setStatus("1");
				drinksCategory.setBranch(branchObj);
				productCategoryRepository.createNewProductcategory(drinksCategory);
				
				Productcategory kandarMainCategory=new Productcategory();
				kandarMainCategory.setCode("02");
				kandarMainCategory.setName("P.Nasi Kandar");
				kandarMainCategory.setParentCategoryId(mainCategory.getCategoryId());
				kandarMainCategory.setParentCategoryName("Main");
				kandarMainCategory.setStatus("1");
				kandarMainCategory.setLevel(2);
				kandarMainCategory.setOnline(false);
				kandarMainCategory.setStatus("1");
				kandarMainCategory.setBranch(branchObj);
				productCategoryRepository.createNewProductcategory(kandarMainCategory);
				
				Productcategory kandarSubCategory=new Productcategory();
				kandarSubCategory.setCode("02");
				kandarSubCategory.setName("NASI KANDAR");
				kandarSubCategory.setParentCategoryId(kandarMainCategory.getCategoryId());
				kandarSubCategory.setParentCategoryName("Main");
				kandarSubCategory.setStatus("1");
				kandarSubCategory.setLevel(3);
				kandarSubCategory.setOnline(false);
				kandarSubCategory.setStatus("1");
				kandarSubCategory.setBranch(branchObj);
				productCategoryRepository.createNewProductcategory(kandarSubCategory);
				
				Productcategory onlineCategory=new Productcategory();
				onlineCategory.setCode("02");
				onlineCategory.setName("ONLINE SHOP");
				onlineCategory.setDescription("ONLINE SHOP CATEGORY");
				onlineCategory.setParentCategoryId(0);
				onlineCategory.setParentCategoryName("Min");
				onlineCategory.setStatus("1");
				onlineCategory.setLevel(1);
				onlineCategory.setOnline(true);
				onlineCategory.setStatus("1");
				onlineCategory.setBranch(branchObj);
				productCategoryRepository.createNewProductcategory(onlineCategory);
				
			
				// 5 Product Creation	
				/*
				List<Productbranchlink> productList = productRepository.getProductListReport(null, null, null, null,branch.getHdbranchId(),null,null);

				for(Productbranchlink product:productList)
					{
					
					Productbranchlink productbranchlink=new Productbranchlink();
					productbranchlink.setQuantityonHand(new BigDecimal(0.00));
					productbranchlink.setNormalPrice(product.getNormalPrice());
					productbranchlink.setPurchasePrice(product.getPurchasePrice());			
					productbranchlink.setDoctorPrice(product.getDoctorPrice());
					productbranchlink.setSalesrepPrice(product.getSalesrepPrice());
					productbranchlink.setQuantityonalert(product.getQuantityonalert());
					productbranchlink.setFullredemptionpoint(product.getFullredemptionpoint());
					productbranchlink.setHalfredemptionpoint(product.getHalfredemptionpoint());
					productbranchlink.setRedemAmount(product.getRedemAmount());
					productbranchlink.setExpiryDate(product.getExpiryDate());
					productbranchlink.setTaxAmount(product.getTaxAmount());
					productbranchlink.setTaxCode(product.getTaxCode());
					productbranchlink.setGstAmount(product.getGstAmount());
					productbranchlink.setGstCode(product.getGstCode());
					productbranchlink.setCustomerPoint(product.getCustomerPoint());
					
					
					productbranchlink.setProduct(product.getProduct());						
					productbranchlink.setBranch(branchObj);							
					
					productRepository.createNewProductbranchlink(productbranchlink);
					
					Productprice newPrice = new Productprice();
					newPrice.setDiscountAmount(new BigDecimal(0.00));					
					newPrice.setQuantityFrom(new BigDecimal(1.00));					
					newPrice.setUom(product.getProduct().getUom());						
					newPrice.setLastModifiedDate(new Date());
					newPrice.setBarCode(product.getProduct().getBarcode());
					newPrice.setTotalQuantity(new BigDecimal(0.00));
					
					newPrice.setProduct(product.getProduct());				
					newPrice.setBranch(branchObj);										
					
					productRepository.createNewProductprice(newPrice);
					}					
			*/
			
				//6 Auto Numbers	
				
				Autonum autonumModel1= commonListRepository.getAutonumDetail(2);
				
				Autonum autonumModel = new Autonum();			   
			    autonumModel.setBranch(branchObj);			    
			  
			    autonumModel.setDeliveryOrderFormat(autonumModel1.getDeliveryOrderFormat());
			    autonumModel.setDespatchNoFormat(autonumModel1.getDespatchNoFormat());
			    autonumModel.setInvoiceNoFormat(autonumModel1.getInvoiceNoFormat());
			    autonumModel.setPrescriptionNoFormat(autonumModel1.getPrescriptionNoFormat());
			    autonumModel.setProductCodeFormat(autonumModel1.getProductCodeFormat());
			    autonumModel.setPurchaceOrderFormat(autonumModel1.getPurchaceOrderFormat());
			    autonumModel.setPurchaseRequestFormat(autonumModel1.getPurchaseRequestFormat());
			    autonumModel.setQuoteNoFormat(autonumModel1.getQuoteNoFormat());
			    autonumModel.setSalesOrderFormat(autonumModel1.getSalesOrderFormat());	
			    
			    autonumModel.setCollectionFormat(autonumModel1.getCollectionFormat());
			    autonumModel.setCollectionNo(1);
			    autonumModel.setDeliveryOrder(1);
			    autonumModel.setDespatchNo(1);
			    autonumModel.setInvoiceNo(1);
			    autonumModel.setPaymentFormat(autonumModel1.getPaymentFormat());
			    autonumModel.setPaymentNo(1);
			    autonumModel.setPrescriptionNo(1);
			    autonumModel.setPurchaceOrder(1);
			    autonumModel.setPurchaseRequest(1);
			    autonumModel.setQuoteNo(1);
			    autonumModel.setSalesOrder(1);		  
			    
			    commonListRepository.createAutonum(autonumModel);
				
				
			    EmailProcess emailProcess = new EmailProcess();
			    String subject="JPOSSYSTEM REGISTRATION for "+branchObj.getBranchName();
			    String message="Login details for "+branchObj.getBranchName()+"\nUser Name : "+staffObj.getStaffCode()+"\nPassword : "+staffObj.getPassword();
			 //   emailProcess.sendEmailToNewUser(branch.getEmailAddress(),subject,message);		
				
				
			}
			
			
			
		}

		catch (Exception e) {
			log.info("Error in createNewBranch BranchBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	
	}

	@Transactional
	@Override
	public boolean updateBranch(BranchModel branch) throws Exception {
		boolean updateSuccess = false;
		
		try {
			Branch branchObj = branchRepository.getBranchDetails(branch.getBranchId());
			
			branchObj.setAddress(branch.getAddress().toUpperCase());
			branchObj.setBranchCode(branch.getBranchCode());			
			branchObj.setBranchName(branch.getBranchName().toUpperCase());			
			branchObj.setCity(branch.getCity().toUpperCase());
			branchObj.setCountry(branch.getCountry().toUpperCase());
			branchObj.setCreatedBy(branch.getCreatedBy());
			branchObj.setCreatedDate(branch.getCreatedDate());
			branchObj.setEmailAddress(branch.getEmailAddress());
			branchObj.setFaxNo(branch.getFaxNo());
			branchObj.setFixedLicenseFee(branch.getFixedLicenseFee());
			branchObj.setFranchiseeFee(branch.getFranchiseeFee());
			branchObj.setIsHQ(branch.getIsHQ());
			branchObj.setLastModifiedDate(branch.getLastModifiedDate());
			branchObj.setPhoneNo(branch.getPhoneNo());
			branchObj.setPostCode(branch.getPostCode());
			
			branchObj.setState(branch.getState());
			branchObj.setStatus(branch.getStatus());
			branchObj.setContactPerson(branch.getContactPerson());
			
			branchObj.setCustomersalesPoint(branch.getCustomersalesPoint());
			branchObj.setCustomersalesValue(branch.getCustomersalesValue());
			branchObj.setRedemPoint(branch.getRedemPoint());
			branchObj.setRedemValue(branch.getRedemValue());
			
			updateSuccess = branchRepository.updateBranch(branchObj);			
		}

		catch (Exception e) {
			log.info("Error in updateBranch BranchBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}


	@Transactional
	@Override
	public boolean deleteBranch(BranchModel branch) throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Branch branchObj = branchRepository.getBranchDetails(branch.getBranchId());			
		
			
			List<Taxmaster> taxlist=commonListRepository.getTaxmasterList(branch.getBranchId());
			for(Taxmaster taxdata:taxlist)
			{
				commonListRepository.deleteTaxmaster(taxdata);		
			}
					
			
			List<Autonum> autonumModel1List=commonListRepository.getAutonumList(branch.getBranchId());
			for(Autonum autonumModel1:autonumModel1List)
			{
				commonListRepository.deleteAutonum(autonumModel1);
			}			
			
			List<Branchstaffmember> staff=staffRepository.findByStaffList(branch.getBranchId(),null, null,null,null);
			for(Branchstaffmember staffdata:staff)
			{
			staffRepository.deleteStaff(staffdata);
			}
			
			List<Rolefunctionlink>  rolefuntionlist=RoleFunctionRepository.findfunctionbyRoleListBranch(branch.getBranchId());
			for(Rolefunctionlink rolefuntiondata:rolefuntionlist)
			{
				RoleFunctionRepository.delete(rolefuntiondata.getRoleFunctionLinkId());
			}
			
			List<Role> rolelist=RoleRepository.findByRoleListAll(null,branch.getBranchId());
			for(Role roleData:rolelist)
			{
				RoleRepository.delete(roleData);
			}		
			
			List<Department> dept =commonListRepository.getDepartmentListByBranch(branch.getBranchId());
			
			for(Department deptdata:dept)
			{
				commonListRepository.deleteDepartment(deptdata);
			}
			
				
			List<Productbranchlink> productbranchlinklist =productRepository.getProductList(null,null,branch.getBranchId());
			
			for(Productbranchlink deptdata:productbranchlinklist)
			{
				productRepository.deleteProductbranchlink(deptdata);
			}
			
			
			/*for(Productbranchlink deptdata:productbranchlinklist)
			{
				Product p = deptdata.getProduct();
				
				List<Productprice> Productpricelist =productRepository.getProductpriceByBarcode(p.getBarcode(),branch.getBranchId());
				
				for(Productprice price:Productpricelist)
				{
				productRepository.deleteProductPrice(price);
				}
			}*/
			
			
			
			
			
			
			deleteSuccess = branchRepository.deleteBranch(branchObj);	
			
			}
		catch (Exception e) {
			log.info("Error in deleteBranch BranchBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	
	}
	
	
	
	@Override
	public List<BranchModel> findByBranchList(Integer branchId,
			String cityName, String stateName, String Status) throws Exception {
		  
		   List<BranchModel> branchObjList = new ArrayList<BranchModel>();	
		   List<Branch> branchList = new ArrayList<Branch>();
		try {			
			branchList = branchRepository.findByBranchList(branchId, cityName, stateName, Status);
			
			for (Branch branch : branchList) {				
			BranchModel branchObj = new BranchModel();	
			branchObj.setAddress(branch.getAddress().toUpperCase());
			branchObj.setBranchCode(branch.getBranchCode());			
			branchObj.setBranchName(branch.getBranchName().toUpperCase());			
			branchObj.setCity(branch.getCity().toUpperCase());
			branchObj.setCountry(branch.getCountry().toUpperCase());
			branchObj.setCreatedBy(branch.getCreatedBy());
			branchObj.setCreatedDate(branch.getCreatedDate());
			branchObj.setEmailAddress(branch.getEmailAddress());
			branchObj.setEmailOldAddress(branch.getEmailAddress());
			branchObj.setFaxNo(branch.getFaxNo());
			branchObj.setFixedLicenseFee(branch.getFixedLicenseFee());
			branchObj.setFranchiseeFee(branch.getFranchiseeFee());
			branchObj.setIsHQ(branch.getIsHQ());
			branchObj.setLastModifiedDate(branch.getLastModifiedDate());
			branchObj.setPhoneNo(branch.getPhoneNo());
			branchObj.setPostCode(branch.getPostCode());
			branchObj.setState(branch.getState());
			branchObj.setStatus(branch.getStatus());
			branchObj.setBranchId(branch.getBranchId());
			branchObj.setContactPerson(branch.getContactPerson());
			branchObj.setBranchtype(branch.getBranchtype());
			
			branchObj.setCustomersalesPoint(branch.getCustomersalesPoint());
			branchObj.setCustomersalesValue(branch.getCustomersalesValue());
			branchObj.setRedemPoint(branch.getRedemPoint());
			branchObj.setRedemValue(branch.getRedemValue());			
			branchObj.setTotalPurchaseTax(branch.getTotalPurchaseTax());
			branchObj.setTotalSalesTax(branch.getTotalSalesTax());
			
			branchObjList.add(branchObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByBranchList BranchBOImpl " + e);
			throw e;
		}
		return branchObjList;
	}

	@Override
	public BranchModel getBranchDetailsByName(String branchName) throws Exception {		
			BranchModel branchObj = new BranchModel();	
			
		try {			
			Branch branch = branchRepository.getBranchDetailsByName(branchName);
			
			branchObj.setAddress(branch.getAddress().toUpperCase());
			branchObj.setBranchCode(branch.getBranchCode());			
			branchObj.setBranchName(branch.getBranchName().toUpperCase());			
			branchObj.setCity(branch.getCity().toUpperCase());
			branchObj.setCountry(branch.getCountry().toUpperCase());
			branchObj.setCreatedBy(branch.getCreatedBy());
			branchObj.setCreatedDate(branch.getCreatedDate());
			branchObj.setEmailAddress(branch.getEmailAddress());
			branchObj.setFaxNo(branch.getFaxNo());
			branchObj.setFixedLicenseFee(branch.getFixedLicenseFee());
			branchObj.setFranchiseeFee(branch.getFranchiseeFee());
			branchObj.setIsHQ(branch.getIsHQ());
			branchObj.setLastModifiedDate(branch.getLastModifiedDate());
			branchObj.setPhoneNo(branch.getPhoneNo());
			branchObj.setPostCode(branch.getPostCode());
			branchObj.setState(branch.getState());
			branchObj.setStatus(branch.getStatus());
			branchObj.setBranchId(branch.getBranchId());
			branchObj.setContactPerson(branch.getContactPerson());
			branchObj.setBranchtype(branch.getBranchtype());
			branchObj.setCustomersalesPoint(branch.getCustomersalesPoint());
			branchObj.setCustomersalesValue(branch.getCustomersalesValue());
			branchObj.setRedemPoint(branch.getRedemPoint());
			branchObj.setRedemValue(branch.getRedemValue());
			branchObj.setTotalPurchaseTax(branch.getTotalPurchaseTax());
			branchObj.setTotalSalesTax(branch.getTotalSalesTax());
		}
		catch (Exception e) {
			log.info("Error in getBranchDetails BranchBOImpl " + e);
			throw e;
		}
		return branchObj;
	}

	@Override
	public BranchModel getBranchDetails(Integer branchId) throws Exception {
		BranchModel branchObj = new BranchModel();	
		
		try {			
			Branch branch = branchRepository.getBranchDetails(branchId);
			
			branchObj.setAddress(branch.getAddress().toUpperCase());
			
			branchObj.setBranchCode(branch.getBranchCode());			
			branchObj.setBranchName(branch.getBranchName().toUpperCase());
			
			branchObj.setBranchOldCode(branch.getBranchCode());			
			branchObj.setBranchOldName(branch.getBranchName().toUpperCase());			
			
			branchObj.setCity(branch.getCity().toUpperCase());
			branchObj.setCountry(branch.getCountry().toUpperCase());
			branchObj.setCreatedBy(branch.getCreatedBy());
			branchObj.setCreatedDate(branch.getCreatedDate());
			branchObj.setEmailAddress(branch.getEmailAddress());
			branchObj.setEmailOldAddress(branch.getEmailAddress());
			branchObj.setFaxNo(branch.getFaxNo());
			branchObj.setFixedLicenseFee(branch.getFixedLicenseFee());
			branchObj.setFranchiseeFee(branch.getFranchiseeFee());
			branchObj.setIsHQ(branch.getIsHQ());
			branchObj.setLastModifiedDate(branch.getLastModifiedDate());
			branchObj.setPhoneNo(branch.getPhoneNo());
			branchObj.setPostCode(branch.getPostCode());
			branchObj.setState(branch.getState());
			branchObj.setStatus(branch.getStatus());	
			branchObj.setBranchId(branch.getBranchId());
			branchObj.setContactPerson(branch.getContactPerson());
			branchObj.setBranchtype(branch.getBranchtype());
			
			branchObj.setCustomersalesPoint(branch.getCustomersalesPoint());
			branchObj.setCustomersalesValue(branch.getCustomersalesValue());
			branchObj.setRedemPoint(branch.getRedemPoint());
			branchObj.setRedemValue(branch.getRedemValue());
			branchObj.setTotalPurchaseTax(branch.getTotalPurchaseTax());
			branchObj.setTotalSalesTax(branch.getTotalSalesTax());
			branchObj.setOnlineBranchId(branch.getOnlineBranchId());
		}
		catch (Exception e) {
			log.info("Error in getBranchDetails BranchBOImpl " + e);
			throw e;
		}
		return branchObj;
	}

	@Override
	public boolean findBranchNameExites(String branchName) throws Exception {
		boolean exists = false;		
		try {
			exists = branchRepository.findBranchNameExites(branchName);
		}
		catch (Exception e) {
			log.info("Error in findBranchNameExites BranchBOImpl " + e);
			throw e;
		}
		return exists;
	}

	@Override
	public boolean findBranchCodeExites(String branchCode) throws Exception {
		boolean exists = false;		
		try {
			exists = branchRepository.findBranchCodeExites(branchCode);
		}
		catch (Exception e) {
			log.info("Error in findBranchCodeExites BranchBOImpl " + e);
			throw e;
		}
		return exists;
	}

}
