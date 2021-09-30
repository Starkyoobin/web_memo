package com.starkyb.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starkyb.memo.post.model.Post;

@Repository
public interface PostDAO {
	public int insertPost(
			@Param("userId") int userId
			,@Param("subject") String subject
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public List<Post> selectMemoList(@Param("userId") int userId);
	
	public Post selectMemo(
			@Param("id") int id
			, @Param("userId") int userId);
	//삭제하기
	public int deleteMemo(
			@Param("id") int id
			, @Param("userId") int userId);
	//수정하기
	public int updateMemo(
			@Param("id") int id
			, @Param("subject") String subject
			, @Param("content") String content
			, @Param("userId") int userId);
}