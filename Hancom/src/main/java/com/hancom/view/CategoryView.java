package com.hancom.view;

import java.util.List;

import com.hancom.dto.CategoryVO;

public class CategoryView {

	public static void print(List<CategoryVO> deptlist) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━TYPINGLIST━━━━━━━━━━━━━━━━━━━━");
		deptlist.stream().forEach(category -> {
			System.out.println(category);
		});
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public static void printCategoryList(List<CategoryVO> deptlist, String listName) {
		System.out.printf("━━━━━━━━━━━━━━━━━━━━[%s]━━━━━━━━━━━━━━━━━━━━%n", listName);
		deptlist.forEach(category -> {
			String artistInfo = listName.equals("MUSICLIST") ? " | artist [" + category.getArtist() + "]" : "";
			System.out.printf("[%s]%s%n", category.getTitle(), artistInfo);
		});
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public static void printAdviceandWord(List<CategoryVO> deptlist) {
		printCategoryList(deptlist, "LIST");
	}

	public static void printMusic(List<CategoryVO> deptlist) {
		printCategoryList(deptlist, "MUSICLIST");
	}

}