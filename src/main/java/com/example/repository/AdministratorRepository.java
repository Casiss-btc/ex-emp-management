package com.example.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
     = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
     };
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password)";
        
        Integer id = administrator.getId();
        String name = administrator.getName();
        String mail_address = administrator.getMailAddress();
        String password = administrator.getPassword();
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id).addValue("name",name).addValue("mailAddress",mail_address).addValue("password",password);
        template.update(sql, param);
    }
    public Administrator findByMailAddressAddPassword(String mailAddress,String password) {
        String sql = "SELECT * FROM administrators WHERE mail_address = :mailAddress AND password = :password";
        List<Administrator> administratorList = template.query(sql, ADMINISTRATOR_ROW_MAPPER);
        if(administratorList.size()==0) {
            return null;
        }
        return administratorList.get(0);
    }
}
