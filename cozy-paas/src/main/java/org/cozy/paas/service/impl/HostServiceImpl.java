package org.cozy.paas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.cozy.paas.dao.HostDao;
import org.cozy.paas.pojo.Host;
import org.cozy.paas.service.HostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HostServiceImpl implements HostService {

	@Resource
	private HostDao hostDao;

	@Override
	public int insert(Host host) {
		return hostDao.insert(host);
	}

	@Override
	public int delete(int id) {
		return hostDao.delete(id);
	}

	@Override
	public int update(Host host) {
		return hostDao.update(host);
	}

	@Override
	public Host selectById(int id) {
		return hostDao.selectById(id);
	}

	@Override
	public List<Host> selectByPageASC(int start, int pageSize) {
		return hostDao.selectByPageASC(start, pageSize);
	}

	@Override
	public List<Host> selectByPageDESC(int start, int pageSize) {
		return hostDao.selectByPageDESC(start, pageSize);
	}

}
