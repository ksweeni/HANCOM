package com.hancom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserVO {
	private int user_id;
	private String nickname;
	private String user_name;
	private String user_pwd;
	private int user_type;
	private int user_goal;
	private String university;
	private String user_level;
}