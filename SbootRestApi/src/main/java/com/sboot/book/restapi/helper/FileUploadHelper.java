package com.sboot.book.restapi.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
 //public final String UPLOAD_DIR = "C:\\Users\\Ajay's\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\SbootRestApi\\src\\main\\resources\\static\\images";
 
 public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();

 public FileUploadHelper() throws IOException{
	 
 }
 public boolean uplaodFile(MultipartFile f) {
	 boolean flag = false;
	 try {
//		InputStream is  = f.getInputStream();
//		byte data[] = new byte[is.available()];
//		is.read(data);
//		FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+f.getOriginalFilename());
//		fos.write(data);
//		fos.flush();
//		fos.close();
//		flag = true;
		 
		Files.copy(f.getInputStream(),Paths.get( UPLOAD_DIR+File.separator+f.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		flag = true;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return flag;
 }

}
