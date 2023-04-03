package com.board.icia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.dto.MemberDto;
import com.board.icia.service.MemberMM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin("/*")
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberMM mm;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
//	@GetMapping("/") @PostMapping("/")
	@RequestMapping(value = "/")
	public String home(HttpServletRequest req,HttpSession session) {
//		Enumeration<String> header_list = req.getHeaderNames(); 
//		while (header_list.hasMoreElements()) {
//			System.out.println(req.getHeader(header_list.nextElement()));
//		}
		String refer = req.getHeader("refer"); 
//		System.out.println("refer:"+refer);
		if(refer!=null) {
			session.setAttribute("refer", refer);
		}
		return "home";
	}
	@GetMapping //member?val=10&str=hello 
	public String index(@RequestParam(required = false) Integer val, @RequestParam String str) {
		
		log.info(val+","+str);
		
		return "index";
	}
	
	@PostMapping(value = "/join")
	public ModelAndView join(MemberDto mb, RedirectAttributes attr) {
		
		boolean result=mm.join(mb);
		
		if(result) {
			return new ModelAndView("home").addObject("msg", "join OK").addObject("check", "1");
		}else {
			return new ModelAndView("join").addObject("msg", "join Fail").addObject("check", "2"); 
		}
		
	}
	//로그아웃
	@PostMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		if(session.getAttribute("id")!=null) {
			session.invalidate();
			return "redirect:/member/";
		}else {
			log.info("접속 30분후...세션초기화"); //세션타임아웃(30분,초기화)후 redirect에러남
			return "forward:/member/"; //get forward:/url -->get
		}
		
	}
	
	  @PostMapping(value = "/access")
	  public ModelAndView access(MemberDto mb, HttpSession session, RedirectAttributes attr) {

		  MemberDto member=mm.access(mb); 
	  
		  if(member != null) {	  
			  session.setAttribute("id", mb.getM_id());//로그인 마킹
//			  redirect전 session영역에 저장한 뒤 request객체에 저장후 session영역 삭제 함
//			  attr.addAttribute("msg", "login OK!!!"); //여러번사용가능
//			  session영역에 저장한뒤 한번 사용하고 삭제
//			  attr.addFlashAttribute("msg", "login OK!!!"); //한번만 사용가능
//			  attr.addFlashAttribute("check", "1"); //한번만 사용가능
//			  Redirect 시 새로운 request객체에 속성값을 저장한다.
//			  attr.addFlashAttribute("member",member);
//			  get방식만 가능
			  
//			  ObjectMapper objMapper=new ObjectMapper();
//			  String json = objMapper.writeValueAsString(member);
//			  attr.addAttribute("json",json);
//			  attr.addAttribute("member",member);
			  attr.addAttribute("m_id",member.getM_id());
			  attr.addAttribute("m_name",member.getM_name());
			  attr.addAttribute("m_point",member.getM_point());
			  attr.addAttribute("m_grade",member.getM_grade());
			  
			  return new ModelAndView("redirect:/board/list");
		  }else { 
			  attr.addFlashAttribute("msg", "login Fail!!!");
			  attr.addFlashAttribute("check", "2");	//회원가입성공:1, 로그인 실패:2
			  
			  return new ModelAndView("redirect:/member/");  //home.jsp
		 // return new ModelAndView("home").addObject("msg", "login Fail"); 
		  } 
	  }
	 	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/test-map")
	public  ModelAndView test_map(@RequestParam HashMap<String,String> hMap) {  // annotation 생략 불가
		
		System.out.println("cName= "+hMap.get("cName"));
		System.out.println("serach= "+hMap.get("Name"));
		
		List<Map<String,Object>>m_list=mm.test_map(hMap);
		
		return new ModelAndView("test-map").addObject("m_list",m_list);
	}

	@GetMapping("/test-params")
	public  ModelAndView test_map(String cName, Integer search) {
		
		List<Map<String,Object>>m_list=mm.test_params(cName,search);
		
		return new ModelAndView("test-map").addObject("m_list",m_list);
	}
	
}
