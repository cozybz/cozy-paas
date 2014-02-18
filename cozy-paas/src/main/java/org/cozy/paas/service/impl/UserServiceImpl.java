package org.cozy.paas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.cozy.paas.dao.UserDao;
import org.cozy.paas.pojo.UserDB;
import org.cozy.paas.service.UserService;
import org.cozy.paas.tools.EncodeHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public int insert(UserDB user) {
		user.setPassword(EncodeHelper.encodeBySHA1(user.getPassword()));
		return userDao.insert(user);
	}

	@Override
	public int delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public int update(UserDB user) {
		user.setPassword(EncodeHelper.encodeBySHA1(user.getPassword()));
		return userDao.update(user);
	}

	@Override
	public UserDB selectById(int id) {
		return userDao.selectById(id);
	}

	@Override
	public List<UserDB> selectByPageASC(int start, int pageSize) {
		return userDao.selectByPageASC(start, pageSize);
	}

	@Override
	public List<UserDB> selectByPageDESC(int start, int pageSize) {
		return userDao.selectByPageDESC(start, pageSize);
	}

	@Override
	public UserDB vertify(String name, String password) {
		if (name == "" || password == "")
			return null;
		UserDB u = userDao.selectByName(name);
		if (u!=null && u.getPassword().equals(EncodeHelper.encodeBySHA1(password)))
			return u;
		return null;
	}

	@Override
	public UserDB selectByName(String name) {
		return userDao.selectByName(name);
	}
}
