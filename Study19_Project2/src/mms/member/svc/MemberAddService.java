package mms.member.svc;
//8-1. 회원등록 Business Logic이 구현되는 Service 클래스 구현

import java.sql.Connection;

import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;

//트렌젝션 처리는 비지니스 단 에서 해 주는 것이 Convention 이다.
//즉, Connection 객체는 비지니스 단 에서 생성해서 사용하는 것이 일반적이다.

import mms.member.vo.Member;

public class MemberAddService {

	public boolean addMember(Member newMember) throws Exception{
		
		boolean isInsertSuccess = false;
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		int insertCount =  memberDAO.insertNewMember(newMember);
		if(insertCount >0) {
			JdbcUtil.commit(con);//완전저장
			isInsertSuccess = true;
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isInsertSuccess;
		
	}
}
