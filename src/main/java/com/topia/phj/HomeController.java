package com.topia.phj;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topia.phj.service.BoardServImpl;
import com.topia.phj.service.ReplyServImpl;
import com.topia.phj.utill.GetIp;
import com.topia.phj.vo.BoardVo;
import com.topia.phj.vo.Criteria;
import com.topia.phj.vo.Paging;
import com.topia.phj.vo.ReplyVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	public BoardServImpl bsi;

	@Autowired
	private ReplyServImpl rsi;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "redirect:./boardList";
	}

	// 게시글목록구현
	@RequestMapping(value = "boardList")
	public String boardList(HttpServletRequest request, Model model, Criteria cri) {
		System.out.println("**************boardList cont***********");

		String searchCondition = request.getParameter("searchCondition");
		String boardSearchWord = request.getParameter("boardSearchWord");
		
		

		HashMap<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("searchCondition", searchCondition);
		reqMap.put("boardSearchWord", boardSearchWord);

		int boardListCnt = bsi.boardListCount(reqMap);

		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(boardListCnt);

		reqMap.put("cri", cri);

		model.addAttribute("boardVo", bsi.boardList(reqMap));
		model.addAttribute("search", reqMap);
		model.addAttribute("paging", paging);
		model.addAttribute("cri", cri);

		return "boardList";

	}

	// 게시글 등록화면구현
	@RequestMapping(value = "boardInsert")
	public String boardInsert(HttpServletRequest request, Model model) {
		System.out.println("**************boardInsert cont***********");
		HttpSession session = request.getSession();
		model.addAttribute("loginId", session.getAttribute("sessionId"));
		model.addAttribute("loginName", session.getAttribute("sessionName"));
		model.addAttribute("sessionAuth", session.getAttribute("sessionAuth"));

		return "./boardInsert";
	}

	// 게시글 등록 구현
	@RequestMapping(value = "boardInsertOk", method = RequestMethod.POST)
	public String boardInsertOk(HttpServletRequest request, BoardVo board, Model model) {
		// ip얻기
		GetIp ipUtill = new GetIp();
		String ip = ipUtill.getMemberIp(request);
		board.setBoardRegIp(ip);
		bsi.boardInsert(board);

		return "redirect:./boardList";
	}

	// 상세게시글보기
	@RequestMapping(value = "boardDetail")
	@ResponseBody
	public Model boardDetail(@RequestParam("boardIdx") Integer bno, BoardVo board, Model model) {
		System.out.println("******************boardDetail********************");
		// 조회수증가
		bsi.boardViewCount(board.getBoardIdx());
		model.addAttribute("boardVo", bsi.boardDetail(bno));
		
//		List<ReplyVo> replyList = rsi.replyList(board.getBoardIdx());  // 추가
//		model.addAttribute("replyList", replyList);   // 추가
		return model;
	}

	// 업데이트화면
	@RequestMapping(value = "boardUpdate")
	public String boardUpdate(@RequestParam("boardIdx") Integer bno, Model model) {
		System.out.println("******************boardupdate화면보여주기********************");
		model.addAttribute("boardVo", bsi.boardDetail(bno));
		return "./boardUpdate";
	}

	// 엡데이트 구현
	@ResponseBody
	@RequestMapping(value = "boardUpdateOk", method = RequestMethod.POST)
	public String boardUpdateOk(HttpServletRequest request, BoardVo boardVo) {
		System.out.println("******************boardupdate구현하기********************");
		GetIp ipUtill = new GetIp();
		String ip = ipUtill.getMemberIp(request);
		boardVo.setBoardUpdateIp(ip);
		bsi.boardUpdate(boardVo);
		return "redirect:./boardList";
	}

	// 삭제메서드 구현
	@RequestMapping(value = "boardDelete", method = RequestMethod.POST)
	public String boardDelete(@RequestParam("boardIdx") Integer bno) {
		System.out.println("******************boardupdate구현하기********************");
		//rsi.replyDelete(bno);
		bsi.boardDelete(bno);
		return "redirect:./boardList";
	}
	
	//체크버튼시 삭제
		@RequestMapping(value="boardDeleteChkList")
		public String memberDeleteChkList(HttpServletRequest request) {
			String[]  checkArr = request.getParameterValues("checkArr");
			int size = checkArr.length;
			
			for(int i =0; i<size; i++) {
				HashMap<String, Object> reqMap = new HashMap<String, Object>();
				reqMap.put("boardIdx", checkArr[i]);
				bsi.boardDeleteChkList(reqMap);
			}
			return "redirect:boardList";
		}
	
	
	
	// 체크박스 선택후 삭제
//	@RequestMapping(value = "boardDeleteChkList", method = RequestMethod.POST)
//	public String boardDeleteChkList(HttpServletRequest request, @RequestBody List<HashMap<String, Object>> param) {
//		System.out.println("******************boardDeleteChkList구현하기********************");
//
//		HttpSession session = request.getSession();
//		String sessionId = (String) session.getAttribute("sessionId");
//		String sessionAuth = (String) session.getAttribute("sessionAuth");
//
//		String boardIdx = null;
//		String boardMemberId = null;
//		HashMap<String, Object> inputParam = new HashMap<>();
//		for (int i = 0; i < param.size(); i++) {
//
//			boardIdx = (String) param.get(i).get("boardIdx");
//			boardMemberId = (String) param.get(i).get("boardMemberId");
//			if (!"master".equals(sessionAuth) && !sessionId.equals(boardIdx)) {
//				System.out.println("****작성자가 다르거나 귀하는 master가 아닙니다.******");
//				return "redirect:./boardList";
//			}
//
//		}
//
//		for (int i = 0; i < param.size(); i++) {
//			bsi.boardDeleteChkList(param.get(i));
//		}
//
//		return "redirect:./boardList";
//	}

	// 댓글 목록 불러오기
	@RequestMapping(value ="replyList")
	@ResponseBody
	public  HashMap<String, Object> list(@RequestBody HashMap<String, Object> param) {
		System.out.println("*************replyList 목록보기**********************");
		List<ReplyVo> replyList = rsi.replyList(param);
		
		HashMap<String, Object> resMap = new HashMap<>();
		resMap.put("replyList",replyList);
		
		return resMap;
	}

	
	//댓글작성
	@RequestMapping(value = "replyInsert", method = RequestMethod.POST)
	@ResponseBody
	public void insert(@RequestBody ReplyVo reple, HttpSession session) {
		System.out.println("*************relyInsert 목록보기**********************");
		String sessionId = (String) session.getAttribute("sessionId");
		//작성자 아이디
			reple.setReplyer(sessionId);
		
		rsi.insertReply(reple);
		
		
	}
	
	//댓글수정
	@RequestMapping(value="replyUpdate", method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyUpdate(@RequestBody ReplyVo param) {
		System.out.println("*************replyUpdate 목록보기**********************");
		Map<String, Object> map = new HashMap<>();
		
		map.put("updateReply", rsi.replyUpdate(param));		
			
		return map;
	}
	
	//댓글삭제
	@RequestMapping(value = "replyDelete", method = RequestMethod.POST)
	@ResponseBody
	public String boardDelete(@RequestParam("rno") int rno) {
		System.out.println("******************replyDelete구현하기********************");
		rsi.replyDelete(rno);
		return "redirect:./boardDetail";
	}
}
