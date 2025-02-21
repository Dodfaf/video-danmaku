package com.videodanmaku.auth.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class DruidUtil {

    private static String publicKey;
    private static String privateKey;

    static{
        try {
            //生成字符串
            String[] strings = ConfigTools.genKeyPair(512);
            privateKey = strings[0];
            System.out.println("privateKey: " + privateKey);
            publicKey = strings[1];
            System.out.println("公钥： " + publicKey);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainKey) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainKey);
        System.out.println("encrypt: " + encrypt);
        return encrypt;
    }
    public static String decrypt(String encryptTest) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptTest);
        System.out.println("decrypt: " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("xdf_danmakuvideo");
        System.out.println("encrypt: " + encrypt);

    }

}
