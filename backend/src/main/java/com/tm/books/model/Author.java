package com.tm.books.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "b_author")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Author implements Serializable {

	/**
	 *
	 */
	@Transient
	private static final long serialVersionUID = 2491532558753196185L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Id
	private Integer id;

	@JsonView(Views.Public.class)
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@JsonView(Views.Author.class)
	@OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
	private Set<Book> books;

}
