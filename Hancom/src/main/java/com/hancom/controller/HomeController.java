package com.hancom.controller;

import java.util.Scanner;

import com.hancom.model.CategoryDAO;
import com.hancom.model.CategoryService;
import com.hancom.model.TeamService;
import com.hancom.model.UserService;
import com.hancom.view.CategoryView;
import com.hancom.view.TeamView;

public class HomeController {
	static Scanner sc = new Scanner(System.in);
	static CategoryService cs = new CategoryService();
	static UserService us = new UserService();
	static TeamService ts = new TeamService();
	static String userNick;

	public static void main(String[] args) {

		if (args.length > 0) {
			userNick = args[0];
		}

		boolean run = true;

		while (run) {
			int option = main();
			switch (option) {
			case 1:
				personalPractice();
				break;
			case 2:
				int groupOption = groupMenu();
				if (groupOption == 1)
					TeamView.printTeamInfo(ts.getAllTeams());
				else if (groupOption == 2)
					groupPractice();
				else if (groupOption == 3)
					createGroup();
				else
					exitGroup();
				break;
			case 3:
				createCategory();
				break;
			case 4:
				personalSetting();
				break;
			case 5:
				ranking();
				break;
			case 6:
				System.out.println("\n┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┴┬┬┴┬┴┬ T H E E N D ┬┴┴┬┴┬┬┴┬┴┬┬┴┬┴┬┴┬┴┬┴┴┬┬┴┴┬┴┬┴┬┴┬");
				System.exit(0);
				break;
			default:
				System.out.println(". · ◈ 올바른 선택을 입력하세요 ◈ · .");
				break;
			}
		}
	}

