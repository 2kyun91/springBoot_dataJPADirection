package com.example.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl1_pdsfiles")
@EqualsAndHashCode(of="pdsFno")
public class PDSFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pdsfile")
	@SequenceGenerator(name = "seq_pdsfile", sequenceName = "SEQ_PDSFILE", allocationSize = 1, initialValue = 1)
	private Long pdsFno;
	private String pdsFile;
	
}
