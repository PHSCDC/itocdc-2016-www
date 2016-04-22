package org.iseage.ito.repository;

import org.iseage.ito.model.Comment;
import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    @Autowired
    CommentRowMapper commentRowMapper;

    public List<Comment> getComments() {
        String sql = "select * from comments;";
        List<Comment> list = new ArrayList<>();
        for (Object o : template.query(sql, commentRowMapper)) {
            list.add((Comment) o);
        }
        return list;
    }

    public void addComment(String comment, String author) {
        String sql = "insert into comments (comment, author) values ('" + comment + "', '" + author + "');";
		if(comment.length()  < 350)
			template.update(sql);
    }
}
