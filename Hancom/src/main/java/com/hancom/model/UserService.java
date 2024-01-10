package com.hancom.model;

import java.util.List;

import com.hancom.dto.UserVO;

public class UserService {
	UserDAO dao = new UserDAO();

	public boolean login(String nickname, String userPwd) {
		return dao.login(nickname, userPwd);
	}

	public boolean register(String username, String nickname, String userPwd, int userType, int userGoal,
			String university) {
		return dao.register(username, nickname, userPwd, userType, userGoal, university, "BRONZE");
	}

	public void updateType(String nickname, int userType) {
		dao.updateTypeAndLevel(nickname, userType);
	}

	public void userProfile(String nickname) {
		dao.userProfile(nickname);
	}
	/*Added*/
	public List<UserVO> getUserProfileList(String nickname) {
		return dao.getUserProfileList(nickname);
	}

	public boolean isNicknameExists(String nickname) {
		// DAO에서 닉네임 중복 체크를 수행하는 메서드 호출
		return dao.isNicknameExists(nickname);
	}

	public boolean updateUserProfile(String newNickname, String newName, String newUniv, int newGoal, String nickName) {
		return dao.updateUserProfile(newNickname, newName, newUniv, newGoal, nickName);
	}

	public boolean deleteUser(String nickname) {
		return dao.deleteUser(nickname);
	}

	public void getAllUserRank() {
		dao.getAllUserRank();
	}
	
	/*Added*/
	public List<UserVO> getAllUserRankByList() {
		return dao.getAllUserRankByList();
	}
	
	/*Added*/
	public List<UserVO> getUserRankByLevelToList(String level) {
		return dao.getUserRankByLevelToList(level);
	}

	public void getUserRankByLevel(String level) {
		dao.getUserRankByLevel(level);
	}

}