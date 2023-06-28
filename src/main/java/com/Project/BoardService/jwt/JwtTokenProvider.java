package com.Project.BoardService.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //토큰 암호화 비밀 키 (64글자 이상)
    @Value("${jwt.secretKey}")
    private String secretKey;
    //토큰 유효시간 (30분)
    private final long validTime = 1000L*30*60;
    //비밀 키 인코딩 -> Base46 이용
    @PostConstruct
    private void secretKeyEncoding(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //JWT 토큰 생성
    public LogInResponse createToken(Long id){

        Date now = new Date();

        //JWT payload 단위
        Claims claims = Jwts.claims();
        claims.put("userId", id);

        //JWT 토큰 생성
        String accessToken = Jwts.builder()
                .setClaims(claims)                                         //payload
                .setIssuedAt(now)                                          //토큰 발행 시간
                .setExpiration(new Date(now.getTime() + validTime))       //인증 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)            //암호화 알고리즘 & secretKey
                .compact();

        return LogInResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    //JWT 토큰 복호화
    public Long getMemberId(HttpServletRequest request){

        //요청에서 토큰 GET
        String accessToken = request.getHeader("Token");

        //토큰 payload GET
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();

        return claims.get("userId", Long.class);
    }

}
