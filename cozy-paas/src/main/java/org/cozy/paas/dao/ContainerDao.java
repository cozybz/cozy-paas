package org.cozy.paas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cozy.paas.pojo.Container;

public interface ContainerDao {

	@Insert("INSERT INTO container (id,userId,hostId) VALUES (#{id},#{userId},#{hostId})")
	public int insert(Container container);

	@Delete("DELETE FROM container WHERE id = #{id}")
	public int delete(String id);

	@Update("UPDATE container SET hostId = #{hostId},userId = #{userId} WHERE id = #{id}")
	public int update(Container container);

	@Select("SELECT * FROM container WHERE id = #{id}")
	public Container selectById(String id);

	@Select("SELECT * FROM container ORDER BY id ASC LIMIT #{start},#{pageSize}")
	public List<Container> selectByPageASC(int start, int pageSize);

	@Select("SELECT * FROM container ORDER BY id DESC LIMIT #{start},#{pageSize}")
	public List<Container> selectByPageDESC(int start, int pageSize);

}
