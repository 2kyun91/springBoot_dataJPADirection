package com.example.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl1_freeboardreply")
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
