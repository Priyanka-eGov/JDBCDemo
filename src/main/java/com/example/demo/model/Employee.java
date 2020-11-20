package com.example.demo.model;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;


//persistence objects stored as a record in the database
//@Getter
//@Setter
@Data
@Entity
@Builder
@Table(name = "employee")
public class Employee {

    @Id  //Specifies the primary key of an entity
	@Column(name = "id",nullable = false) //Is used to specify the mapped column
	private long id;
	@Column(name = "last_name", nullable = false)
	private String lastname;

	@Column(name = "email_address", nullable = false)
	private String email;
	@Column(name = "first_name", nullable = false)
	private String firstname;


	public Map<String, Object> toMap() {
		Map<String, Object> values = new HashMap<>();
		values.put("first_name", firstname);
		values.put("last_name", lastname);
		values.put("email_address", email);
		values.put("id",id);

		return values;
	}


}
