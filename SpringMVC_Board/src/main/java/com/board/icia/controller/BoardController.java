package com.board.icia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dto.BoardDto;
import com.board.icia.dto.MemberDto;
import com.board.icia.dto.ReplyDto;
import com.board.icia.service.BoardMM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired //게시판 서비스 클래스
	private BoardMM bm;
	
	@GetMapping("write")
	public String write() {
		return "write";
	}
	
//	@PostMapping("write")
//	public ModelAndView write(BoardDto board,HttpSession session) {
//		log.info("board: "+board.toString());
//		log.info("board: "+board.getAttachments().toString());
//		
//		return null;
//		/*
//		 * board.setB_id(session.getAttribute("id").toString()); boolean
//		 * result=bm.boardWrite(board);
//		 * 
//		 * if(result) { return new ModelAndView("redirect:/board/list"); //get만 }else {
//		 * return new ModelAndView("redirect:/board/write"); //get만 }
//		 */
//	}
	
//	첨부파일, 게시판 데이터와 request 객체 정보를 한꺼번에 받음
	@PostMapping("write")
	public ModelAndView write(MultipartHttpServletRequest multi) {
//		BoardDto board=new BoardDto();
//		board.setB_contents(multi.getParameter("b_contents"));
//		board.setB_title(multi.getParameter("b_title"));
//		log.info("board: "+board);
//		List<MultipartFile> attachments = multi.getFiles("attachments");
//		log.info("files: "+attachments);
		
		boolean result = bm.boardWrite(multi);
		if(result) {
			return new ModelAndView("redirect:/board/list");
		}else {
			return new ModelAndView("redirect:/board/write");
		}
	}
	
	
	@GetMapping("/list")
	public ModelAndView boardList(@RequestParam(defaultValue = "1") Integer pageNum
			,MemberDto member
			){
		log.info("pnum:{}",pageNum);
		log.info("member:{}",member);
//		log.info("json:{}",json);
		List<BoardDto> bList=bm.getBoardList(pageNum);
		String pageHtml=bm.getPaging(pageNum);
				
//		new ModelAndView("boardList").addObject("bList",new Gson().toString(bList));
		return new ModelAndView("boardList").addObject("bList",bList)
			.addObject("member", member) 
			.addObject("paging",pageHtml);
		////jstl활용
		//.addObject("json", json);
//		log.info(bList.toString());
		//return "boardList";
	}
	
	@GetMapping("/contents")
	public ModelAndView getContents(Integer b_num) {
		if(b_num==null) { /*최신글 가져오기 */}
		log.info("b_num:"+b_num);
		BoardDto board=bm.getContents(b_num);
		List<ReplyDto> rList=bm.getReplyList(b_num);
		
		log.info("BM board: "+board);
		if(board!=null) {
			return new ModelAndView("boardContentsAjax")
					.addObject("board",board)
					.addObject("rList",rList);
		}else {
			return new ModelAndView("redirect:/board/list"); 
		}
	}
}
