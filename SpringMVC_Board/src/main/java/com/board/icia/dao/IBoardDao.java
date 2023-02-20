package com.board.icia.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.board.icia.dto.BoardDto;
import com.board.icia.dto.ReplyDto;

public interface IBoardDao {

	List<BoardDto> getBoardList(Integer pageNum);
	
	@Select("SELECT COUNT(*) FROM B")
	int getBoardCount();

	BoardDto getnContents(Integer b_num);
	
	int updateViews(Integer b_num);

	List<ReplyDto> getReplyList(Integer b_num);

	boolean reply_insert(ReplyDto reply);

}
