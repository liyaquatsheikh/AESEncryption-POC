package com.aes.enc;


import com.aes.enc.algorithm.AesAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {


    public static void main(String args[]){

        SpringApplication.run(Application.class, args);

        String secretKey ="MyFirstSecretKey";
        String password="This is not my password http://github.com/";


        log.info("Original value = {} ", password);
        String strToDec = AesAlgorithm.encrypt(password,secretKey);

        log.info("Encryption = {} ",strToDec );

        String strOriginalText = AesAlgorithm.decrypt(strToDec,secretKey);
        log.info("Original Text after Decryption  = {} ", strOriginalText);



    }

}
