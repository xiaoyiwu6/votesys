package com.xtdx.dao;

import java.util.List;

import com.xtdx.pojo.KeyPairs;
import com.xtdx.pojo.SessionCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtdx.pojo.User;

@Repository
public interface UserDao {
	public User getUser(String keywords);

	/**
	 * 获取所有选民
	 *     <select id="getAllUsers" resultType="com.xtdx.pojo.User">
	 *         SELECT * FROM USER WHERE LEVEL=0;
	 *     </select>
	 * @return 用户队列
	 */
	public List<User> getAllUsers();

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

	/**
	 * 根据选民ID和当前选投票活动获取选票
	 *     <select id="getSessionCountByUserIdAndSessionId" resultType="com.xtdx.pojo.SessionCount">
	 *         SELECT * FROM sesscount WHERE userId=#{userId} AND sessionId=#{sessionId};
	 *     </select>
	 */
	public SessionCount getSessionCountByUserIdAndSessionId(@Param("userId") int userId, @Param("sessionId") int sessionId);

	/**
	 * 插入密钥对
	 *     <insert id="insertKeyPairsById" parameterType="com.xtdx.pojo.KeyPairs">
	 *         INSERT INTO `VOTE`.`keypairs` (
	 *           `userId`,
	 *           `rsaPubKey`,
	 *           `rsaPriKeey`,
	 *           `eccPubKey`,
	 *           `eccPriKey`
	 *         )
	 *         VALUES
	 *           (
	 *             #{userId},
	 *             #{rsaPubKey},
	 *             #{rsaPriKeey},
	 *             #{eccPubKey},
	 *             #{eccPriKey}
	 *           );
	 *     </insert>
	 */
	public  int insertKeyPairsById(KeyPairs keyPairs);

	/**
	 * 获取密钥对
	 *     <select id="selectKeyPairsByUserId" parameterType="int" resultType="com.xtdx.pojo.KeyPairs">
	 *         SELECT * FROM keypairs WHERE userId=#{userId};
	 *     </select>
	 */
	public KeyPairs selectKeyPairsByUserId(int userId);
}
