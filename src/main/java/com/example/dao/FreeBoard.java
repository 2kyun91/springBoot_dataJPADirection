package com.example.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 게시물(FreeBoard)과 댓글(FreeBoardReply)의 관계는 '일대다', '다대일'의 양방향으로 지정한다.
 * 두 클래스간 상호 참조가 가능하도록 @OneToMany, @ManyToOne으로 각각 설정해준다.
 * 양방향 참조를 사용하는 경우에는 반드시 한쪽은 toString()에서 참조하는 객체를 출력하지 않도록 수정해야한다.
 * */

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Table(name = "tbl1_freeboards")
@EqualsAndHashCode(of = "bno")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_freeboard")
	@SequenceGenerator(name = "seq_freeboard", sequenceName = "SEQ_FREEBOARD", allocationSize = 1, initialValue = 1)
	private Long bno;
	private String title;
	private String writer;
	private String content;

	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	/*
	 * @OneToMany 설정 시 '다'에 해당하는 정보를 보관하기 위해 JPA 구현체는 별도의 테이블을 자동으로 생성한다. 즉 생성 할 테이블은 2개지만 3개가 생성된다.
	 * JPA에서 양쪽이 모두 참조를 사용하는 경우에는 어느쪽이 PK, FK인지 지정한다.
	 * PK쪽이 mappedBy 속성을 이용해서 자신이 다른 객체에게 '매여있다'라는 것을 명시한다.
	 * 속성 값으로 종속적인 클래스의 인스턴스 변수를 지정한다.(FreeBoardReply 클래스의 freeBoard 변수가 FreeBoard의 인스턴스를 의미)
	 * 예 : 게시글에 댓글이 있는경우 삭제가 불가능하다.
	 * 
	 * JPA는 연관관계의 Collection 타입을 처리할 때 성능저하를 방지하기 위해 '지연로딩(lazy loading)'을 default으로 사용한다.
	 * 반대 개념은 '즉시로딩(eager loading)'으로 @OneToMany에 'fetch'라는 속성값으로 FetchType.EAGER를 지정하면 된다.
	 * 하지만 지연로딩을 즉시로딩으로 바꾸는것은 좋은 방법은 아니다.
	 * 지연로딩을 하면서 다른 엔티티의 값을 조회하고 싶다면 @Transactional을 이용해서 처리해야 한다.
	 * 
	 * 지연로딩 문제를 해결하는 가장 좋은 방법은 @Query를 이용해서 조인 처리를 하는 것이다.
	 * (스프링 부트 2.0 이상 부터는 연관관계가 없는 엔티티 간에도 조인이 가능하다)
	 * */
//	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FreeBoardReply> replies;
}
