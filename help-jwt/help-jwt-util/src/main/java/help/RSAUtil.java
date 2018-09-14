package help;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
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
	
	static PrivateKey readPrivateKey(String file, String password, String alias, String aliasPassword) throws Exception
	{
		FileInputStream is = new FileInputStream("keystore.jks");
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(is, password.toCharArray());
		Key key = keystore.getKey(alias, aliasPassword.toCharArray());
		return (PrivateKey) key;
	}
	
	static PublicKey readPublicKey(String file) throws Exception
	{
	    CertificateFactory fact = CertificateFactory.getInstance("X.509");
	    FileInputStream is = new FileInputStream (file);
	    X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
	    PublicKey key = cer.getPublicKey();
	    return key;
	}
}
