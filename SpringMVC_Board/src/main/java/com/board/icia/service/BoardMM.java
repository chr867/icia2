package com.board.icia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.BoardDto;
import com.board.icia.dto.ReplyDto;
import com.board.icia.userClass.FileManager;
import com.board.icia.userClass.Paging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardMM {
	@Autowired
	private IBoardDao bDao;

	@Autowired
	private FileManager fM;
	
	
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

	public boolean boardWrite(BoardDto board) {
		return bDao.boardWrite(board);
	}

	public boolean boardWrite(MultipartHttpServletRequest multi, Integer fileCheck) {
//1개 file tag에 여러개의 파일을 첨부할 때
//		for(MultipartFile file : attachments) {
//			System.out.println("file name="+file.getOriginalFilename());
//		}
		String id=multi.getSession().getAttribute("id").toString();
		String title=multi.getParameter("b_title");
		String contents=multi.getParameter("b_contents");
		BoardDto board=new BoardDto();
		board.setB_title(title).setB_contents(contents).setB_id(id);
		boolean result=bDao.boardWriteSelectKey(board); //글번호 100을 게시 후 글번호를 가져옴
		System.out.println("board="+board);
		if(result) { //글쓰기 성공 -> 파일 업로드
			if(fileCheck==1) {
				if(fM.fileUp(multi,board.getB_num()));
				System.out.println("upload OK");
				return true; //첨부+글쓰기 성공
			}
			return true; //첨부없이 글쓰기 성공
		}
		return false;
	}
}
