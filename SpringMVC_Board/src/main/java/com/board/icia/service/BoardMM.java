package com.board.icia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.BoardDto;
import com.board.icia.dto.ReplyDto;
import com.board.icia.userClass.Paging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardMM {
	@Autowired
	private IBoardDao bDao;

	public List<BoardDto> getBoardList(Integer pageNum) {
		List<BoardDto> bList=null;
		//if(pageNum==null) pageNum=1;
		bList=bDao.getBoardList(pageNum);
		log.info(bList.toString());
		return bList;
	}

	public String getPaging(Integer pageNum) {
		int maxNum = bDao.getBoardCount(); //전체 글 갯수
		int listCount =10; //페이지당 글의 개수
		int pageCount=2; //그룹당 페이지 개수
		String boardName ="/board/list"; //url
		Paging paging=new Paging(maxNum,pageNum,listCount,pageCount,boardName);
		return paging.makeHtmlPaging();
	}

	public BoardDto getContents(Integer b_num) {
		bDao.updateViews(b_num);
		BoardDto board=bDao.getnContents(b_num);
		//댓글 리스트
		
		return board;
	}

	public List<ReplyDto> getReplyList(Integer b_num) {
//		List<ReplyDto> rList=null;
//		bDao.getReplyList(b_num);
		return bDao.getReplyList(b_num);
	}

	public List<ReplyDto> reply_insert(ReplyDto reply) {
		if(bDao.reply_insert(reply)) {
			return bDao.getReplyList(reply.getR_bnum());
		}
			return null;
	}
}