	public static int getIntInput(String message, int min, int max) {
		int value;
		while (true) {
			System.out.print(message);
			try {
				value = Integer.parseInt(sc.nextLine());
				if (value < min || value > max) {
					System.out.println(". · ◈입력 범위를 벗어났습니다. " + min + "에서 " + max + " 사이의 값을 입력하세요◈ · .");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(". · ◈올바른 숫자를 입력하세요◈ · .");
			}
		}
		return value;
	}

	private static void ranking() {
		System.out.println("\n━━━━━━━━━━━━━°랭킹조회°━━━━━━━━━━━━━━");
		System.out.println("① 전체 랭킹 조회 | ② LEVEL별 랭킹 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		int rank1 = getIntInput("입력 ▶", 1, 2);

		if (rank1 == 1)
			us.getAllUserRank();
		else {
			System.out.print("조회할 level[BRONZE/SILVER/GOLD] ▶");
			us.getUserRankByLevel(sc.nextLine());
		}

	}

	private static void personalSetting() { // 개인정보설정
		us.userProfile(userNick);
		System.out.println("\n━━━━━━━°개인정보설정°━━━━━━━");
		System.out.println("① 정보 수정 | ② 회원탈퇴");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
		int op = getIntInput("입력 ▶", 1, 2);

		if (op == 1) {
			System.out.print("새로운 닉네임을 입력해주세요 ▶");
			String newNickname = sc.nextLine();
			System.out.print("새로운 이름을 입력해주세요 ▶");
			String newName = sc.nextLine();
			System.out.print("새로운 학교명을 입력하세요 ▶");
			String newUniv = sc.nextLine();
			System.out.print("새로운 목표 타수를 입력하세요 ▶");
			int newGoal = Integer.parseInt(sc.nextLine());

			boolean updated = us.updateUserProfile(newNickname, newName, newUniv, newGoal, userNick);
			if (updated) {
				System.out.println(". ·°업데이트된 정보°· .");
				userNick = newNickname;
				us.userProfile(newNickname);
			} else {
				System.out.println("(o`ㅅ`o) . ·사용자 정보 업데이트에 실패했습니다 · .\n");
			}
		} else {
			System.out.print("\n━━━━━━━━━━━━°회원탈퇴°━━━━━━━━━━━━━\n닉네임을 입력하세요 ▶");
			String exituser = sc.nextLine();
			if (us.deleteUser(exituser)) {
				System.out.println(". · (o > v < o) 회원 탈퇴 성공 · .\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
				LoginController.main(null); // 로그인 화면으로 돌아감
			} else
				System.out.println("(o`ㅅ`o) . ·회원 삭제 실패, 해당 닉네임을 찾을 수 없습니다 · .\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
	}

	private static void createCategory() { // 문제 만들기
		CategoryDAO cat = new CategoryDAO();
		System.out.println("\n문제만들기 ▶▶");

		CategoryView.printAdviceandWord(cat.getWords());
		CategoryView.printAdviceandWord(cat.getAdvices());
		CategoryView.printMusic(cat.getMusicCategories());

		int categoryID = getIntInput("카테고리 ID 입력(음악=1/단어=2/명언=3) ▶", 1, 3);
		String categoryName = "";

		if (categoryID == 2) {
			categoryName = "단어";
		}
		if (categoryID == 3) {
			categoryName = "음악";
		}

		System.out.print("title 입력 ▶");
		String title = sc.nextLine();
		String artist = null;
		String country = null;
		String text = null;

		if (categoryID == 1) {
			categoryName = "음악";
			System.out.print("가수 입력 ▶");
			artist = sc.nextLine();
			System.out.print("한국/외국 입력 ▶");
			country = sc.nextLine();
			System.out.print("가사 입력 ▶");
			text = sc.nextLine();
		}

		cs.addCategory(categoryID, title, categoryName, artist, country, text);
	}

	private static void exitGroup() { // 그룹 나가기
		System.out.println("\n━━━━━━━━━━━━━°팀 탈퇴°━━━━━━━━━━━━━");
		System.out.print("닉네임을 입력하세요 ▶");
		String nickname = sc.nextLine();
		TeamView.print(ts.getMyTeams(nickname));
		System.out.print("팀명을 입력하세요 ▶");
		String teamName = sc.nextLine();
		ts.leaveTeam(nickname, teamName);
	}

	private static void createGroup() { // 그룹 만들기
		System.out.println("\n━━━━━━━━━━━━━°그룹 만들기°━━━━━━━━━━━━━");
		System.out.print("그룹장을 입력해주세요 ▶");
		String teamOwner = sc.nextLine();
		System.out.print("멤버명을 입력해주세요 ▶");
		String teamMember = sc.nextLine();
		System.out.print("그룹명을 입력해주세요 ▶");
		String teamName = sc.nextLine();
		System.out.print("연습명을 입력해주세요 ▶");
		String practiceTitle = sc.nextLine();
		System.out.print("카테고리명을 입력해주세요(음악/단어/명언) ▶");
		String categoryName = sc.nextLine();
		System.out.print("title을 입력해주세요 ▶");
		String title = sc.nextLine();
		ts.createNewTeam(teamOwner, teamMember, teamName, practiceTitle, categoryName, title);
	}

	private static void groupPractice() { // 2. 연습하기
		if (ts.getMyTeams(userNick).isEmpty())
			System.out.println("(o`ㅅ`o) . · 아직 그룹에 속해있지 않습니다!· .\n");
		else {
			TeamView.print(ts.getMyTeams(userNick));
			System.out.print("팀명을 입력하세요 ▶");
			String teamName = sc.nextLine();
			System.out.print("연습명을 입력하세요 ▶");
			String practiceName = sc.nextLine();
			System.out.print("노래제목/단어/명언을 입력하세요 ▶");
			String title = sc.nextLine();
			int score = cs.getPractice(practiceName, userNick, title);
			// 팀에 자신의 score 업데이트 및 avg 다시 계산
			ts.updateMemberTypingResult(userNick, teamName, practiceName, title, score);
		}

	}

	private static int groupMenu() {
		System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━°그룹°━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("① 모든 그룹 조회 | ② 연습하기 | ③ 그룹 만들기 | ④ 그룹 나가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		int option = getIntInput("입력 ▶", 1, 4);
		return option;
	}

	private static void personalPractice() {
		System.out.println("\n━━━━━━━━°개인연습°━━━━━━━━");
		System.out.println("① 음악 | ② 단어 | ③ 명언");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");
		int option = getIntInput("입력 ▶", 1, 3);
		int result = cs.getSelectedCategories(option);
		us.updateType(userNick, result);
	}
							
	private static int main() {
		System.out.println("┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┬▒ " + userNick + ", w e l c o m e h o m e ▒┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┬");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━°°━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("① 개인연습 | ② 그룹 | ③ 문제만들기 | ④ 개인정보설정 | ⑤ 랭킹조회 | ⑥ 종료");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		int option = getIntInput("choose your number ▶", 1, 6);
		return option;
	}
}