package org.cozy.paas.service;

import java.util.List;
import org.cozy.paas.pojo.HostDB;

public interface HostService {
	public int insert(HostDB host);

	public int delete(int id);

	public int update(HostDB host);

	public HostDB selectById(int id);

	public List<HostDB> selectByPageASC(int start, int pageSize);

	public List<HostDB> selectByPageDESC(int start, int pageSize);
}
