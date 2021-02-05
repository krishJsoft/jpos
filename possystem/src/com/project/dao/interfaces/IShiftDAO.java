package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Shift;

public interface IShiftDAO {

	Boolean createNewShift(Shift shiftObj) throws Exception;

	List<Shift> loadShiftAll() throws Exception;

	boolean updateShift(Shift shiftObj)throws Exception;

	boolean shiftIsExist(String shiftName) throws Exception;

	Shift loadShift(int shiftID) throws Exception;

	boolean deleteShift(Shift shiftData) throws Exception;

}
