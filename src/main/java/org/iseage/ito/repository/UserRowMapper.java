package org.iseage.ito.repository;

import org.iseage.ito.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        UserResultSetExtractor ex = new UserResultSetExtractor();
        return ex.extractData(resultSet);
    }

    private class UserResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            User user = new User();
            user.setUserID(resultSet.getLong(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setAccess(resultSet.getInt(5));
            return user;
        }
    }
}
