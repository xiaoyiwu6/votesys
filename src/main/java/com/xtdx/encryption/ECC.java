package com.xtdx.encryption;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: vote
 * @description: EC密钥生成器
 * @author: LEO
 * @create: 2021-04-03 18:04
 **/
public class ECC {

    private final static int KEY_SIZE = 256;//bit
    private final static String SIGNATURE = "SHA256withECDSA";
    private final static String KEY_ECC = "EC";
    private final static String BOUNCY_CASTLE = "BC";
    //定义公钥关键词
    public static final String KEY_ECC_PUBLICKEY = "ECCPublicKey";
    //定义私钥关键词
    public static final String KEY_ECC_PRIVATEKEY = "ECCPrivateKey";

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private static void printProvider() {
        Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        for (Provider.Service service : provider.getServices()) {
            System.out.println(service.getType() + ": "
                    + service.getAlgorithm());
        }
    }

    //测试
    public static void main(String[] args) {
        Map<String,Object> map = ECC.getKeyPair();
        System.out.println("[pubKey]:\n" + getPublicKey(map));
        System.out.println("[priKey]:\n" + getPrivateKey(map));
//        try {
////            KeyPair keyPair = getKeyPair2();
////            ECPublicKey pubKey = (ECPublicKey) keyPair.getPublic();
////            ECPrivateKey priKey = (ECPrivateKey) keyPair.getPrivate();
//            Map<String,Object> map = ECC.getKeyPair();
//            System.out.println("[pubKey]:\n" + getPublicKey(map));
//            System.out.println("[priKey]:\n" + getPrivateKey(map));
//
//            //测试文本
//            String content = "abcdefg";
//
//            //加密
//            byte[] cipherTxt = encrypt(content.getBytes(), pubKey);
//            //解密
//            byte[] clearTxt = decrypt(cipherTxt, priKey);
//            //打印
//            System.out.println("content:" + content);
//            System.out.println("cipherTxt["+cipherTxt.length+"]:" + new String(cipherTxt));
//            System.out.println("clearTxt:" + new String(clearTxt));
//
//            //签名
//            byte[] sign = sign(content, priKey);
//            //验签
//            boolean ret = verify(content, sign, pubKey);
//            //打印
//            System.out.println("content:" + content);
//            System.out.println("sign["+sign.length+"]:" + new String(sign));
//            System.out.println("verify:" + ret);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("[main]-Exception:" + e.toString());
//        }
    }

    /**
     * BASE64 解码
     * @param key 需要Base64解码的字符串
     * @return 字节数组
     */
    public static byte[] decryptBase64(String key) {
        return Base64.getDecoder().decode(key);
    }

    /**
     * BASE64 编码
     * @param key 需要Base64编码的字节数组
     * @return 字符串
     */
    public static String encryptBase64(byte[] key) {
        return new String(Base64.getEncoder().encode(key));
    }


    //生成秘钥对
    public static KeyPair getKeyPair2() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ECC, BOUNCY_CASTLE);//BouncyCastle
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }

    /**
     * 生成秘钥对
     * @return Map<String, Object>
     * @throws Exception
     */
    public static  Map<String, Object> getKeyPair() {
        Map<String, Object> map = null;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ECC, BOUNCY_CASTLE);//BouncyCastle
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 获取公钥
            ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
            // 获取私钥
            ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
            // 将密钥对封装为Map
            map = new HashMap<String, Object>();
            map.put(KEY_ECC_PUBLICKEY, publicKey);
            map.put(KEY_ECC_PRIVATEKEY, privateKey);
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 获取公钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair) {
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return encryptBase64(bytes);
    }

    /**
     * 获取公钥(Base64编码)
     * @param map
     * @return
     */
    public static String getPublicKey(Map<String,Object> map) {
        Key publicKey = (Key) map.get(KEY_ECC_PUBLICKEY);
        byte[] bytes = publicKey.getEncoded();
        return encryptBase64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair) {
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return encryptBase64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     * @param map
     * @return
     */
    public static String getPrivateKey(Map<String,Object> map) {
        Key privateKey = (Key) map.get(KEY_ECC_PRIVATEKEY);
        byte[] bytes = privateKey.getEncoded();
        return encryptBase64(bytes);
    }

    //公钥加密
    public static byte[] encrypt(byte[] content, ECPublicKey pubKey) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, ECPrivateKey priKey) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher.doFinal(content);
    }

    /**
     * 签名
     * @param content
     * @param priKey
     * @return String
     * @throws Exception
     */
    public static String sign2(String content, ECPrivateKey priKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE);//"SHA256withECDSA"
        signature.initSign(priKey);
        signature.update(content.getBytes());
        return encryptBase64(signature.sign());
    }

    public static byte[] sign(String content, ECPrivateKey priKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE);//"SHA256withECDSA"
        signature.initSign(priKey);
        signature.update(content.getBytes());
        return signature.sign();
    }

    /**
     * 签名验证
     * @param content
     * @param sign
     * @param pubKey
     * @return boolean
     * @throws Exception
     */
    public static boolean verify(String content, byte[] sign, ECPublicKey pubKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE);//"SHA256withECDSA"
        signature.initVerify(pubKey);
        signature.update(content.getBytes());
        return signature.verify(sign);
    }
    public static boolean verify(String encryptedStr, String publicKey, String sign) throws Exception {
        return false;
    }

    /**
     * 解析证书的签名算法，单独一本公钥或者私钥是无法解析的，证书的内容远不止公钥或者私钥
     * */
    private static String getSigAlgName(File certFile) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
        X509Certificate x509Certificate = (X509Certificate) cf.generateCertificate(new FileInputStream(certFile));
        return x509Certificate.getSigAlgName();
    }
}