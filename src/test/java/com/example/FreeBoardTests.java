package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.FreeBoard;
import com.example.dao.FreeBoardReply;
import com.example.persistence.FreeBoardReplyRepository;
import com.example.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {
	@Autowired
	FreeBoardRepository freeBoardRepository;

	@Autowired
	FreeBoardReplyRepository freeBoardReplyRepository;

	@Test
	public void insertDummy() {
		IntStream.range(1, 200).forEach(i -> {
			FreeBoard freeBoard = new FreeBoard();
			freeBoard.setTitle("자유 게시물 제목 " + i);
			freeBoard.setContent("자유 게시물 내용 " + i);
			freeBoard.setWriter("자유 게시물 작성자 " + i%10);
			
			freeBoardRepository.save(freeBoard);
		});
	}

	/*
	 * 댓글을 추가하는 방식은 2가지 방식을 사용할 수 있다.
	 * 첫번째 방식은 양방향 방식의 댓글 추가.
	 * - 양방향이므로 FreeBoard 객체를 얻어온 후 FreeBoardReply를 댓글 리스트에 추가한 후에 FreeBoard 자체를 저장하는 방식
	 * */
	@Transactional
	@Test
	public void insertReply2Way() {
		Optional<FreeBoard> result = freeBoardRepository.findById(199L);
		
		result.ifPresent(board -> {
			List<FreeBoardReply> replies = board.getReplies();
			
			FreeBoardReply freeBoardReply = new FreeBoardReply();
			freeBoardReply.setReply("댓글...");
			freeBoardReply.setReplyer("replyer00");
			freeBoardReply.setFreeBoard(board);
			
			replies.add(freeBoardReply);
			
			board.setReplies(replies);
			
			freeBoardRepository.save(board);
		});
	}

	/*
	 * 두번째 방식은 단방향 방식의 댓글 추가.
	 * - 단방향에서 처리하듯이 FreeBoardReply를 생성하고 FreeBoard 자체는 새로 만들어서 bno 속성만을 지정하여 처리하는 방식
	 * */
	@Test
	public void insertReply1Way() {
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setBno(199L);
		
		FreeBoardReply freeBoardReply = new FreeBoardReply();
		freeBoardReply.setReply("댓글...");
		freeBoardReply.setReplyer("replyer00");
		freeBoardReply.setFreeBoard(freeBoard);
		
		freeBoardReplyRepository.save(freeBoardReply);
	}

	@Test  // 게시물 출력(엔티티 1개)
	public void testList1() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

		freeBoardRepository.findByBnoGreaterThan(0L, pageable).forEach(board -> {
			log.info(board.getBno() + " : " + board.getTitle());
		});
	}
	
	@Transactional
	@Test  // 게시물 + 댓글 출력(엔티티 2개)
	public void testList2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

		freeBoardRepository.findByBnoGreaterThan(0L, pageable).forEach(board -> {
			log.info(board.getBno() + " : " + board.getTitle() + " : " + board.getReplies().size());
		});
	}
	
	@Test
	public void testList3() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		freeBoardRepository.getPage(pageable).forEach(arr -> {
			log.info(Arrays.toString(arr));
		});
	}
}
