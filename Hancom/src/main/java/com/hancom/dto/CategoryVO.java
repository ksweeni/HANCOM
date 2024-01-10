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
public class CategoryVO {
	private int id;
	private String title;
	private String category_name;
	private String artist;
	private int category_id;
	private String country;
	private String text;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(">");
		if (title != null) {
			builder.append("[").append(title).append("]");
		}
		if (artist != null) {
			builder.append(" / artist[").append(artist).append("]");
		}
		if (text != null) {
			builder.append("text <").append(text).append(">");
		}
		return builder.toString();
	}

}