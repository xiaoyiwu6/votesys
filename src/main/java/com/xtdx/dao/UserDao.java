package com.xtdx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtdx.pojo.User;

@Repository
public interface UserDao {
	public User getUser(String keywords);

	public List<User> getUsers();

	public boolean deleteUser(int userId);

	/**
	 * 注册用户（仅选民）
	 * <insert id="addUser" parameterType="com.xtdx.pojo.User">
	 * INSERT INTO `VOTE`.`user` (
	 * `account`,
	 * `password`,
	 * `type`
	 * )
	 * VALUES
	 * (
	 * #{account},
	 * #{password},
	 * 0
	 * );
	 * </insert>
	 */
	public int addUser(User user);
	/**
	 * 根据账户获取选民账户
	 * 	<select id="getUserByAccount" parameterType="String" resultType="com.xtdx.pojo.User">
	 * 		SELECT * FROM user WHERE account=#{account};
	 * 	</select>
	 */
	public User getUserByAccount(String account);
}
