package com.xtdx.encryption;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @program: vote
 * @description: RSA密钥生成器
 * @author: LEO
 * @create: 2021-04-03 17:57
 **/
public class RSA {
    private KeyPairGenerator kePaGen=null;//密钥生成器;
    private KeyPair keyPair=null;//密钥对，用于生成公钥私钥
    private PublicKey publicKey=null;//公钥；
    private PrivateKey privateKey=null;//私钥；
    private int keySize = 1024;//密匙长

    public RSA(int keySize) {
        this.keySize= keySize;
        try{
            this.kePaGen= KeyPairGenerator.getInstance("RSA");
            this.kePaGen.initialize(this.keySize);
            this.keyPair=this.kePaGen.genKeyPair();
            this.privateKey=this.keyPair.getPrivate();
            this.publicKey=this.keyPair.getPublic();
        }catch( Exception err){
            err.printStackTrace();
        }
    }


    public RSA(){}

    //公钥字符串加密
    public static byte[] encryptRSA(String plain,PublicKey publickey)
    {
        byte[]plainByte=null;                              //获得明文的byte数组;
        byte[]cipherByte = null;                                    //产生秘闻的byte数组;
        Cipher cipher =null;
        try{
            plainByte=plain.getBytes("UTF-8");
            cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE,publickey);
            cipherByte=cipher.doFinal(plainByte);
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("error in en: "+err.toString());
        }
        return cipherByte;
    }

    //公钥字节数组加密
    public static byte[] encryptRSA(byte[] bytes,PublicKey publickey)
    {
        byte[]plainByte=null;//明文字节数组
        byte[]cipherByte = null;//密文数组，返回
        Cipher cipher =null;//密码锁，支持多种算法
        try{
            plainByte=bytes;
            cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");//选择RSA算法，无向量模式，字节填充
            cipher.init(Cipher.ENCRYPT_MODE,publickey);//选择加密模式，使用公钥加密
            cipherByte=cipher.doFinal(plainByte);//对明文加密
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("error in en: "+err.toString());
        }
        return cipherByte;
    }

    //私钥字符串解密
    public static byte[] decryptRSA(String cipherText,PrivateKey privateKey)
    {
        byte[] cipherByte =null;                             //获得秘闻的byte数组;
        byte[] plainByte   =null;                             //解密后的明文数组;
        Cipher   cipher      =null;                            //加密用;
        try{
            cipherByte       =cipherText.getBytes("UTF-8");    //统一使用该种编码方式;
            cipher =Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            plainByte=cipher.doFinal(cipherByte);
        }catch(Exception err)
        {
            err.printStackTrace();
        }
        return plainByte;
    }
    //私钥字节数组解密
    public static byte[] decryptRSA(byte[] bytes,PrivateKey privateKey)
    {
        byte[] cipherByte =null;                             //获得秘闻的byte数组;
        byte[] plainByte   =null;                             //解密后的明文数组;
        Cipher   cipher      =null;                            //加密用;
        try{
            cipherByte=bytes;    //统一使用该种编码方式;
            cipher =Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            plainByte=cipher.doFinal(cipherByte);
        }catch(Exception err)
        {
            err.printStackTrace();
        }
        return plainByte;
    }

    public PublicKey getPublicKey()
    {
        return this.publicKey;
    }
    public PrivateKey getPrivateKey()
    {
        return this.privateKey;
    }

}