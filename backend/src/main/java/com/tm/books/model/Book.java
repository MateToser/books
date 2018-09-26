package com.tm.books.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tm.books.common.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "b_book")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Book implements Serializable {

	/**
	 *
	 */
	@Transient
	private static final long serialVersionUID = 1417299992583842278L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Id
	private Integer id;

	@JsonView(Views.Public.class)
	@Column(name = "title", length = 50, nullable = false)
	private String title;

	@JsonView(Views.Public.class)
	@Column(name = "description", length = 500, nullable = false)
	private String description;

	@JsonView(Views.Public.class)
	@Column(name = "price", nullable = false)
	private Double price;

	@JsonView(Views.Public.class)
	@Column(name = "rating", nullable = false)
	private Integer rating;

	@JsonView(Views.Public.class)
	@Column(name = "approved", columnDefinition = "TINYINT DEFAULT 0")
	private Boolean approved;

	@JsonView(Views.Book.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Author author;

	@JsonView(Views.Book.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
