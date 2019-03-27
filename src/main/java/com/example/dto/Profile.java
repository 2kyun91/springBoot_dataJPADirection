package com.example.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* Profile와 Member의 관계는 다대일 관계
 * Profile 클래스에만 Member 타입의 인스턴스 변수를 추가(단방향 참조 == 일방통행의 참조)
 * 프로필을 통해서 회원정보를 조회하는 경우이다.
 * @ToString에 exclude 속성을 이용하여 Member에 관련된 toString()을 호출하지 않게 한다.
 * 양방향으로 참조하는 경우 Member의 toString() <---> Profile의 toString()의 호출이 무한반복되는 문제가 발생할 수 있다.
 * */

@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name = "tbl1_profile")
@EqualsAndHashCode(of = "fName")
public class Profile {
	
	@Id
	// 오라클의 시퀀스를 생성하려면 @SequenceGenerator를 이용해서 생성되는 Sequence의 이름과 SequenceGenerator의 이름을 지정해 주어야 한다.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profile")
	@SequenceGenerator(name = "seq_profile", sequenceName = "SEQ_PROFILE", allocationSize = 1, initialValue = 1)
	private Long fNo;
	private String fName;
	private boolean status;
	
	@ManyToOne
	private Member member; // Member 타입의 인스턴스 변수, Member의 uId 칼럼을 참조한다.
	
}
