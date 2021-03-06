package com.starkyb.memo.common;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\yb010\\Desktop\\Web\\SPRINGTEST\\memo\\images/";
	
	public static String saveFile(int userId, MultipartFile file) {
		//파일 경로
		//1. 게시자의 id로 구분해서 저장
		//2. 게시 시간을 기준으로 구분
//		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		//디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			//디렉토리 생성 에러
//			logger.error("[FileManagerService saveFile] 디렉토리 생성 에러");
			return null;
		}
		
		//byte[]
		try {
			byte[] bytes = file.getBytes();
			//파일 경로
			Path path = Paths.get(filePath + file.getOriginalFilename());
			//파일 저장
			Files.write(path, bytes);
		} catch (IOException e) {
//			logger.error("[FileManagerService saveFile] 파일 생성 실패");
			e.printStackTrace();
			return null;
		}
		
		//파일을 접근할수있는 경로
		//<img src="/images/43_381281298129/test.png">
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	public static void removeFile(String filePath) {
		//filePath
		//post테이블에 있는 imagePath
		//ex) /images/1-201020123/test.png
		//실제 경로 ex) C:\\Users\\yb010\\Desktop\\Web\\SPRINGTEST\\memo\\images\1-201020123\test.png
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		Path path = Paths.get(realFilePath);
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		//디렉토리(폴더) 삭제
		path = path.getParent();
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
