package com.example.springwebserver;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IPAddressMapper implements RowMapper<IpAddress> {

    @Override
    public IpAddress mapRow(ResultSet rs, int rowNum) throws SQLException {

        IpAddress ipAddress = new IpAddress();
        ipAddress.setIp(rs.getString("IP"));
        ipAddress.setCountCall(rs.getInt("CountCALL"));

        return ipAddress;

    }

}
