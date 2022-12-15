package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardFileDTO;
import com.multi.erp.board.BoardService;
import com.multi.erp.board.FileUploadLogic;

@Controller
public class BoardController {
	BoardService service;
	FileUploadLogic fileUploadService; 

	public BoardController() {
	}

	@Autowired
	public BoardController(BoardService service, FileUploadLogic fileUploadService) {
		super();
		this.service = service;
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView writePage() {
		ModelAndView mav = new ModelAndView("board/write");
		return mav;
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public ModelAndView write(BoardDTO board, HttpSession session) throws IllegalStateException, IOException{
		ModelAndView mav = new ModelAndView("board/read");

		// 1. MultipartFile 정보를 추출하기
		List<MultipartFile> files = board.getFiles();
		System.out.println(files);

		// 2. 업로드 될 서버의 경로 - 실제 서버의 경로를 추출하기 위해서 context 의 정보를 담고 있는 ServletContext 객체를 추출
		String path = WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
		System.out.println(path);
		
		// 3. 파일 업로드 서비스를 호출해서 실제 서버에 업로드 하기
		List<BoardFileDTO> boardFileDTOList = fileUploadService.uploadFiles(files, path);
		int count = 1;
			// 업로드 된 파일의 boardFileNo 의 값을 세팅 - 1 부터 1, 2, 3, 4, ... 첨부파일 마지막 번호
		for(BoardFileDTO boardFileDTO : boardFileDTOList) {
			boardFileDTO.setBoardFileNo(count + "");
			count++;
		}
		System.out.println(boardFileDTOList);
		// 4. 게시글에 대한 일반적인 정보와 첨부되는 파일의 정보를 DB 에 저장하기
		service.insert(board, boardFileDTOList);
		
		mav.addObject("board", board);
		return mav;
	}

	@RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
	public ModelAndView list(String category) {
		ModelAndView mav = new ModelAndView("menu/board");
		List<BoardDTO> list = service.findByCategory(category);
		mav.addObject("boardList", list);
		mav.addObject("category", category);
		return mav;
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView delete(String board_no) {
		ModelAndView mav = new ModelAndView("redirect:/board/list?category=all");
		service.delete(board_no);
		return mav;
	}

	@RequestMapping(value = "/board/read.do")
	public String read(String board_no , String state, Model model) {
//		ModelAndView mav = new ModelAndView();
		BoardDTO board = service.getBoardInfo(board_no);
		List<BoardFileDTO> files = service.getFileList(board_no);
		System.out.println(files);
		String view="";
		switch (state) {
		case "READ":
			view = "board/read";
			break;
		default:
			view = "board/update";
			break;
		}
//		mav.setViewName(view);
		model.addAttribute("board", board);
		model.addAttribute("files", files);
		return view;
	}

	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	public ModelAndView update(BoardDTO board) {
		ModelAndView mav = new ModelAndView("board/read");
		service.update(board);
		mav.addObject("board", board);
		return mav;
	}
	
	@RequestMapping(value = "/board/search.do")
	public ModelAndView search(String tag, String data) {
		ModelAndView mav = new ModelAndView("menu/board");
		List<BoardDTO> boardList = service.search(tag, data);
		mav.addObject("boardList", boardList);
		return mav;
	}
	
// Ajax 요청으로 실행 될 메소드
	// Jackson lib 가 List 에 저장된 DTO 들을 자동으로 Json 객체로 변환
	@RequestMapping(value = "/board/ajax/list.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<BoardDTO> ajaxList(String category) {
		List<BoardDTO> boardList = service.findByCategory(category);
		return boardList;
	}
	
//	ResponseEntity (응답 데이터를 관리) 는 HttpEntity 의 하위 객체
	// → Http 헤더, Http 바디, Http 상태 정보... 등을 관리
//	HttpEntity<T> 는 Http 요청과 응답을 관리하는 객체: 요청헤더, 요청바디, 응답헤더, 응답바디를 관리
	// UrlResource 는 파일 객체를 다루기 위해서 스프링 내부에서 사용하는 객체
	
	@RequestMapping("/board/download/{id}/{board_no}/{boardFileNo}")
	public ResponseEntity<UrlResource> downloadFile(@PathVariable String id, @PathVariable String board_no, @PathVariable String boardFileNo, HttpSession session) throws MalformedURLException, FileNotFoundException, UnsupportedEncodingException {
//		System.out.println(id + ", " + board_no + ", " + boardFileNo);
		
		// 파일을 다운로드 하기 위해서 DB에 저장된 파일의 정보를 가져오기 - 다운로드를 요청한 경우 요청된 파일을 response
		BoardFileDTO Boardfile = service.getFile(new BoardFileDTO(board_no, "", "", boardFileNo));
		
		// 파일명을 이용해서 다운로드 할 파일을 객체로 생성하는 작업
//		UrlResource resource = new UrlResource("file:" + 파일의 풀 경로);
		UrlResource resource = new UrlResource("file:" + WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload/" + Boardfile.getStoreFileName()));
		
		// 파일명에 한글이 있는 경우 오류가 발생하지 않도록 처리 - 다운 로드 되는 파일명
		String encodedFileName = UriUtils.encode(Boardfile.getOriginalFileName(), "UTF-8");
		String myContentType = "attachment; filename=\"" + encodedFileName + "\"";
		System.out.println(Boardfile);
		
		// 파일 다운로드
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, myContentType).body(resource);
	}
	

}



