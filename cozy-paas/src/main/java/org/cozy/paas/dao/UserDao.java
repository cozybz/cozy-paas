package org.cozy.paas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cozy.paas.pojo.UserDB;

public interface UserDao {
	@Insert("INSERT INTO user (id,name,password) VALUES (#{id},#{name},#{password})")
	public int insert(UserDB user);

	@Delete("DELETE FROM user WHERE id = #{id}")
	public int delete(int id);

	@Update("UPDATE user SET name = #{name},password = #{password} WHERE id = #{id}")
	public int update(UserDB user);

	@Select("SELECT * FROM user WHERE id = #{id}")
	public UserDB selectById(int id);

	@Select("SELECT * FROM user WHERE name = #{name}")
	public UserDB selectByName(String name);

	@Select("SELECT * FROM user ORDER BY id ASC LIMIT #{start},#{pageSize}")
	public List<UserDB> selectByPageASC(int start, int pageSize);

	@Select("SELECT * FROM user ORDER BY id DESC LIMIT #{start},#{pageSize}")
	public List<UserDB> selectByPageDESC(int start, int pageSize);
}
