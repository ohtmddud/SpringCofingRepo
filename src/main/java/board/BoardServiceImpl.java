package board;

import java.util.List;
//BoardDAO의 메소드를 호출
//→ Controller 에게 받은 데이터를 가공해서 DAO 로 넘기거나 DAO 에서 받은 데이터를 가공해서 컨트롤러로 넘기는 작업
//→ 트랜잭션 처리

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO dao;
	public BoardServiceImpl() {
		
	}

	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public BoardDTO getBoardInfo(String board_no) {
		return dao.read(board_no);
	}

	@Override
	public int update(BoardDTO board) {
		return dao.update(board);
	}

	@Override
	public int delete(String board_no) {
		return dao.delete(board_no);
	}

	@Override
	public List<BoardDTO> search(String data) {
		return dao.search(data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		return dao.search(tag, data);
	}

	@Override
	public List<BoardDTO> findByCategory(String category) {
		List<BoardDTO> list = null;
		if(category != null) {
			if(category.equals("all")) {
				list = dao.boardList();
			}else {
				list = dao.findByCategory(category);
			}
		}
		return list;
	}
	
	@Override
	public List<BoardDTO> boardList() {
		return dao.boardList();
	}
	
	@Override
	public List<BoardFileDTO> getFileList(String board_no) {
		return dao.getFileList(board_no);
	}
	
	

	@Override
	public int insertFile(List<BoardFileDTO> boardFileDTOList) {
		return 0;
	}

	// 게시글 등록 → 게시글 기본 정보 저장, 첨부된 파일에 대한 정보 저장
	// dao 클래스에정의 된 두개의 메소드를 호출
	// 내부적으로 스프링 AOP 가 동작하면서 트랜잭션을 처리
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insert(BoardDTO board, List<BoardFileDTO> boardFiledDTOList) {
		dao.insert(board);
		dao.insertFile(boardFiledDTOList);
		return 0;
	}
	
	@Override
	public BoardFileDTO getFile(BoardFileDTO inputData) {
		return dao.getFile(inputData);
	}

}
