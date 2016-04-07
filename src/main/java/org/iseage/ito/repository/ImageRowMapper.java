package org.iseage.ito.repository;

import org.iseage.ito.model.Image;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ImageRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ImageResultSetExtractor ex = new ImageResultSetExtractor();
        return ex.extractData(resultSet);
    }

    private class ImageResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Image image = new Image();
            image.setImage(resultSet.getString(1));
            image.setApproved(resultSet.getInt(2));
            return image;
        }
    }
}
