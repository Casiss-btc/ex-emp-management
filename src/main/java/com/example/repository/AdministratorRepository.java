package main.java.com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;

import main.java.com.example.domain.Administrator;

@Repository

public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
    = new BeanPropertyRowMapper<>(Administrator.class);
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (mail_address, password) VALUES (:mailAdress, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mailAdress", administrator.getMailAddress());
        paramMap.put("password", administrator.getPassword());
        template.update(sql, paramMap);
    }
    public Administrator findByMailAdressAddPassword(String mailAdress,String password) {
        String sql = "SELECT * FROM administrators WHERE mail_address = :mailAdress AND password = :password";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mailAdress", mailAdress);
        paramMap.put("password", password);
        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if(administratorList.size()==0) {
            return null;
        }
        return administratorList.get(0);
    }
}
