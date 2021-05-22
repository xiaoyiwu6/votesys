package com.xtdx.service;

import java.security.KeyPair;
import java.util.List;
import java.util.Map;

import com.xtdx.encryption.ECDSA;
import com.xtdx.encryption.RSA;
import com.xtdx.pojo.KeyPairs;
import com.xtdx.pojo.SessionCount;
import com.xtdx.utils.HexUtil;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtdx.dao.UserDao;
import com.xtdx.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public User getUser(String keywords) {
		return userDao.getUser(keywords);
	}

	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}

	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}

	//根据用户名得到用户
	public User getUserByAccount(String account){return userDao.getUserByAccount(account);}

	//获取我的选票
	public SessionCount getSessionCountByUserIdAndSessionId(int userId, int sessionId){return userDao.getSessionCountByUserIdAndSessionId(userId,sessionId);}

	//生成密钥对
	public int genKeyPairs(int userId) throws Exception {
		//初始化密钥对类
		KeyPairs keyPairs = new KeyPairs();
		keyPairs.setUserId(userId);
		//获取RSA密钥对
		Map<String,Object> rsaMap = RSA.init();
		//放入
		keyPairs.setRsaPubKey(RSA.getPublicKey(rsaMap));
		keyPairs.setRsaPriKey(RSA.getPrivateKey(rsaMap));
		//获取ECC密钥对
		KeyPair ECDSAKeyPairs = ECDSA.getKeyPair();
		//放入
		keyPairs.setEccPubKey(HexUtil.encodeHexString(ECDSAKeyPairs.getPublic().getEncoded()));
		keyPairs.setEccPriKey(HexUtil.encodeHexString(ECDSAKeyPairs.getPrivate().getEncoded()));

		return userDao.insertKeyPairsById(keyPairs);
	}

	//获取密钥对
	public KeyPairs selectKeyPairsByUserId(int userId){return userDao.selectKeyPairsByUserId(userId);}

}
