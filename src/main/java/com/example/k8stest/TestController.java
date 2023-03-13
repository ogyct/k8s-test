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

    private final TestService testService;

    TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("/")
    public String hello() {
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
            byte[] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/property")
    public String getProperty() {
        return prop;
    }
}
