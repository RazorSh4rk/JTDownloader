import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class JTDownloader {

	private String URL, PATH;
	private static String downloadURL = "http://www.convertmp3.io/fetch/?video=";
	private static String infoURL = "http://www.convertmp3.io/fetch/?format=text&video=";

	public JTDownloader(String URL, String PATH){
		this.URL = URL;
		this.PATH = PATH;
	}
	
	private String getTitle(){
		String ret = "";
		
		try{
			URL url = new URL(makeInfoLink());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = reader.readLine()) != null){
				ret += line;
			}
			reader.close();
		}catch(Exception e){
			return "Sample title";
		}
		
		return ret.substring(7, ret.indexOf("<"));
	}
	
	public void download(){
		OutputStream output = null;
		InputStream inStream = null;
		URL url;
		URLConnection conn;
		byte[] buffer;
		int byteRead, byteWritten = 0;
		
		try{
			url = new URL(makeDownloadLink());
			output = new BufferedOutputStream(new FileOutputStream(PATH + getTitle() + ".mp3"));
			conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			inStream = conn.getInputStream();
			buffer = new byte[1024];
			while((byteRead = inStream.read(buffer)) != -1){
				output.write(buffer, 0, byteRead);
				byteWritten += byteRead;
			}
		}catch(Exception e){
			System.err.println("Exception during file download.");
			e.printStackTrace();
		}finally{
			try{
				output.close();
				inStream.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String makeDownloadLink(){ return downloadURL + URL; }
	
	private String makeInfoLink(){ return infoURL + URL; }
	
	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
