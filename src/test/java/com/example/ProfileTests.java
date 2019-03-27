package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dto.Member;
import com.example.dto.Profile;
import com.example.persistence.MemberRepository;
import com.example.persistence.ProfileRepository;

import lombok.extern.java.Log;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Log // Lombok의 로그를 사용
//@Commit // 테스트 결과 커밋 
public class ProfileTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
//	@Test
//	public void testInsertMembers() {
//		// IntStream.range(?, ?)를 이용하면 ?부터 ?까지의 숫자를 생성할 수 있다. 
//		IntStream.range(1, 101).forEach(i -> {
//			Member member = new Member();
//			member.setUId("user" + i);
//			member.setUPw("pw" + i);
//			member.setUName("사용자 " + i);
//			
//			memberRepository.save(member);
//		});
//	}
	
//	@Test
//	public void testInsertProfile() {
//		/*
//		 * Profile 데이터는 반드시 Member 객체에 대한 참조가 필요하다.
//		 * MemberRepository를 인터페이스를 이용해 실제 Member 객체를 가져와서 처리할 수 있지만
//		 * Member를 읽어와서 Profile을 읽어야 하기 때문에 효율적이지 못하다.
//		 * 따라서 Member 객체만 잠시 생성해서 uId만을 지정하는 방식이 더 효율적이다.
//		 * */
//		Member member = new Member();
//		member.setUId("user1");
//		
//		for (int i = 1; i <5 ; i++) {
//			Profile profile = new Profile();
//			profile.setFName("face" + i + ".jpg");;
//			
//			if (i ==1) {
//				profile.setStatus(true);
//			}
//			
//			profile.setMember(member);
//			profileRepository.save(profile);
//		}
//	}
	
//	@Test
//	public void testFetchJoin1() {
//		List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");
//		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
//	}
	
//	@Test
//	public void testFetchJoin2() {
//		List<Object[]> result = memberRepository.getMemberWithProfile("user1");
//		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
//	}
}
