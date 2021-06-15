package mms.member.controller;
//6. 사용자의 전체 요청을 제어하는 클래스 구현
import java.util.Scanner;
import mms.member.action.Action;

public class MemberController {
	
	public void processRequest(Action action,Scanner sc){
		try{
		action.execute(sc);
	
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
}
