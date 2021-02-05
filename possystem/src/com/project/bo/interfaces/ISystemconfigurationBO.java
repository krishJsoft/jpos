package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.KitchenprintercategorylinkModal;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.SystemTypeModel;
import com.project.model.datamodel.SystemconfigurationModel;

public interface ISystemconfigurationBO {

	SystemconfigurationModel getConfiguration(int branchId) throws Exception;

	List<KitchenprinterlistModal> getKitchenPrinterList(String kitchenName, int branchId)throws Exception;

	Boolean createNewKitchenPrinter(KitchenprinterlistModal kitchenPrinter) throws Exception;

	List<KitchenprintercategorylinkModal> getKitchenPrinterCategoryLinkList(Integer printerId, Integer categoryId,
			int branchId) throws Exception;

	KitchenprinterlistModal getKitchenPrinterDetails(Integer printerId) throws Exception;

	List<SystemTypeModel>  fetchSystemTypeList() throws Exception;

	boolean updateSystemConfiguration(SystemconfigurationModel systemconfiguration) throws Exception;

	SystemTypeModel getSystemTypeDetails(int typeId) throws Exception;

	Boolean updateKitchenPrinter(KitchenprinterlistModal printer) throws Exception;

}
