package org.cozy.paas.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.cozy.paas.dao.UserDao;
import org.cozy.paas.pojo.User;
import org.cozy.paas.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectById(int id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> selectByPageASC(int start, int pageSize) {
		return userDao.selectByPageASC(start, pageSize);
	}

	@Override
	public List<User> selectByPageDESC(int start, int pageSize) {
		return userDao.selectByPageDESC(start, pageSize);
	}

}
