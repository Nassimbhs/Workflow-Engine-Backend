package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Service
public class TableService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getTables(String jdbcUrl, String username, String password, String sgbd) {
        String query;

        if (sgbd == null) {
            sgbd = "mysql";
        }

        if (sgbd.equalsIgnoreCase("mysql")) {
            query = "SHOW TABLES";
        } else if (sgbd.equalsIgnoreCase("postgres")) {
            query = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        } else if (sgbd.equalsIgnoreCase("oracle")) {
            query = "SELECT table_name FROM all_tables";
        } else {
            throw new IllegalArgumentException("Unsupported SGBD: " + sgbd);
        }

        List<String> tables = jdbcTemplate.queryForList(query, String.class);
        return tables;
    }



}