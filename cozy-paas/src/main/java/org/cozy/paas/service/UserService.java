package org.cozy.paas.service;

import java.util.List;

import org.cozy.paas.pojo.UserDB;

public interface UserService {
	public int insert(UserDB user);

	public int delete(int id);

	public int update(UserDB user);

	public UserDB selectById(int id);
	
	public UserDB selectByName(String name);

	public List<UserDB> selectByPageASC(int start, int pageSize);

	public List<UserDB> selectByPageDESC(int start, int pageSize);

	public UserDB vertify(String name, String password);
}
