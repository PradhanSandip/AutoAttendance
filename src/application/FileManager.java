package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileManager {
	static String userData = ".//src//application//userData.dat";
	
	//check if user exist
	public boolean isUserExist() {
		File userFile = new File(userData);
		if(userFile.exists() && !userFile.isDirectory()) {
			return true;
		}else {
			return false;
		}
	}
	
	//check if courses exist
	public boolean isCourseExist() {
		return false;
	}
	
	public void writeBytes(String text) throws IOException {
		if(!isUserExist()) {
			File f = new File(userData);
			f.createNewFile();
		}
		try {
			FileOutputStream out = new FileOutputStream(userData);
			byte[] b = text.getBytes();
			out.write(b);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String readBytes() throws IOException{
		FileInputStream in = new FileInputStream(userData);
		String string = new String(in.readAllBytes(), Charset.forName("UTF-8"));
		return string;
	}
	
	
	
	
}
