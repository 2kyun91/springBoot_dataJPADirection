package com.example.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * @Table에는 인덱스를 설계할 때 @Index를 사용해서 테이블 생성 시에 인덱스가 설계되도록 지정할 수 있다.
 * 두개 이상의 칼럼을 사용하여 인덱스를 설계할 경우 쉼표를 사용할 수 있다.
 * */

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl1_freeboardreply", indexes = {@Index(unique = false, columnList = "free_board_bno")})
@EqualsAndHashCode(of = "rno")
public class FreeBoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_freeboardreply")
	@SequenceGenerator(name = "seq_freeboardreply", sequenceName = "SEQ_FREEBOARDREPLY", allocationSize = 1, initialValue = 1)
	private Long rno;
	private String reply;
	private	String replyer;
	
	@CreationTimestamp
	private Timestamp replydate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	@ManyToOne
	private FreeBoard freeBoard;
}
