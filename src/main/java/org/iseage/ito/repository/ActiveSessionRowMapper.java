package org.iseage.ito.repository;

import org.iseage.ito.model.ActiveSession;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActiveSessionRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ActiveSessionResultSetExtractor ex = new ActiveSessionResultSetExtractor();
        return ex.extractData(resultSet);
    }

    private class ActiveSessionResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            ActiveSession sess = new ActiveSession();
            sess.setId(resultSet.getString(1));
            sess.setIp(resultSet.getString(2));
            return sess;
        }
    }
}
