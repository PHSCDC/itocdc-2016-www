package org.iseage.ito.repository;

import org.iseage.ito.model.Comment;
import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import org.jsoup.safety.Whitelist;
import org.jsoup.Jsoup;

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
		try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"insert into comments (comment, author) values (?, ?);");
			stmt.setString(1, Jsoup.clean(comment, Whitelist.simpleText()));
			stmt.setString(2, Jsoup.clean(author, Whitelist.simpleText()));
			if(comment.length()  < 350)
				stmt.execute();
		} ca tch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
