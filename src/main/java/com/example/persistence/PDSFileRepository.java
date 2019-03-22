package com.example.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.dao.PDSFile;

public interface PDSFileRepository extends CrudRepository<PDSFile, Long>{
	
	/*
	 * @Query는 기본적으로 select 구문만 지원하지만 @Modifying을 이용해서 DML(입력,수정,삭제) 작업을 처리할 수 있다.
	 * */
	@Modifying
//	@Query("update from tbl1_pdsfiles f set f.pds_file = ?2 where f.pds_fno = ?1")
	@Query("update from PDSFile f set f.pdsFile = ?2 where f.pdsFno = ?1")
	public int updatePDSFile(Long pdsFno, String newFileName);
	
}
