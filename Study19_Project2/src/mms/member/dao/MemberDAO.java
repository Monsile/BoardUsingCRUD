package mms.member.dao;
//9. Oracle DB로 필요한 SQL구문을 전송하는 클래스 구현
import static mms.member.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mms.member.vo.Member;

public class MemberDAO {
	
	Connection con;
	
	public MemberDAO(Connection con) {
		this.con = con;
	}
	//1.회원정보 입력
	public int insertNewMember(Member newMember) {
		
		int insertCount = 0;
		//Statement stmt=null;//old
		PreparedStatement pstmt = null;//new
		String sql = "INSERT INTO mms_member VALUES(mms_member_id_seq.nextval,?,?,?,?,?)";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newMember.getName());
			pstmt.setString(2, newMember.getAddr());
			pstmt.setString(3, newMember.getNation());
			pstmt.setString(4, newMember.getEmail());
			pstmt.setInt(5, newMember.getAge());
			
			insertCount = pstmt.executeUpdate();//1
			System.out.println(insertCount);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				close(pstmt);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return insertCount;//1
	}
	//2.회원목록 보기
	public ArrayList<Member> selectMemberList() {
		
		ArrayList<Member> memberList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM mms_member";
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<Member>();
			Member member = null;
			while(rs.next()){
				member = new Member(rs.getString("name"), rs.getString("addr"),
						rs.getString("nation"), rs.getString("email"), rs.getInt("age"));
				//레코드 하나 당 Member 객체 하나 씩 생성
				
				memberList.add(member);			
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				close(rs);
				close(pstmt);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return memberList;
	}

	//3.특정이름 찾기
public Member selectOldMember(String name) {
		
		Member oldMember=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM mms_member WHERE name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				oldMember = new Member(rs.getString("name"), rs.getString("addr"),
						rs.getString("nation"), rs.getString("email"), rs.getInt("age"));		
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				close(rs);
				close(pstmt);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return oldMember;
	}
	//4.내용 수정
	public int updateMember(Member updateMember) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE mms_member SET addr = ?, nation = ?, email = ?, age = ? WHERE name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateMember.getAddr());
			pstmt.setString(2, updateMember.getNation());
			pstmt.setString(3, updateMember.getEmail());
			pstmt.setInt(4, updateMember.getAge());
			pstmt.setString(5, updateMember.getName());
			
			updateCount = pstmt.executeUpdate();//1
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				close(pstmt);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return updateCount;//1
	}
	//5.삭제
	public int deleteMember(String name) {
		
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "DELETE mms_member WHERE name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			deleteCount = pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				close(pstmt);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return deleteCount;
	}
}