package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardService;

@Controller
@RequestMapping("/ajax")
public class AJaxTestController {
	BoardService service;
	
	public AJaxTestController() {
		super();
	}
	
	@Autowired
	public AJaxTestController(BoardService service) {
		super();
		this.service = service;
	}

	// 처리 후 뷰를 리스폰스하는 메소드
	@RequestMapping("/noAjax")
	public String noAjax(String id, Model model) {
		String msg = "";
		if (id.equals("jang")) {
			msg = "중복 된 아이디 입니다.";
		} else {
			msg = "사용 가능한 아이디 입니다.";
		}
		model.addAttribute("msg", msg);
		return "etcview/ajax";
	}
	
	@RequestMapping(value = "/ajaxtest01", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String ajaxtest(String id) {
		String msg = "";
		if (id.equals("jang")) {
			msg = "중복 된 아이디 입니다.";
		} else {
			msg = "사용 가능한 아이디 입니다.";
		}
		return msg;
	}
		
	@RequestMapping(value = "/exam01", 
					produces = "application/text; charset=utf-8")
	@ResponseBody
	public String ajaxexam(String board_no) {
		BoardDTO board = service.getBoardInfo(board_no);
		return board.toString();
	}
	
	@RequestMapping(value = "/exam02/getJsonData", 
					produces = "application/json; charset=utf-8")
	@ResponseBody
	public BoardDTO ajaxJsonExam(String board_no) {
		return service.getBoardInfo(board_no);
	}

}
