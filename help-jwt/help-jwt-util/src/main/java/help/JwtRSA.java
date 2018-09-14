package help;



import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;

public class JwtRSA {

	static PublicKey publicKey;
	static PrivateKey privateKey;	    

	public static void main(String[] args) throws Exception 
	{
		/*KeyPair kp = RsaProvider.generateKeyPair();
		publicKey = kp.getPublic();
		privateKey = kp.getPrivate();
	
		System.out.println("private key");
		String base64PrivateKey = RSAUtil.toBase64(privateKey);
		System.out.println(base64PrivateKey);
		System.out.println("public key");
		String base64PublicKey = RSAUtil.toBase64(publicKey);
		System.out.println(base64PublicKey);
		
		publicKey =  RSAUtil.publicKeyFromBase64(base64PublicKey);
		privateKey =  RSAUtil.privateKeyFromBase64(base64PrivateKey);   */
		
		privateKey = RSAUtil.readPrivateKey("keystore.jks", "12345678", "jwt", "12345678");
		publicKey = RSAUtil.readPublicKey("jwt.pem");
		
		    
		String key = build();		
		parse(key);
	}

	public static String build()
	{
		Claims claims = Jwts.claims().setSubject("manager");
        claims.put("name", "manager");
        claims.put("role", "role");
        claims.put("company", "SBODEMOUS");
        claims.put("server", "1.1.1.1");

        String str= Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
        System.out.println("JWT Token");
        System.out.println(str);
        return str;
		
	}
	public static void parse(String token)
	{
		Jws<Claims> claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        String username = claims.getBody().toString();
		                //.getSubject();
        
        
        System.out.println("JWT JSON");
        System.out.println(username);
        System.out.println(claims.getBody().get("company"));

	}
}
