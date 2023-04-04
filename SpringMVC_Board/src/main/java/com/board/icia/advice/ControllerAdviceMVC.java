package com.board.icia.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.exception.CommonException;

@ControllerAdvice
public class ControllerAdviceMVC {
	//board/list?pageNum=a
	//board/list?pageNum=-1
	
	@ExceptionHandler(NumberFormatException.class)
	public String except(NumberFormatException ex,RedirectAttributes attr) {
		attr.addFlashAttribute("err_msg", ex.getMessage());
		System.out.println("err_msg"+ex.getMessage());
		return "redirect:/board/list";
	}
	
	@ExceptionHandler(CommonException.class)
	public String except(CommonException ex,RedirectAttributes attr) {
		attr.addFlashAttribute("page_num_err", ex.getMessage());
		System.out.println("err_msg"+ex.getMessage());
		return "redirect:/board/list";
	}
	
// 모든 예외를 처리하고 싶다면 Exception.class
	
}