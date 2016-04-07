package org.iseage.ito.repository;

import org.iseage.ito.model.Comment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        CommentResultSetExtractor ex = new CommentResultSetExtractor();
        return ex.extractData(resultSet);
    }

    private class CommentResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Comment comment = new Comment();
            comment.setId(resultSet.getLong(1));
            comment.setComment(resultSet.getString(2));
            comment.setAuthor(resultSet.getString(3));
            return comment;
        }
    }
}
