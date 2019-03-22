package com.example.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name = "tbl1_pdsboard")
@EqualsAndHashCode(of="pdsId")
public class PDSBoard {
	
	/*
	 * 자료실의 자료와 첨부 파일의 관계는 '일대다', '다대일'의 단방향 관계이다.
	 * PDSBoard -> PSDFile의 단방향.
	 * 실행 시 데이터베이스에 tbl1_pdsboard, tbl1_pdsfiles, tbl1_pdsboard_files 테이블이 생성된다.
	 * 2개가 아닌 3개의 테이블이 생성되는 이유는 @OneToMany 특성 때문인데
	 * tbl1_pdsboard에 칼럼이 생성된다면 여러개의 tbl1_pdsfiles의 pdsFno를 저장(일대다)해야한다.
	 * 데이터베이스의 칼럼에 하나의 값이 아닌 여러 개의 값을 저장해야 하므로 @OneToMany가 지정되면 JPA에서는 
	 * 무조건 여러개의 데이터를 저장하기 위해 별도의 테이블을 생성하게 되는 것이다.
	 * 
	 * 별도의 테이블이 생성되지 않게 하려면 @JoinTable을 명시해서 특정 테이블을 생성하거나 @JoinColumn을 명시해 기존 테이블을 이용하면 된다.
	 * @JoinTable : 별도의 테이블을 생성하고자 할 때 사용
	 * @JoinColumn : 기존 테이블에 칼럼을 추가할 때 사용
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pdsboard")
	@SequenceGenerator(name = "seq_pdsboard", sequenceName = "SEQ_PDSBOARD", allocationSize = 1, initialValue = 1)
	private Long pdsId;
	private String pdsName;
	private String pdsWriter;
	
	/*
	 * 데이터 저장시 두개의 테이블에 데이터가 저장되어야 한다.
	 * JPA는 처리하려는 엔티티 객체(PDSBoard, PDSFiles)의 상태에 따라서 종속적인 객체들의 영속성도 같이 처리되는 것을 "영속성 전이"라고 한다.
	 * 
	 * 영속성 전이에 대한 설정
	 * ALL : 모든 변경에 대해 전이
	 * PERSIST : 저장 시에만 전이
	 * MERGE : 병합 시에만 전이
	 * REMOVE : 삭제 시에만 전이
	 * REFRESH : 엔티티 매니저의 refresh() 호출 시 전이
	 * DETACH : 부모 엔티티가 detach 되면 자식 엔티티 역시 detach
	 * */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdsNo")
	private List<PDSFile> files;
	
}
