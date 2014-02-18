package org.cozy.paas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cozy.paas.pojo.HostDB;

public interface HostDao {
	@Insert("INSERT INTO host (id,ip) VALUES (#{id},#{ip})")
	public int insert(HostDB host);

	@Delete("DELETE FROM host WHERE id = #{id}")
	public int delete(int id);

	@Update("UPDATE host SET ip = #{ip} WHERE id = #{id}")
	public int update(HostDB host);

	@Select("SELECT * FROM host WHERE id = #{id}")
	public HostDB selectById(int id);
	
	@Select("SELECT * FROM host")
	public List<HostDB> selectAll();

	@Select("SELECT * FROM host ORDER BY id ASC LIMIT #{start},#{pageSize}")
	public List<HostDB> selectByPageASC(int start, int pageSize);

	@Select("SELECT * FROM host ORDER BY id DESC LIMIT #{start},#{pageSize}")
	public List<HostDB> selectByPageDESC(int start, int pageSize);
}
