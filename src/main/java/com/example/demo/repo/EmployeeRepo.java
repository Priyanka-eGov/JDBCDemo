package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.*;
@Repository
public class EmployeeRepo {
	//mapping Java objects into relational databases
	//implements basic CRUD operations like count, delete, deleteById, save, saveAll, findById, and findAll

    //Jdbc Template object to connect to the database and execute SQL queries
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Employee findById(long id) {
        String sqlQuery = "select id, first_name, last_name, email_address " +
                "from employee where id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToEmployee, id);
    }

    public List<Employee> findAll() {
        String sqlQuery = "select id, first_name, last_name, email_address from employee";
        return jdbcTemplate.query(sqlQuery, this::mapRowToEmployee);
    }

    public Employee save(Employee employee) {
        String sqlQuery = "insert into employee (id, first_name, last_name, email_address) " +
                "values (?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, employee.getId(), employee.getFirstname(), employee.getLastname(), employee.getEmail());

        return employee;
    }

    public void update(Employee employee, long id) {
        String sqlQuery = "update employee set " +
                "first_name = ?, last_name = ?, email_address = ? " +
                "where id = ?";

        jdbcTemplate.update(sqlQuery
                , employee.getFirstname()
                , employee.getLastname()
                ,employee.getEmail()
                , id);
    }


    public boolean delete(long id) {
        String sqlQuery = "delete from employee where id = ?";

        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    private Employee mapRowToEmployee(ResultSet resultSet, int rowNum) throws SQLException {
        // This method is an implementation of the functional interface RowMapper.
        // It is used to map each row of a ResultSet to an object.
        return Employee.builder()
                .id(resultSet.getLong("id"))
                .firstname(resultSet.getString("first_name"))
                .lastname(resultSet.getString("last_name"))
                .email(resultSet.getString("email_address"))
                .build();
    }

}


