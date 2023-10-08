package com.das.consultation.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * created by jun on 2020/11/13
 * describe:AES加密解密
 * version 1.0
 */
public class AESUtil {

    private static final String VIPARA = "0102030405060708";
    private static final String BM = "UTF-8";

    public AESUtil() {
    }

    public static String encrypt(String content, String password) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = getKey(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(content.getBytes(BM));
        return parseByte2HexStr(encryptedData);
    }

    public static String decrypt(String content, String password) throws Exception {
        content = new String(content.getBytes(BM));
        byte[] decryptFrom = parseHexStr2Byte(content);
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = getKey(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(decryptFrom);
        return new String(decryptedData, BM);
    }

    private static SecretKeySpec getKey(String strKey) {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16];

        for(int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }

        return new SecretKeySpec(arrB, "AES");
    }

    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();

        for (byte b : buf) {
            String hex = Integer.toHexString(b & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }


}
