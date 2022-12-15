package board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadLogic {
	
	// 파일을 업로드 한 후, 정보를 BoardFileDTO 로 변환해서 리턴
	public List<BoardFileDTO> uploadFiles(List<MultipartFile> files, String path) throws IllegalStateException, IOException{
		List<BoardFileDTO> fileDTOList = new ArrayList<BoardFileDTO>();
		for(MultipartFile file : files) {
			if (!file.isEmpty()) {
				// 클라이언트가 업로드한 파일명
				String originalFileName = file.getOriginalFilename();
				System.out.println(originalFileName);
				
				// 서버에서 식별할 수 있도록 파일명을 변경
				String storeFileName = createStoreFileName(originalFileName);
				
				// File 객체를 실제 경로에 저장
				file.transferTo(new File(path + File.separator + storeFileName));
				
				// multipartFile 을 List<BoardFileDTO> 로 변환
				fileDTOList.add(new BoardFileDTO(originalFileName, storeFileName));
			}
		}
		return fileDTOList;
	}
	// upladFile 메소드를 작성
	//  → 한 개의 파일만 업로드 할 수 있도록 정의
	//  → uploadFiles 메소드에서 작성한 uploadFile 메소드를 호출하여 작업
	
	
	// Client 가 입력한 파일명을 중복 없는 이름으로 변경
	// UUID: 32자리의 16진수로 표기
	private String createStoreFileName(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(pos + 1);
		String uuId = UUID.randomUUID().toString();
		return uuId + "." + ext;
	}
	

}
