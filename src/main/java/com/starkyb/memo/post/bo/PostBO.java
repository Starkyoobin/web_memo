package com.starkyb.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.starkyb.memo.common.FileManagerService;
import com.starkyb.memo.post.dao.PostDAO;
import com.starkyb.memo.post.model.Post;

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
	
	public List<Post> getMemoList(int userId) {
		return postDAO.selectMemoList(userId);
	}
	
	public Post getMemo(int id, int userId) {
		return postDAO.selectMemo(id, userId);
	}
}
