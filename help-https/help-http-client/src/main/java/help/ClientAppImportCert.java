package help;

import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class ClientAppImportCert
{
	public static void main(String[] args) throws Exception
	{
	/*	
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File("client.jks"));
		trustStore.load(instream, "123456789".toCharArray());
		instream.close();*/
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new File("client.jks"),"123456789".toCharArray()).build();
		//SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpGet httpGet = new HttpGet("https://localhost:8443/test");
		CloseableHttpResponse httpResponse = null;
		httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println(httpEntity.getContent());
		System.out.println(EntityUtils.toString(httpEntity));

	}

}
