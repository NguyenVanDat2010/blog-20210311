package com.kira.blog.utils;

import com.kira.blog.ciphers.RsaUtils;
import com.kira.blog.constant.JwtConst;
import com.kira.blog.domain.JwtMobilePayload;
import com.kira.blog.domain.JwtPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * JWT Utils
 **/
public class JwtUtils {

    /**
     * private key encrypt token
     */
    public static String generateToken(JwtPayload payload, String priKey, long expireMinutes) throws Exception {
        return Jwts.builder()
                //.claim(JwtConst.USER_UUID, payload.getUserUuid())
                .claim(JwtConst.USERNAME, payload.getUsername())
                .claim(JwtConst.ROLE_RIGHT, payload.getRoleRight())
                .claim(JwtConst.ROLE_STATUS, payload.getRoleStatus())
                .setExpiration(Date.from(LocalDateTime.now()
                        .plusMinutes(expireMinutes)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(priKey))
                .compact();
    }

    public static String generateMobileToken(JwtMobilePayload payload, String priKey, long expireMinutes) throws Exception {
        return Jwts.builder()
                //.claim(JwtConst.DEVICE_ID, payload.getDeviceId())
                .setExpiration(Date.from(LocalDateTime.now()
                        .plusMinutes(expireMinutes)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(priKey))
                .compact();
    }

    /**
     * public key decrypt token
     */
    private static Jws<Claims> parserToken(String token, String pubKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(pubKey)).parseClaimsJws(token);
    }

    /**
     * Receive user info from token
     */
    public static JwtPayload getInfoFromToken(String token, String pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        //Map<String, String> userRoles = (Map<String, String>) body.get(JwtConst.USER_ROLES);
        return new JwtPayload(
                //String.valueOf(body.get(JwtConst.DEVICE_ID)),
                //String.valueOf(body.get(JwtConst.USER_UUID)),
                String.valueOf(body.get(JwtConst.USERNAME)),
                String.valueOf(body.get(JwtConst.ROLE_RIGHT)),
                String.valueOf(body.get(JwtConst.ROLE_STATUS))
        );
    }

    /**
     * Extract JWT Mobile token info
     */
    public static JwtMobilePayload getInfoFromMobileToken(String token, String pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JwtMobilePayload(
                //String.valueOf(body.get(JwtConst.DEVICE_ID))
        );
    }

}