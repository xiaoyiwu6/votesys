package com.xtdx.service;

import com.xtdx.dao.UserDao;
import com.xtdx.encryption.RSA;
import com.xtdx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	//返回注册结果
	public int sign(User user){return userDao.addUser(user);}
	
	//判断用户是否登陆成功
	public boolean execute(User requestUser){
		User user = userDao.getUserByAccount(requestUser.getAccount());
		//判断用户是否存在,存在后再判断密码是否相等
		if(user ==null){
			return false;
		} else{
			String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIs1uYqX3+AufsF5//G1SSyELVbKstktGlcJwVhHWBCLSKxaxkFOJwLh58E6SQ0bev7W57SC+25T+2NEQUZ/hjsdoocZvm00I6Uw142MzlgtHAsWqtRKxDIPiuPh65rWW15quvbhHQeKWdO+dCbpn9RjSzbW905QVAE9jQY0Br7TAgMBAAECgYBcYhbzpr5no/Nyqmf0G/6nkEAWbQYrogbs5AhvcUk8EXL1DnirNhYlj42hafC4xhflrvCtlo8NNKaLxewbwN1uuzG8A2jd+ROEXlx5HDh2ZluhtHzL/SmNcJXo684xAl2pCNVBjDcW48PcIBijke/sTVHTDsDCukLKDPUOM/mKIQJBAL96k4+jBscazsJiuZ6C3RFDVtRRDpf1dMgLgxcx63bAXkA2Arau0J49IAYmSVJoDXqDoJKWdXJVh9vHSkhN/48CQQC6Hk1/G0Y0nOylf6NOp0oMgc0A+etnwxHKqwtctPKjEYcJx2fzALzTtCoySLYXX7gLnPIQXpQBTUysG5skBKp9AkEAiSQm6fqu0Q4fRlRlc+VwpnufhgPkOuw/z0OHiaZkajJPjxfgC63bl2paNG1ZmJ8UAEqkSDlhNxmRa9UqG+1ZewJASaQxz6gwCCNLM1SkfjuM/hPh1JAOh9jUUleJQF5MXx9RSho/VBQnorB3vbutaOQzw0yPLtDtSPKX8sVdhkveVQJAIDsJP5X8Tey6zXTUISor7PF0TSiKdE4k0IwKoy9y8HmQ+AU8+xyr/iOt5lvaGxKlBK8N/7yCw5H4qHnJaHT+Bg==";
			String requestPwd = RSA.decryptByPrivate(requestUser.getPassword(),privateKey);
			String userPwd = RSA.decryptByPrivate(user.getPassword(),privateKey);
//			System.out.println("[requestPwd]:"+requestPwd);
//			System.out.println("[userPwd]:"+userPwd);
//			System.out.println(requestPwd.equals(userPwd));
			if(!requestPwd.equals(userPwd)){
				return false;
			}
			return true;
		}
	}



}
