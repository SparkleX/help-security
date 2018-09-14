package help;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil 
{
	static String toBase64(Key key)
	{
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	static PrivateKey privateKeyFromBase64(String base64) throws Exception
	{
	    byte[] decoded = Base64.getDecoder().decode(base64);
	    PKCS8EncodedKeySpec  spec =new PKCS8EncodedKeySpec (decoded);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    return kf.generatePrivate(spec);
	}
	
	static PublicKey publicKeyFromBase64(String base64) throws Exception
	{
	    byte[] decoded = Base64.getDecoder().decode(base64);
	    X509EncodedKeySpec spec =new X509EncodedKeySpec(decoded);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    return kf.generatePublic(spec);
	}
}
