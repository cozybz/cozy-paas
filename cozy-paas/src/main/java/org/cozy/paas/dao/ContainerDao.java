package org.cozy.paas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cozy.paas.pojo.ContainerDB;

public interface ContainerDao {

	@Insert("INSERT INTO container (id,userId,hostId,name,memory,status) VALUES (#{id},#{userId},#{hostId},#{name},#{memory},#{status})")
	@Options(useGeneratedKeys = true, keyColumn = "id")
	public int insert(ContainerDB container);

	@Delete("DELETE FROM container WHERE id = #{id}")
	public int delete(String id);

	@Update("UPDATE container SET hostId = #{hostId},userId = #{userId},name = #{name}, memory = #{memory},status = #{status} WHERE id = #{id}")
	public int update(ContainerDB container);

	@Select("SELECT * FROM container WHERE id = #{id}")
	public ContainerDB selectById(String id);

	@Select("SELECT * FROM container")
	public List<ContainerDB> selectAll();

	@Select("SELECT * FROM container WHERE hostId = #{hostId}")
	public List<ContainerDB> selectByHostId(int hostId);

	@Select("SELECT * FROM container WHERE userId = #{userId}")
	public List<ContainerDB> selectByUserId(int userId);

	@Select("SELECT * FROM container ORDER BY id ASC LIMIT #{start},#{pageSize}")
	public List<ContainerDB> selectByPageASC(int start, int pageSize);

	@Select("SELECT * FROM container ORDER BY id DESC LIMIT #{start},#{pageSize}")
	public List<ContainerDB> selectByPageDESC(int start, int pageSize);

}
