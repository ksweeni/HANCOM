package com.hancom.controller;

import java.util.Scanner;
import com.hancom.model.UserService;

public class LoginController {
	private static Scanner sc = new Scanner(System.in);
	private static UserService us = new UserService();

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┬ H A N C O M T A J A ┴┬┴┬┴┬┴┬┴┴┬┴┬┴┬┴┬");
			System.out.println("━━━━━━━━━━━━━°°━━━━━━━━━━━━━");
			System.out.println("① 로그인 | ② 회원가입 | ③ 종료");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			int option = HomeController.getIntInput("choose your number ▶", 1, 3);
			
			switch (option) {
			case 1:
				userLogin();
				break;
			case 2:
				userRegister();
				break;
			case 3:					  
				System.out.println("\n┬┴┬┴┴┬┴┬┴┬┴┴┬┬┴┬┬┴┬┴┬ T H E E N D ┬┴┴┴┬┴┬┴┬┴┬┬┴┬┬┴┬┴┬┬┴");
				run = false;
				break;
			default:
				System.out.println("\n잘못된 번호를 입력하셨습니다. 1, 2, 또는 3을 입력하세요.");
			}
		}
	}

	private static void userLogin() {
		String nickname;
		String password;
		System.out.println("\n━━━━━━━━━°LOGIN°━━━━━━━━━");
		System.out.print("닉네임 입력 ▶");
		nickname = sc.nextLine();
		System.out.print("비밀번호 입력 ▶");
		password = sc.nextLine();

		if (us.login(nickname, password)) {
			System.out.println(". · ◈로그인 성공◈ · .\n");
			HomeController.main(new String[] { nickname });
		} else {
			System.out.println(". · ◈로그인 실패, 다시 시도해주세요◈ · .\n");
		}
	}

	private static void userRegister() {
		String username;
		String university;
		int goal = 0;
		int typing = 0;
		String nickname;
		String password;
		System.out.println("\n━━━━━━━━━━━━━°REGISTER°━━━━━━━━━━━━━");

		System.out.print("닉네임 입력 ▶");
		nickname = sc.nextLine();

		if (us.isNicknameExists(nickname)) {
			System.out.println(". · ◈이미 사용중인 닉네임, 다시 시도해주세요◈ · .\n");
			return;
		}

		System.out.print("이름 입력 ▶");
		username = sc.nextLine();
		System.out.print("비밀번호 입력 ▶");
		password = sc.nextLine();
		System.out.print("학교 입력 (없으면 '일반' 입력) ▶");
		university = sc.nextLine();
		System.out.print("목표 타수 입력 ▶");
		goal = sc.nextInt();
		System.out.print("현재 타수 입력 (없으면 0 입력) ▶");
		typing = sc.nextInt();
		sc.nextLine();

		if (us.register(username, nickname, password, typing, goal, university)) {
			System.out.println(". · ◈회원가입 성공, 로그인 해주세요◈ · .\n");
			userLogin();
		} else {
			System.out.println(". · ◈회원가입 실패, 다시 시도해주세요◈ · .\n");
		}
	}
}