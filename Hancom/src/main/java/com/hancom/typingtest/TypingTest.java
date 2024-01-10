package com.hancom.typingtest;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.hancom.dto.CategoryVO;
import com.hancom.model.CategoryDAO;

public class TypingTest {

	public static int typingTest(List<CategoryVO> typelist) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryVO> sentences = typelist;

		Scanner scanner = new Scanner(System.in);
		int currentIndex = 0;
		int totalStrokes = 0;
		int correctStrokes = 0;
		long startTime = 0;
		long totalElapsedTime = 0;

		System.out.println("\n┴┬┴┬┴┬┴┬┴┴┬┴┬ 타자 연습을 시작합니다, 시작하려면 엔터를 눌러주세요 ┴┬┴┬┴┬┴┬┴┴┬┴┬");

		scanner.nextLine();

		while (currentIndex < sentences.size()) {
			CategoryVO sentence = sentences.get(currentIndex);
			String sentenceText = sentence.getText();
			if (sentenceText == null) {
				sentenceText = sentence.getTitle();
			}

			System.out.println(sentenceText);
			System.out.println("-----------------------------");

			startTime = System.nanoTime();
			String userInput = scanner.nextLine();

			if (!sentenceText.equals(userInput)) {
				System.out.println("┴┬┴┬┴┬┴┬┴┴┬┴┬<!>오타 발견, 종료합니다<!>┴┬┴┬┴┬┴┬┴┴┬┴┬\n");
				break;
			} else {
				correctStrokes += userInput.length();
			}

			totalStrokes += userInput.length();
			totalElapsedTime += System.nanoTime() - startTime;

			double timeInSeconds = TimeUnit.NANOSECONDS.toSeconds(totalElapsedTime);
			double typingSpeed = totalStrokes / timeInSeconds * 60;
			double accuracy = (double) correctStrokes / totalStrokes * 100;

			currentIndex++;

			if (currentIndex < sentences.size()) {
				System.out.println("\n◈다음 문장을 위해 <Enter>◈");
				scanner.nextLine();
			} else {
				System.out.println("┴┬┴┬┴┬┴┬┴┴┬┴┬<!>문제가 종료되었습니다<!>┴┬┴┬┴┬┴┬┴┴┬┴┬");
			}
		}

		System.out.println("\n|°°연습 종료 결과°°");
		System.out.println("|글자 수: " + totalStrokes);
		System.out.println("|정확도: " + ((double) correctStrokes / totalStrokes * 100) + "%");
		System.out.println("|시간: " + totalElapsedTime / 1e9 + " seconds"); // Convert to seconds
		System.out.println(
				"|분당 결과 타수: " + (int) ((totalStrokes / (totalElapsedTime / 1e9)) * 60) + " strokes per minute\n");

		return (int) ((totalStrokes / (totalElapsedTime / 1e9)) * 60);
	}
}