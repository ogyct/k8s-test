package com.example.k8stest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Arrays;

@RestController
class TestController {
    @Value("${my.property}")
    private String prop;
    @Value("${info.app.version:unknown}")
    private String version;


    @GetMapping("/")
    public String hello() {
        return version;
    }

    @GetMapping("/heavy")
    public String heavyCompute() {
        return Arrays.toString(
                hashPassword(Instant.now().toString().toCharArray(),
                        "salt".getBytes(),
                        1000000,
                        512));
    }

    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/property")
    public String getProperty() {
        return prop;
    }
}
