package mms.member.action;

import java.util.Scanner;

import mms.member.svc.MemberModifyService;
import mms.member.util.ConsoleUtil;
import mms.member.vo.Member;

//7-3 회원 정보 수정 요청을 처리하는 Action 클래스 구현


public class MemberModifyAction implements Action {

	@Override
	public void execute(Scanner sc) throws Exception {
		ConsoleUtil cu = new ConsoleUtil();
		MemberModifyService memberModifyService = new MemberModifyService();
		String name = cu.getName("수정할", sc);
		Member oldMember = memberModifyService.getOldMember(name);
		Member updateMember = cu.getUpdateMember(sc, oldMember);
		
		boolean isModifySuccess = memberModifyService.modifyMember(updateMember);
		if(isModifySuccess)
			cu.printModifySuccessMessage(updateMember);
		else
			cu.printModifyFailMessage(updateMember);
		
	}
}


