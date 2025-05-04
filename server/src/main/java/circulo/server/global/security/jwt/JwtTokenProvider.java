package circulo.server.global.security.jwt;

import circulo.server.global.apiPayload.code.exception.custom.AuthException;
import circulo.server.global.apiPayload.code.exception.custom.TokenException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final long ACCESS_TOKEN_DURATION = 1000 * 60 * 30L; // 30분
    private static final long REFRESH_TOKEN_DURATION = 1000 * 60 * 60L * 24 * 7; // 7일

    private final SecretKey secretKey;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String key, StringRedisTemplate redisTemplate) {
        byte[] keyBytes = key.getBytes();
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.redisTemplate = redisTemplate;
    }

    // 토큰 생성 (공통 메서드)
    public TokenInfo generateToken(Long userId) {
        String accessToken = generateAccessToken(userId);
        String refreshToken = generateRefreshToken(userId);

        redisTemplate.opsForValue().set("refresh:" + userId, refreshToken, REFRESH_TOKEN_DURATION, TimeUnit.MILLISECONDS);

        return new TokenInfo("Bearer", accessToken, refreshToken);
    }

    // Access Token 생성
    private String generateAccessToken(Long userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + ACCESS_TOKEN_DURATION);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("auth", "ROLE_USER")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    private String generateRefreshToken(Long userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + REFRESH_TOKEN_DURATION);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("auth", "ROLE_USER")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 유효성 검사
    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        try {
            Claims claims = parseClaims(token);
            String userId = claims.getSubject();

            // 로그아웃된 토큰인지 Redis 확인
            return !Boolean.TRUE.equals(redisTemplate.hasKey("logout:" + userId));
        } catch (JwtException e) {
            return false;
        }
    }

    // refreshToken 검증 후 재발급
    public TokenInfo reissueToken(String refreshToken) {
        Claims claims = parseClaims(refreshToken);
        String userId = claims.getSubject();

        String storedRefreshToken = redisTemplate.opsForValue().get("refresh:" + userId);
        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new TokenException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }

        redisTemplate.delete("refresh:" + userId); // 기존 Refresh 삭제
        return generateToken(Long.parseLong(userId));
    }

    // 인증 정보 가져오기
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        String userId = claims.getSubject();

        if (!StringUtils.hasText(userId) || claims.get("auth") == null) {
            throw new TokenException(ErrorStatus.INVALID_TOKEN);
        }

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(claims.get("auth").toString())
        );

        UserDetails userDetails = new User(userId, "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    // Claims 파싱
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenException(ErrorStatus.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new AuthException(ErrorStatus.INVALID_TOKEN);
        }
    }
}
