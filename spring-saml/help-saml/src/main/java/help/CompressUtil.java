package help;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.io.IOUtils;

public class CompressUtil 
{
	public static byte[] compress(byte[] content)
	{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
        	InflaterOutputStream gzipOutputStream = new InflaterOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.printf("Compressiono %f\n", (1.0f * content.length/byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompress(byte[] contentBytes){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
        	Inflater def = new Inflater(true);
            IOUtils.copy(new InflaterInputStream(new ByteArrayInputStream(contentBytes), def), out);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }
}
