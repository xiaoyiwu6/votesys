package com.xtdx.dao;

import java.util.HashMap;
import java.util.List;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.PlayerBindCount;
import com.xtdx.pojo.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtdx.pojo.SessionTable;

@Repository
public interface SessionDao {
	public boolean addSessionTable(SessionTable sessionTable);//向sessiontable表中插入比赛
	public List<SessionTable> getSessionTable(int nowNum);//查询sessiontable表的全部信息
	public int getgameNow();//查看比赛状态是否开始
	public boolean beginGame(String nowSession);//修改本次比赛状态为1，开始
	public boolean oversGame(String nowSession);//修改本次比赛状态为2，结束
	public int getgameNowBynowSession(String nowSession);//查看本次比赛的状态，主要是开是否结束
	public boolean stopsGame(String nowSession);//修改本次比赛状态为3，暂停
	public int getWhetherTheEntry(Player player);//查询选手是否参赛
	public int getNowField(int wheel);//查询本轮比赛已有场数
	public int nowSessionRepeat(String nowSession);//查询数据库sessiontable表，传入拼凑出的nowSession对比是否有相同的字段
	public int getValueAByNowSession(String nowSession);//查询选手A的票数
	public int getValueBByNowSession(String nowSession);//查询选手B的票数
	public SessionTable getSessionTableByNowSession(String nowSession);//查询sessiontable表，传入sessiontable
	public boolean updateVoteAndWinner(SessionTable sessionTable);//插入票数和winner
	public List<SessionTable> getAllSessionTable();//查看完成比赛的所有信息

	/**
	 *  获取当前session下的所有候选人
	 * 	<select id="getAllPlayersBySessionId" parameterType="int" resultType="com.xtdx.pojo.Player">
	 * 		SELECT * FROM sessplayer WHERE sessionId=${sessionId};
	 * 	</select>
	 * @param sessionId
	 * @return 候选人队列
	 */
	public List<Player> getAllPlayersBySessionId(int sessionId);

	/**
	 *  获取当前投票活动
	 * 	<select id="getCurSession" resultType="com.xtdx.pojo.session">
	 * 		SELECT * FROM SESSION WHERE state=1;
	 * 	</select>
	 * @return  当前投票活动
	 */
	public Session getCurSession();

	/**
	 *  获取所有session
	 * 	<select id="getAllSession" resultType="com.xtdx.pojo.Session">
	 * 		SELECT * FROM SESSION;
	 * 	</select>
	 */
	public List<Session> getAllSession();

	/**
	 * 插入新的session
	 * 	<insert id="insertSession" parameterType="com.xtdx.pojo.Session">
	 * 		INSERT INTO `VOTE`.`session` (
	 * 		  `sessionName`,
	 * 		  `state`,
	 * 		  `create`,
	 * 		)
	 * 		VALUES
	 * 		  (
	 * 			${sessionName},
	 * 			0,
	 * 			${create}
	 * 		  );
	 *
	 * 	</insert>
	 */
	public int insertSession(Session session);

	/**
	 * 查找没加入当前投票的候选人
	 * 	<select id="getAbsentPlayersBySessionId" parameterType="int" resultType="com.xtdx.pojo.Player">
	 * 		SELECT * FROM player WHERE playerId NOT IN (SELECT playerId FROM sessplayer WHERE sessionId=${sessionId});
	 * 	</select>
	 */
	public List<Player> getAbsentPlayersBySessionId(@Param("sessionId") int sessionId);

	/**
	 * 查找已加入当前投票的候选人
	 * 	<select id="getPresentPlayersBySessionId" parameterType="int" resultType="com.xtdx.pojo.Player">
	 * 		SELECT * FROM player WHERE playerId IN (SELECT playerId FROM sessplayer WHERE sessionId=${sessionId});
	 * 	</select>
	 */
	public List<Player> getPresentPlayersBySessionId(@Param("sessionId") int sessionId);

