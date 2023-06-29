package com.Project.BoardService.jwt;

import io.jsonwebtoken.*;
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
    private final long validTime = 1000L * 30 * 60;
    //비밀 키 인코딩 -> Base46 이용
    @PostConstruct
    private void secretKeyEncoding() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //JWT 토큰 생성
    public LogInResponse createToken(Long id) {

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
    public Long getMemberId(HttpServletRequest request) {

        //요청에서 토큰 GET
        String accessToken = request.getHeader("Token");

        //토큰 유효성 확인
        validJWT(accessToken);

        //토큰 payload GET
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();

        return claims.get("userId", Long.class);


    }

    //토큰 유효성 확인
    public void validJWT(String accessToken) {
        try {
            //토큰 payload GET
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken);
        } catch (MalformedJwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("기한이 만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원하지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("claims 정보가 비어있습니다.");
        } catch (SignatureException e) {
            throw new JwtException("JWT 서명이 로컬로 산정된 서명과 일치하지 않습니다.");
        }
    }

}
