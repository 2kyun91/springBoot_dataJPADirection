package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.PDSBoard;
import com.example.dao.PDSFile;
import com.example.persistence.PDSBoardRepository;
import com.example.persistence.PDSFileRepository;

import lombok.extern.java.Log;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Log
//@Commit
public class PDSBoardTests {
	@Autowired
	PDSBoardRepository pdsBoardRepository;
	
	@Autowired
	PDSFileRepository pdsFileRepository;
	
//	@Test // 1개의 자료와 2개의 첨부파일을 저장
//	public void testInserPDS() {
//		PDSBoard pdsBoard = new PDSBoard();
//		pdsBoard.setPdsName("첨부파일 1~2");
//		
//		PDSFile pdsFile1 = new PDSFile();
//		pdsFile1.setPdsFile("파일1.doc");
//		
//		PDSFile pdsFile2 = new PDSFile();
//		pdsFile2.setPdsFile("파일2.doc");
//		
//		pdsBoard.setFiles(Arrays.asList(pdsFile1, pdsFile2));
//		
//		log.info("pds 저장 시도!");
//		
//		pdsBoardRepository.save(pdsBoard);
//	}
	
	/* @Query를 이용해서 수정, 삭제 작업을 하는경우 반드시 @Transactional 처리를 해주어야한다.
	 * @Transactional을 선언하면 콘솔창에 에러없이 정상처리가 되었음에도 기본적으로 롤백 처리를 시도한다.
	 * 이런 롤백 처리가 싫은 경우 대상 클래스의 선언부에 @Commit을 추가로 선언하여 자동 커밋 되게 해주면 된다.
	 * */
//	@Transactional 
//	@Test
//	public void testUpdateFileName1() {
//		Long pdsFno = 1L;
//		String newFileName = "수정파일1.doc";
//		
//		int count = pdsFileRepository.updatePDSFile(pdsFno, newFileName);
//		
//		log.info("update count : " + count);
//	}
	
//	@Transactional
//	@Test
//	public void testUpdateFileName2() {
//		String newFileName = "수정파일2.doc";
//		
//		Optional<PDSBoard> result = pdsBoardRepository.findById(3L); // 반드시 번호가 존재하는지 확인해야 한다.
//		
//		System.out.println(result);
//	
//		result.ifPresent(pds -> {
//			log.info("데이터가 존대하므로 update 시도!");
//			
//			PDSFile target = new PDSFile();
//			target.setPdsFno(2L);
//			target.setPdsFile(newFileName);
//			
//			int idx = pds.getFiles().indexOf(target); // DB의 pdsFno값이 2번인 데이터의 인덱스를 추출
//			
//			if (idx > -1) {
//				List<PDSFile> list = pds.getFiles();
//				list.remove(idx); // 삭제하고
//				list.add(target); // 새로운 데이터를 추가
//				pds.setFiles(list); // 갱신된 첨부파일의 목록을 설정
//			}
//			
//			pdsBoardRepository.save(pds); // 저장
//		});
//	}
	
//	@Transactional
//	@Test
//	public void deletePDSFile() {
//		Long pdsFno = 2L; // 첨부파일 번호
//		
//		int count = pdsBoardRepository.deletePDSFile(pdsFno);
//		
//		log.info("delete PDSFile : " + count);
//	}
	
//	@Test
//	public void insertDummiesData() {
//		List<PDSBoard> list = new ArrayList<>();
//		
//		IntStream.range(1, 100).forEach(i -> {
//			PDSBoard pdsBoard = new PDSBoard();
//			pdsBoard.setPdsName("자료 " + i);
//			
//			PDSFile pdsFile1 = new PDSFile();
//			pdsFile1.setPdsFile("파일1.doc");
//			
//			PDSFile pdsFile2 = new PDSFile();
//			pdsFile2.setPdsFile("파일2.doc");
//			
//			pdsBoard.setFiles(Arrays.asList(pdsFile1, pdsFile2));
//			
//			log.info("pds 저장 시도!");
//			
//			list.add(pdsBoard);
//		});
//		
//		// saveAll()을 이용해서 한 번에 여러개의 데이터를 insert 처리할 수 있다.
//		pdsBoardRepository.saveAll(list);
//	}
	
//	@Test
//	public void viewSummary() {
//		pdsBoardRepository.getSummary().forEach(arr -> {
//			log.info(Arrays.toString(arr));
//		});
//	}
}
