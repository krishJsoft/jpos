package com.project.dao.interfaces;

import java.util.List;

import com.project.model.datamodel.SystemconfigurationModel;
import com.project.model.his.Kitchenprintercategorylink;
import com.project.model.his.Kitchenprinterlist;
import com.project.model.his.Systemconfiguration;
import com.project.model.his.Systemtype;

public interface ISystemconfigurationDAO {

	Systemconfiguration getConfiguration(int branchId) throws Exception;

	List<Kitchenprinterlist> getKitchenPrinterList(String kitchenName, int branchId) throws Exception;

	Boolean createNewKitchenPrinter(Kitchenprinterlist printer) throws Exception;

	List<Kitchenprintercategorylink> getKitchenPrinterCategoryLinkList(Integer printerId, Integer categoryId,
			int branchId) throws Exception;

	Kitchenprinterlist getKitchenPrinterDetails(Integer printerId) throws Exception;

	List<Systemtype> fetchSystemTypeList()  throws Exception;

	boolean updateSystemConfiguration(Systemconfiguration config) throws Exception;

	Systemtype getSystemTypeDetails(int typeId) throws Exception;

	boolean updateKitchenPrinter(Kitchenprinterlist printer) throws Exception;
}
