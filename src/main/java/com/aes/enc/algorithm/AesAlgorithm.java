package com.aes.enc.algorithm;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


@Slf4j
public class AesAlgorithm {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static   void setKey(String myKey)
    {

        try
        {
            key = myKey.getBytes(StandardCharsets.UTF_8);

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey= new SecretKeySpec(key,"AES");

        }catch (NoSuchAlgorithmException ex)
        {
            log.info("No Such Algorithm Exceptipn {} ",ex);
        }catch (Exception e)
        {
            log.info("Exception in set Key");
        }

    }



    public static String encrypt(String strToEnc, String sec){

        try{

            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e)
        {
            log.info("Exception in Encryption {} ", e);
        }
        return null;
    }


    public static String decrypt(String strToDec, String sec){

        try{
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));

        }catch (Exception e)
        {
            log.info("Exception in Encryption {} ", e);
        }
        return null;
    }


}
