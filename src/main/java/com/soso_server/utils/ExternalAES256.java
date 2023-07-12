package com.soso_server.utils;

import org.jboss.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

public class ExternalAES256 {
    private static final Logger logger = Logger.getLogger(ExternalAES256.class);

    public static String alg = "AES/CBC/PKCS5Padding";
    private String key = "";
    private String iv = ""; // 16byte

    public ExternalAES256() throws IOException {
        logger.info("[ExternalAES256] ExternalAES256 KEY Load...");

//        BufferedReader bufferReader = new BufferedReader(new FileReader("C:\\key\\ExternalAES256.txt"));
        key = "70890874578974573986234987792344";
        iv = key.substring(0, 16);
//        bufferReader.close();

        logger.info("[ExternalAES256] ExternalAES256 KEY Load Success");
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