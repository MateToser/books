package com.tm.books.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.tm.books.common.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "b_user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

	/**
	 *
	 */
	@Transient
	private static final long serialVersionUID = -5851794474500384768L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Id
	private Integer id;

	@JsonView(Views.Public.class)
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@JsonView(Views.Public.class)
	@Column(name = "first_name", length = 30, nullable = false)
	private String firstName;

	@JsonView(Views.Public.class)
	@Column(name = "last_name", length = 30, nullable = false)
	private String lastName;

	@JsonView(Views.Public.class)
	@Column(name = "approver", columnDefinition = "TINYINT DEFAULT 0")
	private Boolean approver;

	@JsonView(Views.User.class)
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Book> books;

}
