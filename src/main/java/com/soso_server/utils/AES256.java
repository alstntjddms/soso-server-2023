package com.soso_server.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {

    BufferedReader reader = null;
    public static String alg = "AES/CBC/PKCS5Padding";
    private String key = null;
    private String iv = null; // 16byte

    public AES256() throws IOException {
        reader = new BufferedReader(new FileReader("C://key/AES256.txt"));

        key = reader.readLine();
        iv = key.substring(0, 16);
    }


    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

    public String encryptEncodeReplace(String text) throws Exception {
        return URLEncoder.encode(encrypt(text), "UTF-8").replaceAll("%", "MSJSM");
    }

    public String replaceDecodeDecryt(String text) throws Exception {
        return decrypt(URLDecoder.decode(text.replaceAll("MSJSM", "%"), "UTF-8"));
    }

}