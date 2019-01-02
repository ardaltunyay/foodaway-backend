package com.tb.bimo.util;

import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.Base64;

@RequiredArgsConstructor
public class TokenGenerator {

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[5];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getMimeEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

}
