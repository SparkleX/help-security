package help;



import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;

public class JwtRSA {

	static PublicKey publicKey;
	static PrivateKey privateKey;	    

	public static void main(String[] args) 
	{
		KeyPair kp = RsaProvider.generateKeyPair();
		publicKey = kp.getPublic();
		privateKey = kp.getPrivate();
		    
		String key = build();
		parse(key);
	}

	public static String build()
	{
		Claims claims = Jwts.claims().setSubject("manager");
        claims.put("name", "manager");
        claims.put("role", "role");

        String str= Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();
        
        System.out.println(str);
        return str;
		
	}
	public static void parse(String token)
	{
        String username = (String) Jwts.parser().setSigningKey(publicKey)
		                .parseClaimsJws(token)
		                .getBody().toString();
		                //.getSubject();
        System.out.println(username);

	}
}
