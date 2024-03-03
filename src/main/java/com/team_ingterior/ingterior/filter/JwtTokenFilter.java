// package com.team_ingterior.ingterior.filter;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.Jwts;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.Collections;

// public class JwtTokenFilter extends OncePerRequestFilter {

//     @Value("${jwt.secretKey}")
//     private String secretKey; 

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = request.getHeader("Authorization");
//         if (token != null && token.startsWith("Bearer ")) {
//             token = token.substring(7);
//             try {
//                 Claims claims = Jwts.parserBuilder()
//                         .setSigningKey(secretKey.getBytes())
//                         .build()
//                         .parseClaimsJws(token)
//                         .getBody();

//                 String username = claims.getSubject();
//                 if (username != null) {
//                     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
//                     SecurityContextHolder.getContext().setAuthentication(auth);
//                 }
//             } catch (Exception e) {
//                 SecurityContextHolder.clearContext(); // 토큰 검증 실패 시, 보안 컨텍스트 클리어
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
// }
