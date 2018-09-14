package help;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class PrivateKeyReader 
{
	public static void main(String[] args) throws Exception 
	{
		FileInputStream is = new FileInputStream("keystore.jks");
	    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
	    keystore.load(is, "12345678".toCharArray());

	    String alias = "domain";

	    Key key = keystore.getKey(alias, "12345678".toCharArray());
	    if (key instanceof PrivateKey) 
	    {
	      // Get certificate of public key
	      Certificate cert = keystore.getCertificate(alias);
	      // Get public key
	      PublicKey publicKey = cert.getPublicKey();
	      new KeyPair(publicKey, (PrivateKey) key);
	    }

	}

}
