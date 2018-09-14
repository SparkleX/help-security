package help;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Generate {

	public static void main(String[] args) 
	{
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
                .signWith(SignatureAlgorithm.HS512, "123456789")
                .compact();
        
        System.out.println(str);
        return str;
		
	}
	public static void parse(String token)
	{
        String username = (String) Jwts.parser().setSigningKey("123456789")
		                .parseClaimsJws(token)
		                .getBody().toString();
		                //.getSubject();
        System.out.println(username);

	}
}
