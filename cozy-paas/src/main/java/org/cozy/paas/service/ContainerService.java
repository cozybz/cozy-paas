package org.cozy.paas.service;

import java.util.List;
import org.cozy.paas.pojo.ContainerDB;

public interface ContainerService {

	public int insert(ContainerDB container);

	public int delete(String id);

	public int update(ContainerDB container);

	public ContainerDB selectById(String id);

	public List<ContainerDB> selectByPageASC(int start, int pageSize);

	public List<ContainerDB> selectByPageDESC(int start, int pageSize);
}
