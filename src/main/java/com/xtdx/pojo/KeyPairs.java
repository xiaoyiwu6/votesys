package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 密钥实体类
 * @author: LEO
 * @create: 2021-05-19 23:20
 **/
public class KeyPairs {
    private int userId;
    private String rsaPubKey;
    private String rsaPriKey;
    private String eccPubKey;
    private String eccPriKey;

    @Override
    public String toString() {
        return "KeyPairs{" +
                "userId=" + userId +
                ", rsaPubKey='" + rsaPubKey + '\'' +
                ", rsaPriKey='" + rsaPriKey + '\'' +
                ", eccPubKey='" + eccPubKey + '\'' +
                ", eccPriKey='" + eccPriKey + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public KeyPairs(int userId, String rsaPubKey, String rsaPriKey, String eccPubKey, String eccPriKey) {
        this.userId = userId;
        this.rsaPubKey = rsaPubKey;
        this.rsaPriKey = rsaPriKey;
        this.eccPubKey = eccPubKey;
        this.eccPriKey = eccPriKey;
    }

    public String getRsaPubKey() {
        return rsaPubKey;
    }

    public void setRsaPubKey(String rsaPubKey) {
        this.rsaPubKey = rsaPubKey;
    }

    public String getRsaPriKey() {
        return rsaPriKey;
    }

    public void setRsaPriKey(String rsaPriKey) {
        this.rsaPriKey = rsaPriKey;
    }

    public String getEccPubKey() {
        return eccPubKey;
    }

    public void setEccPubKey(String eccPubKey) {
        this.eccPubKey = eccPubKey;
    }

    public String getEccPriKey() {
        return eccPriKey;
    }

    public void setEccPriKey(String eccPriKey) {
        this.eccPriKey = eccPriKey;
    }

    public KeyPairs() {
    }

}
