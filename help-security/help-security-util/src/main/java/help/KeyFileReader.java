package help;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class KeyFileReader 
{
	public static void main(String[] args) throws Exception 
	{
	    CertificateFactory fact = CertificateFactory.getInstance("X.509");
	    FileInputStream is = new FileInputStream ("domain.pem");
	    X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
	    PublicKey key = cer.getPublicKey();
	    System.out.println(key);	    
	}
}
