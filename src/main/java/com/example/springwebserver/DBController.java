package com.example.springwebserver;

import org.hibernate.hql.internal.ast.tree.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

//import org.springframework.jdbc.core.support.JdbcDaoSupport;

@Component
public class DBController{

    final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(SpringWebServerApplication.class);

    public DBController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // public DBController(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public void insert(String ip) throws Exception {
        log.info("Creating tables");
        //jdbcTemplate.execute("DROP TABLE ipAddress IF EXISTS");
        //jdbcTemplate.execute("CREATE TABLE ipAddress(" + "ip VARCHAR(255), countCall int)");
        int countCall = 0;
        if(checkIfExists(ip)){
            IpAddress client = getIpAddress(ip);
            countCall = client.getCountCall();
            countCall++;
            //jdbcTemplate.update("UPDATE ipAddress SET countCall = " + countCall + " WHERE ip=" + ip);
            jdbcTemplate.update("UPDATE ipAddress SET countCall = (?) WHERE ip = (?)", countCall, ip);
        }else{
            jdbcTemplate.update("INSERT INTO ipAddress VALUES (?, ?)", ip, countCall);
        }
    }

    public boolean checkIfExists(String myParam){
        String sql = "SELECT * FROM ipAddress";
        List<IpAddress> allVisitedClients = jdbcTemplate.query(sql,new IPAddressMapper());
        for (int i = 0; i < allVisitedClients.size() ; i++){
            if ( myParam.equals(allVisitedClients.get(i).getIp())){
                return true;
            }
        }
        return false;
    }

    public IpAddress getIpAddress(String ip){
        String sql = "SELECT * FROM ipAddress WHERE IP = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ip}, new IPAddressMapper());
    }

}
