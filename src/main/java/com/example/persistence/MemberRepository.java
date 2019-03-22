package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.dao.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	
	/*
	 * 회원정보를 조회하면서 회원의 현재 프로필 사진도 같이 보여줘야하는 상황에서 여러 문제가 생길 수 있다.
	 * ProfileRepository 인터페이스에 findByMember() 쿼리 함수를 만들어 조회할 경우
	 * 20명의 회원정보가 리스트 형식으로 되어있으면  findByMember() 쿼리 함수가 20번 실행되버린다.
	 * 대신 Inner/Outer join, 서브쿼리등을 @Query에서 native 속성을 사용해 그대로 이용하는 방법도 있지만
	 * 데이터베이스에 종속되기 때문에 효과적이지 못하다.
	 * JPA에서 Fetch join 기법을 사용하면 SQL에서 조인을 처리하는 것과 유사한 작업을 처리할 수 있다.
	 * */
	
//	@Query(value = "select m.U_ID, count(p.F_NAME) from tbl1_members m left outer join tbl1_profile p on p.MEMBER_U_ID = m.U_ID where m.U_ID = ?1 group by m.U_ID", nativeQuery = true)
	@Query("select m.uId, count(p) from Member m left outer join Profile p on m.uId = p.member where m.uId = ?1 group by m")
	public List<Object[]> getMemberWithProfileCount(String uId);
	
//	@Query(value = "select * from tbl1_members m left outer join tbl1_profile p on m.u_id = p.member_u_id where m.u_id = ?1 and p.status = 1", nativeQuery = true)
	@Query("select m, p from Member m left outer join Profile p on m.uId = p.member where m.uId = ?1 and p.status = true")
	public List<Object[]> getMemberWithProfile(String uId);
}
