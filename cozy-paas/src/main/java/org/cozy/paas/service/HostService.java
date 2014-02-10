package org.cozy.paas.service;

import java.util.List;
import org.cozy.paas.pojo.Host;

public interface HostService {
	public int insert(Host host);

	public int delete(int id);

	public int update(Host host);

	public Host selectById(int id);

	public List<Host> selectByPageASC(int start, int pageSize);

	public List<Host> selectByPageDESC(int start, int pageSize);
}
