package gdut.ff.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author liuffei
 *
 */
public class HttpUtil {

	private static String httpRequest(String requestUrl) {
		StringBuffer stringBuffer = null;
		BufferedReader bufferReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConn = null;
		
		try {
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputStreamReader);
			stringBuffer = new StringBuffer();
			String str = null;
			while((str = bufferReader.readLine()) != null) {
				stringBuffer.append(str);
			}
			
			if(null != bufferReader) {
				try {
					bufferReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != inputStreamReader) {
				try {
					inputStreamReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != inputStream) {
				try {
					inputStream.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != httpUrlConn) {
				try {
					httpUrlConn.disconnect();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
}
