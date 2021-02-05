package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IShiftBO;
import com.project.dao.interfaces.IShiftDAO;
import com.project.model.datamodel.ShiftModel;
import com.project.model.his.Branch;
import com.project.model.his.Shift;
@Service("shiftBO")

public class ShiftBOImpl implements IShiftBO {
	public static Logger log = LoggerFactory.getLogger(ShiftBOImpl.class);

	@Resource(name = "shiftRepository")
	private IShiftDAO shiftRepository;
	
	@Transactional
	@Override
	public Boolean createNewShift(ShiftModel shift) throws Exception {
		Boolean savesuccess=false;
		try {

			Shift shiftObj=new Shift();
			shiftObj.setShiftName(shift.getShiftName());
			shiftObj.setShiftStart(shift.getTimein());
			shiftObj.setShiftEnd(shift.getTimeout());
			
			Branch branch = new Branch();
			branch.setBranchId(shift.getBranchId());
			shiftObj.setBranch(branch);
			
			
			savesuccess=shiftRepository.createNewShift(shiftObj);
			
		}catch(Exception ex) {
			log.info("Error in createNewShift ShiftBOImpl " + ex);
			throw ex;
		}
		return savesuccess;
	}

	@Override
	public List<ShiftModel> loadShiftAll() throws Exception {
		List<ShiftModel> shiftList=new ArrayList<ShiftModel>();
		List<Shift> shiftListData=shiftRepository.loadShiftAll();
		for(Shift data:shiftListData) {
			ShiftModel obj=new ShiftModel();
			obj.setShiftID(data.getId());
			obj.setShiftName(data.getShiftName());
			obj.setTimein(data.getShiftStart());
			obj.setTimeout(data.getShiftEnd());
			shiftList.add(obj);
		}
		return shiftList;
	}

	@Override
	public boolean updateShift(ShiftModel shift) throws Exception {
		boolean updateSuccess=false;
		try {

			Shift shiftObj=new Shift();
			shiftObj.setId(shift.getShiftID());
			shiftObj.setShiftName(shift.getShiftName());
			shiftObj.setShiftStart(shift.getTimein());
			shiftObj.setShiftEnd(shift.getTimeout());
			
			Branch branch = new Branch();
			branch.setBranchId(shift.getBranchId());
			shiftObj.setBranch(branch);
			
			updateSuccess=shiftRepository.updateShift(shiftObj);
			
			
		}catch(Exception ex) {
			log.info("Error in updateShift ShiftBOImpl " + ex);
			throw ex;
		}
		return updateSuccess;
	}

	@Override
	public boolean shiftIsExist(String shiftName) throws Exception {
		boolean exist=true;
		try {
			exist=shiftRepository.shiftIsExist(shiftName);
		}catch(Exception ex) {
			log.info("Error in shiftIsExist ShiftBOImpl " + ex);
			throw ex;
		}
		return exist;
	}

	@Override
	public ShiftModel loadShift(int shiftID) throws Exception {
		ShiftModel shiftObj=new ShiftModel();
		try{
			Shift shiftData=shiftRepository.loadShift(shiftID);
			shiftObj.setShiftID(shiftData.getId());
			shiftObj.setShiftName(shiftData.getShiftName());
			shiftObj.setTimein(shiftData.getShiftStart());
			shiftObj.setTimeout(shiftData.getShiftEnd());
		}catch(Exception ex) {
			log.info("Error in loadShift ShiftBOImpl " + ex);
			ex.printStackTrace();
		}
		
		return shiftObj;
	}
	
	@Transactional
	@Override
	public boolean deleteShift(ShiftModel shiftObj) throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Shift shiftData=shiftRepository.loadShift(shiftObj.getShiftID());
			deleteSuccess=shiftRepository.deleteShift(shiftData);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteSuccess;
	}

}