	/**
	 * 把候选人加入到当前投票
	 * 	<insert id="insertPlayerToSession" parameterType="hashmap">
	 * 		INSERT INTO `VOTE`.`sessplayer` (`sessionId`, `playerId`, `count`)
	 * 		VALUES
	 * 		  (${sessionId}, ${playerId}, 0);
	 *
	 * 	</insert>
	 */
	public int insertPlayerToSession(HashMap map);

	/**
	 * 查询正在进行的投票的候选人信息
	 * 	<select id="getAbsentPlayersFromCurSession" parameterType="int" resultType="com.xtdx.pojo.Player">
	 * 		SELECT * FROM player WHERE playerId  IN (SELECT playerId FROM sessplayer,`session` WHERE sessplayer.sessionId=${sessionId} AND state=1);
	 * 	</select>
	 */
	public List<Player> getAbsentPlayersFromCurSession(@Param("sessionId") int sessionId);

	/**
	 * 投票
	 * 	<insert id="votePlayer">
	 * 		INSERT INTO `VOTE`.`sesscount` (
	 * 		  `sessionId`,
	 * 		  `playerId`,
	 * 		  `userId`,
	 * 		  `ballot`
	 * 		)
	 * 		VALUES
	 * 		  (
	 * 			${sessionId},
	 * 			${playerId},
	 * 			${userId},
	 * 			${ballot}
	 * 		  );
	 * 	</insert>
	 */
	public int votePlayer(@Param("sessionId") int sessionId,@Param("playerId") int playerId,@Param("userId") int userId,@Param("ballot") String ballot);

	/**
	 * 投票计数
	 * 	<update id="updateCount" parameterType="hashmap">
	 * 		UPDATE
	 * 		  `VOTE`.`sessplayer`
	 * 		SET
	 * 		  `count` = 'count'+1
	 * 		WHERE `sessionId` = ${sessionId}
	 * 		  AND `playerId` = ${playerId};
	 * 	</update>
	 */
	public int updateCount(HashMap map);

	/**
	 * 查询票数
	 * 	<select id="selectCount" parameterType="hashmap" resultType="int">
	 * 		SELECT count FROM sessplayer
	 * 		WHERE sessionId=${sessionId}
	 * 		AND playerId=${playerId};
	 * 	</select>
	 */
	public int selectCount(HashMap map);

	/**
	 *  联查查询，用于index页
	 * 	<select id="selectPlayerBindCount" parameterType="hashmap" resultMap="PBCResult">
	 * 		SELECT * FROM sessplayer INNER JOIN player
	 * 		WHERE sessionId=${sessionId}
	 * 		AND playerId=${player.playerId}
	 * 	</select>
	 */
	public List<PlayerBindCount> selectPlayerBindCount(@Param("sessionId") int sessionId);

	/**
	 *  开始session
	 * 	<update id="beginSessionById" >
	 * 		UPDATE
	 * 		  `VOTE`.`session`
	 * 		SET
	 * 		  `state` = 1,
	 * 		  `start` = ${start},
	 * 		WHERE `sessionId` = ${sessionId};
	 * 	</update>
	 */
	public int beginSessionById(@Param("start") String start,@Param("sessionId") int sessionId);

	/**
	 * 结束session
	 * 	<update id="endCurSession" parameterType="String">
	 * 		UPDATE
	 * 		  `VOTE`.`session`
	 * 		SET
	 * 		  `state` = 1,
	 * 		  `start` = STR_TO_DATE(#{end},'%Y-%m-%d %H:%i:%s')
	 * 		WHERE `sessionId` = 1;
	 * 	</update>
	 */
	public int endCurSession(String end);

	/**
	 * 查找正在进行的session
	 * 	<select id="selectCurSession" resultType="com.xtdx.pojo.Session">
	 * 		SELECT * FROM `VOTE`.`session` WHERE state=1;
	 * 	</select>
	 */
	public Session selectCurSession();

	/**
	 * 修改活动名字
	 * 	<update id="updateSessionNameBySessionId" >
	 * 		UPDATE `session` SET sessionName=#{sessionName} WHERE  sessionId=#{sessionId};
	 * 	</update>
	 */
	public int updateSessionNameBySessionId(@Param("sessionName") String sessionName,@Param("sessionId") int sessionId);
}
