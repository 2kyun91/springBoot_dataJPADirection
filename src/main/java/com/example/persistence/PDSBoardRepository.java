package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.dao.PDSBoard;

/*
 * tbl1_pdsfiles 테이블에는 tbl1_pdsboard의 pdsId를 참조(일대다)해서 값이 들어가야 한다.
 * 하지만 PDSFile 클래스에는 PDSBoard에 대한 참조가 없기 때문에 단독으로 처리할 수가 없다.
 * 반면 PDSBoard는 모든 PDSFile 객체들의 참조를 보관할 수 있으므로 원하는 데이터 처리가 가능하다.
 * 
 * JPA를 이용해 객체를 설계할 때 단방향으로 설정하는 기준으로 데이터 가공에 대해 고민하고 결정해야한다. 
 * 
 * 이런 경우를 해결하기 위해서 각각 Repository를 생성하는 대신에 One에 해당하는 객체에 대한 Repository만을 이용하는것이 좋다.
 * */

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	
	@Modifying
	@Query("delete from PDSFile f where f.pdsFno = ?1")
	public int deletePDSFile(Long pdsFno);
	
//	@Query("select p, count(f) from PDSBoard b left outer join b.files f"
//			+ "on b.pdsId = f where p.pdsId > 0 group by b order by b.pdsId desc")
	@Query(value = "select b.pds_id, b.pds_name, b.pds_writer, count(f.pds_no) "
			+ "from TBL1_PDSBOARD b left outer join TBL1_PDSFILES f on b.pds_id = f.PDS_NO "
			+ "where b.pds_id > 0 "
			+ "group by b.pds_id, b.pds_name, b.pds_writer "
			+ "order by b.pds_id desc", nativeQuery = true)
	public List<Object[]> getSummary();
}
