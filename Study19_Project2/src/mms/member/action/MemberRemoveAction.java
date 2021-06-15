package mms.member.action;
//7-4 회원 정보 수정 요청을 처리하는 Action 클래스 구현
import java.util.Scanner;
import mms.member.svc.MemberRemoveService;
import mms.member.util.ConsoleUtil;

public class MemberRemoveAction implements Action {

	@Override
	public void execute(Scanner sc) {
		ConsoleUtil cu = new ConsoleUtil();
		MemberRemoveService memberRemoveService = new MemberRemoveService();
		String name= cu.getName("삭제할", sc);
		boolean removeSuccess =  memberRemoveService.removeMember(name);
		if(removeSuccess) {
			cu.printRemoveSuccessMessage(name);
		}else {
			cu.printRemoveFailMessage(name);
		}
	}

}