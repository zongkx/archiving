package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//令牌的生成和解析
public class JwtUtil {
    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();
        map.put("self","aaa");

        JwtBuilder builder = Jwts.builder()
                .setId("Paul")
                .setSubject("AAA")
                .setIssuedAt(new Date())//颁发时间
                .addClaims(map)//载荷中自定义的部分
                .setExpiration(new Date(System.currentTimeMillis()+2000))//过期时间 20秒
                .signWith(SignatureAlgorithm.HS256,"itcast");//加密算法和密钥
//        System.out.println(builder.compact());
        String token = builder.compact();

        String t =  token;
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws(t)
                .getBody();
        System.out.println(claims.toString());


    }


    public static final Long JWT_TTL = 3600000L ;
    public static final String JWT_KEY = "itcast";

    public static String createJWT(String id,String subject,Long ttl){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        if(ttl == null){
            ttl = JWT_TTL;
        }
        long expMills = nowMills+ttl;
        Date expDate = new Date(expMills);

        SecretKey secretKey = getKey();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);
        return jwtBuilder.compact();

    }

    private static SecretKey getKey() {
        byte[] encodeKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey secretKey = new SecretKeySpec(encodeKey,0, encodeKey.length,"AES");
        return secretKey;


    }
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = getKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
