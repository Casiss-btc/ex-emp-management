package com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
    = new BeanPropertyRowMapper<>(Employee.class);
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER BY hire_date DESC";
        List<Employee> employees = template.query(sql, EMPLOYEE_ROW_MAPPER);
        return employees;
    }
    public Employee load(Integer id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }
    public void update(Employee employee) {
        String sql = "UPDATE employees SET "+
        "name = :name, "+
        "image = :image, "+
        "gender = :gender, "+
        "hire_date = :hireDate "+
        "mail_address = :mailAddress, "+
        "zip_code = :zipCode, "+
        "address = :address, "+
        "telephone = :telephone, "+
        "salary = :salary, "+
        "characteristics = :characteristics, "+
        "dependents_count = :dependentsCount, "+
        "WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", employee.getId()); // 主キーで指定
        paramMap.put("name", employee.getName());
        paramMap.put("name", employee.getName());
        paramMap.put("hireDate", employee.getHireDate());
        paramMap.put("mailAddress", employee.getMailAddress());
        paramMap.put("zipCode", employee.getZipCode());
        paramMap.put("address", employee.getAddress());
        paramMap.put("telephone", employee.getTelephone());
        paramMap.put("salary", employee.getSalary());
        paramMap.put("characteristics", employee.getCharacteristics());
        paramMap.put("dependentsCount", employee.getDependentsCount());

        template.update(sql, paramMap);
    }
}
