package com.starkyb.memo.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.starkyb.memo.common.FileManagerService;
import com.starkyb.memo.post.dao.PostDAO;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		// 파일이 있을경우에만 저장 로직 진행
		if(file != null) {
			imagePath = FileManagerService.saveFile(userId, file);
			//saveFile에서 파일 
			if(imagePath == null) {
				return 0;
			}
		}
		
		return postDAO.insertPost(userId, subject, content, imagePath);
	}
}
