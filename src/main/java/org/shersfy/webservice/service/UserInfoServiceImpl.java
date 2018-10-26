package org.shersfy.webservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.shersfy.webservice.model.UserInfo;
import org.springframework.stereotype.Component;

@Component
@WebService(targetNamespace="http://service.webservice.shersfy.org/", 
endpointInterface = "org.shersfy.webservice.service.UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	private Map<Long, UserInfo> cache = new ConcurrentHashMap<>();
	
	@PostConstruct
	protected void init() {
		UserInfo user = new UserInfo();
		user.setId(1L);
		user.setUsername("admin");
		user.setPassword("123456");
		user.setCreateTime(new Date());
		cache.put(user.getId(), user);
	}

	@Override
	public int insert(UserInfo info) {
		info.setCreateTime(new Date());
		cache.put(info.getId(), info);
		return 1;
	}

	@Override
	public int updateById(UserInfo info) {
		cache.put(info.getId(), info);
		return 1;
	}

	@Override
	public int delete(Long id) {
		cache.remove(id);
		return 1;
	}

	@Override
	public List<UserInfo> findList() {
		List<UserInfo> list = new ArrayList<>();
		list.addAll(cache.values());
		return list;
	}
	
}
