package com.example.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.dao.FreeBoard;

/*
 * 각 엔티티가 별도의 라이프사이클을 가진다면 별도의 Repository를 생성하는 것이 좋다.
 * 게시물 작성과 댓글의 작성은 두 엔티티 객체에 서로 영향을 주지 않으므로 별도로 작성해준다.(댓글의 수정,삭제도 마찬가지 이므로 별도로 작성해준다.)
 * 
 * 양방향 처리는 하나의 엔티티 객체를 이용해서 다른 엔티티를 서로 참조하는 관계이므로 단방향의 제한적인 접근에 비해 운용의 폭이 넓다.
 * 양방향으로 원하는 데이터들을 가져올때 SQL이 성능에 나쁜 영향을 주는지 고민해봐야한다.
 * - 쿼리메소드를 이용하는 경우
 * - @Query를 이용하는 경우
 * */
public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{

	// 쿼리메소드를 이용하는 경우 - testList1(), testList2()
	public List<FreeBoard> findByBnoGreaterThan(Long bon, Pageable page);
	
	// @Query를 이용하는 경우 - testList3()
	// @Query를 이용해서 엔티티의 일부 속성이나 다른 엔티티를 조회할 때의 리턴 타입은 컬렉션<배열>의 형태가 된다.
	// 이경우 List는 결과 데이터의 행을 의미하고 Object[]는 열을 의미한다.
	@Query(value = "select b.bno, b.title, count(r.rno) "
			+ "from tbl1_freeboards b left outer join tbl1_freeboardreply r on b.bno = r.free_board_bno "
			+ "where b.bno > 0 "
			+ "group by b.bno, b.title "
			+ "order by b.bno "
			+ "desc", nativeQuery = true)
	public List<Object[]> getPage(Pageable pageable);
}
