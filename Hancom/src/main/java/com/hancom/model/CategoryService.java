package com.hancom.model;

import java.util.List;
import java.util.Scanner;

import com.hancom.dto.CategoryVO;
import com.hancom.typingtest.TypingTest;
import com.hancom.view.CategoryView;

public class CategoryService {
	private CategoryDAO cat = new CategoryDAO();
	private Scanner sc = new Scanner(System.in);

	public int getSelectedCategories(int option) {
		if (option == 1) {
			CategoryView.printMusic(cat.getMusicCategories());
			System.out.print("곡 제목을 입력해주세요 ▶");
			String title = sc.nextLine();
			CategoryView.print(cat.getLyrics(title));
			return TypingTest.typingTest(cat.getLyrics(title));
		} else if (option == 2) {
			List<CategoryVO> wordCategories = cat.getWords();
			CategoryView.printAdviceandWord(wordCategories);

			if (wordCategories != null && !wordCategories.isEmpty()) {
				return TypingTest.typingTest(wordCategories);
			} else {
				System.out.println("▒발견된 단어가 없습니다, 직접 추가해보세요!▒");
			}
		} else if (option == 3) {
			List<CategoryVO> adviceCategories = cat.getAdvices();
			CategoryView.printAdviceandWord(adviceCategories);

			if (adviceCategories != null && !adviceCategories.isEmpty()) {
				return TypingTest.typingTest(adviceCategories);
			} else {
				System.out.println("▒발견된 명언이 없습니다, 직접 추가해보세요!▒");
			}
		}
		return 0;
	}

	public int getPractice(String teamName, String nickname, String title) {
		return TypingTest.typingTest(cat.getPractice(teamName, nickname, title));
	}

	public void addCategory(int categoryId, String title, String categoryName, String artist, String country,
			String lyrics) {
		cat.addCategory(categoryId, title, categoryName, artist, country, lyrics);
	}
}