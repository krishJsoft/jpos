package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.bo.interfaces.IDiscountBO;
import com.project.dao.interfaces.IDiscountDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.his.Discount;
import com.project.model.his.Discountremarks;
import com.project.model.sale.sales.DiscountModel;
import com.project.model.sale.sales.DiscountremarksModel;

@Service("discountBO")
public class DiscountBOImpl implements IDiscountBO {
	public static Logger log = LoggerFactory.getLogger(DiscountBOImpl.class);
	@Resource(name = "discountRepository")
	private IDiscountDAO discountRepository;
	
	@Override
	public List<DiscountModel> fetchDiscountList(int branchId) throws Exception {
		List<DiscountModel> objList=new ArrayList<DiscountModel>();
		try {
			List<Discount> discountList=discountRepository.fetchDiscountList(branchId);
			for(Discount data:discountList) {
				DiscountModel obj=new DiscountModel();
				obj.setDiscountId(data.getDiscountId());
				obj.setDiscountRate(data.getDiscountRate());
				obj.setDiscountTag(data.getDiscountTag());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				
				obj.setBranch(branchObj);
				
				objList.add(obj);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchDiscountList DiscountBOImpl " + ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public List<DiscountremarksModel> fetchDiscountRemarksList(int branchId) throws Exception {
		List<DiscountremarksModel> objList=new ArrayList<DiscountremarksModel>();
		try {
			List<Discountremarks> discountList=discountRepository.fetchDiscountRemarksList(branchId);
			for(Discountremarks data:discountList) {
				DiscountremarksModel obj=new DiscountremarksModel();
				obj.setDiscountRemarksId(data.getDiscountRemarksId());
				obj.setRemarks(data.getRemarks());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				
				obj.setBranch(branchObj);
				
				objList.add(obj);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchDiscountRemarksList DiscountBOImpl " + ex);
			throw ex;
		}
		return objList;
	}

}
