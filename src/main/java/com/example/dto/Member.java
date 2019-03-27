package com.example.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* Member와 Profile의 관계는 일대다 관계 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl1_members")
@EqualsAndHashCode(of = "uId")
public class Member {
	
	@Id
	private String uId;
	private String uPw;
	private String uName;
	
}
