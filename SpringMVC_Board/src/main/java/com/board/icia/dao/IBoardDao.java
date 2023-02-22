package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.icia.dto.BoardDto;
import com.board.icia.dto.ReplyDto;

@Mapper
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

	@Select("SELECT BF_SYSNAME FROM BF WHERE BF_BNUM=#{b_num}")
	String[] get_bf_list(Integer b_num);

	@Select("SELECT COUNT(*) FROM B WHERE B_NUM=#{b_num}")
	int get_board_exist(Integer b_num);

	@Delete ("DELETE FROM BF WHERE BF_BNUM=#{b_num}")
	boolean bf_delete(Integer b_num);

	@Delete ("DELETE FROM R WHERE R_BNUM=#{b_num}")	
	boolean reply_delete(Integer b_num);

	@Delete ("DELETE FROM B WHERE B_NUM=#{b_num}")
	boolean board_delete(Integer b_num);

	@Select("SELECT ROWNUM,B.* FROM B ORDER BY B_NUM ASC")
	List<Integer> get_numbering();


}
