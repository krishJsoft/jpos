package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.ShiftModel;

public interface IShiftBO {

	Boolean createNewShift(ShiftModel shift) throws Exception;
 
	List<ShiftModel> loadShiftAll() throws Exception;

	boolean updateShift(ShiftModel shift) throws Exception;

	boolean shiftIsExist(String shiftName) throws Exception;

	ShiftModel loadShift(int parseInt) throws Exception;

	boolean deleteShift(ShiftModel shiftObj) throws Exception;

}
