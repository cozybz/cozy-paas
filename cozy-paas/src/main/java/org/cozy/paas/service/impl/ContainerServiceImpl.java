package org.cozy.paas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.cozy.paas.dao.ContainerDao;
import org.cozy.paas.pojo.Container;
import org.cozy.paas.service.ContainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContainerServiceImpl implements ContainerService {

	@Resource
	private ContainerDao containerDao;

	@Override
	public int insert(Container container) {
		return containerDao.insert(container);
	}

	@Override
	public int delete(String id) {
		return containerDao.delete(id);
	}

	@Override
	public int update(Container container) {
		return containerDao.update(container);
	}

	@Override
	public Container selectById(String id) {
		return containerDao.selectById(id);
	}

	@Override
	public List<Container> selectByPageASC(int start, int pageSize) {
		return containerDao.selectByPageASC(start, pageSize);
	}

	@Override
	public List<Container> selectByPageDESC(int start, int pageSize) {
		return containerDao.selectByPageDESC(start, pageSize);
	}

}
