package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	boolean boardWrite(BoardDto board);

	boolean boardWriteSelectKey(BoardDto board);

	boolean fileInsert(Map<String, String> fMap);

	String[] get_bf_list(Integer b_num);

	
	boolean delete_bf(Integer b_num);

	boolean reply_delete(Integer b_num);

	int getBoardExist(Integer b_num);

	boolean board_delete();


}
