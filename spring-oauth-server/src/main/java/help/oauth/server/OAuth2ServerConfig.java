package help.oauth.server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter 
{
	@Autowired
	DataSource dataSource;

	/*@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception 
	{
	    clients.jdbc(dataSource);
	}
	
	  @Override
	    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	    }*/
	  
	  

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .realm("oauth2-resources")
                .tokenKeyAccess("permitAll()") //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .checkTokenAccess("isAuthenticated()") //url:/oauth/check_token allow check token
                .allowFormAuthenticationForClients();
    }
    
   /* @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception 
    {
        endpoints.authenticationManager(authenticationManager);
    }*/

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("demoApp")
                .secret("demoAppSecret")
                .redirectUris("http://baidu.com")
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
                        "password", "implicit")
                .scopes("all")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }
}