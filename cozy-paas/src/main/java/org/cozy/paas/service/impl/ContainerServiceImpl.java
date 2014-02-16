package org.cozy.paas.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.cozy.paas.dao.ContainerDao;
import org.cozy.paas.pojo.ContainerDB;
import org.cozy.paas.service.ContainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContainerServiceImpl implements ContainerService {

	@Resource
	private ContainerDao containerDao;

	@Override
	public int insert(ContainerDB container) {
		return containerDao.insert(container);
	}

	@Override
	public int delete(String id) {
		return containerDao.delete(id);
	}

	@Override
	public int update(ContainerDB container) {
		return containerDao.update(container);
	}

	@Override
	public ContainerDB selectById(String id) {
		return containerDao.selectById(id);
	}

	@Override
	public List<ContainerDB> selectByPageASC(int start, int pageSize) {
		return containerDao.selectByPageASC(start, pageSize);
	}

	@Override
	public List<ContainerDB> selectByPageDESC(int start, int pageSize) {
		return containerDao.selectByPageDESC(start, pageSize);
	}

	@Override
	public List<ContainerDB> selectByUserId(int userId) {
		return containerDao.selectByUserId(userId);
	}

	@Override
	public List<ContainerDB> selectByHostId(int hostId) {
		return containerDao.selectByHostId(hostId);
	}

	@Override
	public List<ContainerDB> selectAll() {
		return containerDao.selectAll();
	}

}
