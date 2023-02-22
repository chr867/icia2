package com.board.icia.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.dto.BoardDto;
import com.board.icia.dto.MemberDto;
import com.board.icia.dto.ReplyDto;
import com.board.icia.exception.DBException;
import com.board.icia.service.BoardMM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired //게시판 서비스 클래스
	private BoardMM bm;

	@GetMapping("/delete")
	public ModelAndView board_delete(Integer b_num,RedirectAttributes attr) {
		boolean result=false;
		try {
			result=bm.board_delete(b_num);
		} catch (DBException e) {
			System.out.println(e);
			result=false;
		}
		if(result)
			attr.addFlashAttribute("b_num",b_num); //세션 영역에 저장 후 1번 사용하면 사라짐
		return new ModelAndView("redirect:/board/list");
	}
	
	@GetMapping("/download")
	public void download(@RequestParam Map<String,Object> params,
		HttpServletRequest req, HttpServletResponse res) throws Exception{
		params.put("root", req.getSession().getServletContext().getRealPath("/"));
		params.put("res", res);
		bm.download(params);
	}
	
	@GetMapping("/write")
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
	@PostMapping("/write")
	public ModelAndView write(MultipartHttpServletRequest multi, Integer fileCheck) {
//		BoardDto board=new BoardDto();
//		board.setB_contents(multi.getParameter("b_contents"));
//		board.setB_title(multi.getParameter("b_title"));
//		log.info("board: "+board);
//		List<MultipartFile> attachments = multi.getFiles("attachments");
//		log.info("files: "+attachments);
		
		boolean result = bm.boardWrite(multi,fileCheck);
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
	
	@SuppressWarnings("unused")
	@GetMapping("/contents")
	public ModelAndView getContents(Integer b_num,HttpSession session) {
		ModelAndView mav=new ModelAndView();	
		//if(b_num==null) { /*최신글 가져오기 */}
		log.info("b_num:"+b_num);
		
		BoardDto board=bm.getContents(b_num);
		if(board.getB_id().equals(session.getAttribute("id").toString())) {
			mav.addObject("id_check",1); //본인글 확인
		}
		List<ReplyDto> rList=bm.getReplyList(b_num);
		log.info("BM board: "+board);
		if(board!=null) {
			mav.setViewName("boardContentsAjax");
			mav.addObject("board",board).addObject("rList",rList);
		}else {
			mav.setViewName("redirect:/board/list");
		}
		return mav;
	}
}
