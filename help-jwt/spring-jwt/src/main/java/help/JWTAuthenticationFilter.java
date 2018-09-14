package help;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter 
{

    private String publicKey;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String publicKey) 
    {
        super(authenticationManager);
        this.publicKey = publicKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        //判断是否有token
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken;
		try {
			authenticationToken = getAuthentication(token);
	        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

	        //放行
	        chain.doFilter(request, response);
		} catch (Exception e) 
		{
			throw new ServletException(e);

		}




    }
    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws Exception{


        Claims claims = Jwts.parser().setSigningKey(RSAUtil.publicKeyFromBase64(publicKey))
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        String username = claims.getSubject();

        Date expiration = claims.getExpiration();

      /*  Date now = new Date();

        if (now.getTime() > expiration.getTime()) {

            throw new RuntimeException("该账号已过期,请重新登陆");
        }*/


        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null,new ArrayList<GrantedAuthority>());
        }
        return null;
    }


}