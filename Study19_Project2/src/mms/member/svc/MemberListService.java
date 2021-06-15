package mms.member.svc;
//8-2. 회원목록 보기 요청을 처리하는 Business Logic이 구현되는 Service 클래스 구현

import java.sql.Connection;
import java.util.ArrayList;

import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberListService {

	public ArrayList<Member> getMemberList() {
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		
		ArrayList<Member> memberList =  memberDAO.selectMemberList();
		return memberList;
	}

}

















