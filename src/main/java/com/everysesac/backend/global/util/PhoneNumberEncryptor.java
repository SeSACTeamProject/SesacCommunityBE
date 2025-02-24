package com.everysesac.backend.global.util;

import org.springframework.context.annotation.Bean;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PhoneNumberEncryptor {

    private static final String ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 256; // AES-256
    private final SecretKey secretKey; // 암호화 및 복호화를 위한 키

    // 생성자: 새로운 키 생성
    public PhoneNumberEncryptor() throws Exception {
        this.secretKey = generateKey();
    }

    // 생성자: 기존 키로 초기화
    public PhoneNumberEncryptor(String base64Key) {
        this.secretKey = loadKey(base64Key);
    }

    // AES Secret Key 생성
    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(AES_KEY_SIZE);
        return keyGen.generateKey();
    }

    // Base64로 인코딩된 키를 SecretKey로 변환
    private SecretKey loadKey(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }

    // SecretKey를 Base64 문자열로 반환
    public String getEncodedKey() {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // 데이터 암호화
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData); // Base64로 인코딩하여 문자열로 반환
    }

    // 데이터 복호화
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }
}
