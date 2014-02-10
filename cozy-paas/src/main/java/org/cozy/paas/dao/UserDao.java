package org.cozy.paas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cozy.paas.pojo.User;

public interface UserDao {
	@Insert("INSERT INTO user (id,name,password) VALUES (#{id},#{name},#{password})")
	public int insert(User user);

	@Delete("DELETE FROM user WHERE id = #{id}")
	public int delete(int id);

	@Update("UPDATE user SET name = #{name},password = #{password} WHERE id = #{id}")
	public int update(User user);

	@Select("SELECT * FROM user WHERE id = #{id}")
	public User selectById(int id);

	@Select("SELECT * FROM user ORDER BY id ASC LIMIT #{start},#{pageSize}")
	public List<User> selectByPageASC(int start, int pageSize);

	@Select("SELECT * FROM user ORDER BY id DESC LIMIT #{start},#{pageSize}")
	public List<User> selectByPageDESC(int start, int pageSize);
}
