package org.cozy.paas.service;

import java.util.List;
import org.cozy.paas.pojo.Container;

public interface ContainerService {

	public int insert(Container container);

	public int delete(String id);

	public int update(Container container);

	public Container selectById(String id);

	public List<Container> selectByPageASC(int start, int pageSize);

	public List<Container> selectByPageDESC(int start, int pageSize);
}
