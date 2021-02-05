package com.project.bo.interfaces;

import java.util.List;


import com.project.model.datamodel.ProductvalidityModel;
import com.project.model.datamodel.UserLoginModel;
import com.project.model.his.Productvalidity;

public interface IUserLoginBO {

	public void createNewUser(UserLoginModel user) throws Exception;

	public void updateUser(UserLoginModel user) throws Exception;

	public void deleteUser(Integer id) throws Exception;

	public List<UserLoginModel> findUserLoginLogin(String username)
			throws Exception;

	public List<UserLoginModel> findUserLoginLogin() throws Exception;

	public UserLoginModel getUserLoginDetails(String emailId) throws Exception;

	public UserLoginModel getUserLoginDetails(Integer id) throws Exception;

	public ProductvalidityModel findProductvalidityLogin() throws Exception;

	public boolean updateProductKey(ProductvalidityModel productvalidityModel)
			throws Exception;

	public UserLoginModel findUserLoginbyUserName(String username) throws Exception;

	public boolean findUserLoginExites(String userName) throws Exception;

}
