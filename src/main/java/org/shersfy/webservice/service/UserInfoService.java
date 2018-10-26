package org.shersfy.webservice.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.shersfy.webservice.model.UserInfo;

@WebService
public interface UserInfoService {
	
	
	@WebMethod
	int insert(@WebParam(name="user") UserInfo user);
	
	@WebMethod
	int updateById(@WebParam(name="user") UserInfo user);
	
	@WebMethod
	int delete(@WebParam(name="id") Long id);
	
	@WebMethod
	List<UserInfo> findList();
	

}
