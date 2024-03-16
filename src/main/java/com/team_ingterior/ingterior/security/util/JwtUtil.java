package com.team_ingterior.ingterior.security.util;
// package com.team_ingterior.ingterior.util;

// import java.util.Date;

// import javax.crypto.SecretKey;

// import org.springframework.beans.factory.annotation.Value;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;

// public class JwtUtil {

//     @Value("${jwt.secretKey}")
//     public static String secretKey;  // 실제 사용시 충분히 긴 키를 사용하세요.
//     private static final long EXPIRATION_TIME = 86400000; 

//     public static String generateToken(String username) {
//         SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuer("ingTerior")
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(key) // 업데이트된 signWith 사용
//                 .compact();
//     }
// }