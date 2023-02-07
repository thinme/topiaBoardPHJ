package com.topia.phj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topia.phj.service.MemberServImpl;
import com.topia.phj.utill.GetIp;
import com.topia.phj.vo.Criteria;
import com.topia.phj.vo.MemberVo;
import com.topia.phj.vo.Paging;

@Controller
public class MemberController {
	
	@Autowired
	public MemberServImpl msi;
	
	//로그인체크
	@RequestMapping(value="/login")
	public String login() {
			return "login";
		}
	@ResponseBody
	@RequestMapping(value="/loginCheck")
	public HashMap<String, Object> logincheck(HttpServletRequest request, MemberVo memberVo, Model model) {
		
		HashMap<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("id", memberVo.getMemberId());
		reqMap.put("pw", memberVo.getMemberPassword());
		System.out.println("id+pw>>>" + reqMap);
		MemberVo member = msi.loginCheck(reqMap);
		
		HttpSession session = request.getSession();
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		if("Y".equals(member.getResultYn())){
			session.setAttribute("sessionId", member.getMemberId());
			session.setAttribute("sessionName", member.getMemberName());
			session.setAttribute("sessionAuth", member.getMemberAuth());
			session.setAttribute("sessionIdx", member.getMemberIdx());
		}
		resMap.put("memberAuth", member.getMemberAuth());
		resMap.put("resultYn", member.getResultYn());
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="loginIdChck")
	public int loginIdChck(HttpServletRequest request) {
		String memberIdChck = request.getParameter("memberId");
		int result = msi.loginIdCheck(memberIdChck);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="loginPwChck")
	public int loginPwChck(HttpServletRequest request) {
		String memberPwChck = request.getParameter("memberPassword");
		int result = msi.loginPwCheck(memberPwChck);
		return result;
	}
	
	
	
	@RequestMapping(value="logout")
	public String logout(HttpServletRequest request) {
		System.out.println("******************Logout********************");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:./boardList";
	}
	
	//회원가입버튼시 페이지 호출
	@RequestMapping(value="/memberInsert")
	public String insert() {
		System.out.println("member Insert");
		
		return "memberInsert";
	}
	
	//회원가입페이지 에서 회원가입버튼시 응답
	@RequestMapping(value="/memberInsertOk", method = RequestMethod.POST)
	public String memberInsert(HttpServletRequest request, MemberVo member) {
		System.out.println("******************memberInsert********************");
		GetIp getIp = new GetIp();
		String ip= getIp.getMemberIp(request);
		
		member.setMemberRegIp(ip);
		System.out.println("memberIp>>>>>>>>>>"+ ip);
		msi.memberInsert(member);
		System.out.println(member.toString());
		
		return "redirect:/";
		
	}
	
	//회원관리페이지 호출(관리자영역)
	@RequestMapping(value="/memberList")
	public String memberList(HttpServletRequest request, Criteria cri, Model model){
		System.out.println("******************memberList********************");
		
		String searchCondition = request.getParameter("searchCondition");
		String memberSearchWord = request.getParameter("memberSearchWord");
		
		HashMap<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("searchCondition", searchCondition);
		reqMap.put("memberSearchWord", memberSearchWord);
		
		int memberListCnt = msi.memberListCount(reqMap);
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(memberListCnt);
		
		reqMap.put("cri", cri);
		
		model.addAttribute("memberVo", msi.memberList(reqMap));
		model.addAttribute("search", reqMap);
		model.addAttribute("paging", paging);
		model.addAttribute("cri", cri);
		
		
		return "memberList";
	}
	
	//상세보기
	@RequestMapping(value="/memberDetail")
	public Model memberDetail(@RequestParam ("memberIdx")Integer idx, MemberVo memberVo, Model model) {
		System.out.println("******************memberDetail********************");
		model.addAttribute("memberVo", msi.memberDetail(idx) );
		return model;
	}
	
	//업데이트(화면 호출하기) 
	@RequestMapping(value="/memberUpdate")
	public  String memberUpdate(@RequestParam("memberIdx") Integer idx, Model model ) {
		System.out.println("******************membeupdate화면보여주기********************");
		model.addAttribute("memberVo", msi.memberDetail(idx));
		return "./memberUpdate";
	}
	
	//업데이트 (post화면 구현하기)
	@ResponseBody
	@RequestMapping(value="/memberUpdateOk", method=RequestMethod.POST)
	public HashMap<String, Object> memberUpdateOk(HttpServletRequest request, MemberVo param) {
		System.out.println("******************membeUpdate실행하기********************");
		
		System.out.println("MemberController.java || /memberUpdateOk || param.getMemberName() ======>>> " + param.getMemberName());
		System.out.println("MemberController.java || /memberUpdateOk || param.getMemberId() ======>>> " + param.getMemberId());
		System.out.println("MemberController.java || /memberUpdateOk || param.getMemberIdx() ======>>> " + param.getMemberIdx());
		msi.memberUpdate(param);
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		resMap.put("AUTH", session.getAttribute("sessionAuth"));
		resMap.put("resultYn", "Y");
		
		return resMap;
		
	}
	//삭제
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(@RequestParam("memberIdx") Integer idx, HttpServletRequest request) {
		System.out.println("******************membeDelete실행하기********************");
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		MemberVo test = msi.memberDetail(idx);
		String auth = (String) session.getAttribute("sessionAuth");
		
		msi.memberDelete(idx);
		
		if (!auth.equals("master")) {
			session.invalidate();
		}
		
		return "boardList";
	}
	
	//체크버튼시 삭제
	@RequestMapping(value="memberDeleteChkList")
	public String memberDeleteChkList(HttpServletRequest request) {
		String[]  checkArr = request.getParameterValues("checkArr");
		int size = checkArr.length;
		
		for(int i =0; i<size; i++) {
			HashMap<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("memberIdx", checkArr[i]);
			msi.memberDeleteChkList(reqMap);
		}
		return "redirect: memberList";
	}

}
