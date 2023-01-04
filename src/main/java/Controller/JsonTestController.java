package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardService;

@RestController
//@Controller
//@ResponseBody
@RequestMapping("/json")
public class JsonTestController {
	BoardService service;

	public JsonTestController() {
		super();
	}

	@Autowired
	public JsonTestController(BoardService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/index")
	public String idxPage() {
		return "index";
	}
	
	//	@ResponseBody 는 웹페이지를 응답하지 않고 Response Boby 에 스트링을 추가해서 response 하겠다.
	//	→ View 를 응답하지 않는다. (새로고침 안함.)
	@RequestMapping("/getString")
//	@ResponseBody
	public String responseString() {
		return "json";
	}
	
	@RequestMapping("/getJsonObj")
	public @ResponseBody BoardDTO responseObj() {
		return service.getBoardInfo("21");
	}
	
	@RequestMapping("/getJsonArr")
//	@ResponseBody
	public List<BoardDTO> responseArr() {
		return service.findByCategory("all");
	}

}



