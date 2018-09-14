package help;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
public class App 
{

	public static void main(String[] args) throws Exception 
	{
		SpringApplication.run(App.class, args);
		URL u = ResourceUtils.getURL("classpath:input.txt");
		InputStream is = u.openStream();
		String url = IOUtils.toString(is, "utf8");
		is.close();
		String xml = new String(Base64.getDecoder().decode(url),"utf8");
		//String base64 = URLDecoder.decode(url, "UTF-8");
		//byte[] compressed = Base64.getDecoder().decode(base64);
		//byte[] utf8 = CompressUtil.decompress(compressed);
		//String xml = new String(utf8, "utf8");
		
		FileUtils.writeStringToFile(new File("src/main/resources/output.xml"), xml,"utf8");
	}

}
