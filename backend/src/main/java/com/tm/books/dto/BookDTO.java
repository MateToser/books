package com.tm.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	private String bookTitle;
	private String bookDescription;
	private Double bookPrice;
	private Integer bookRating;

	private Integer userId;

	private String authorName;

}
