package mms.member.svc;
//8-3. 회원정보 수정 요청을 처리하는 Business Logic이 구현되는 Service 클래스 구현
import java.sql.Connection;
import static mms.member.db.JdbcUtil.*;
import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberModifyService {

	public Member getOldMember(String name) {
		Member oldMember = null;
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		oldMember =  memberDAO.selectOldMember(name);
		JdbcUtil.close(con);
		
		return oldMember;
	}

	//실제 DB에 수정
	public boolean modifyMember(Member updateMember) {
		
		boolean isModifySuccess = false;
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		int updateCount =  memberDAO.updateMember(updateMember);
		if(updateCount > 0 ) {
			isModifySuccess = true;
			JdbcUtil.commit(con);	
		}else
			rollback(con);
		
		JdbcUtil.close(con);
		return isModifySuccess;
	}

}














