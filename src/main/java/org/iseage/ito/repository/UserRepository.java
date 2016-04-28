package org.iseage.ito.repository;

import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import org.jsoup.safety.Whitelist;
import org.jsoup.Jsoup;

@Repository
public class UserRepository {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;
    @Autowired
    private UserRowMapper userRowMapper;

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        return getUserList(sql);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT userID, username, password, email, access FROM users WHERE username = '" + username + "';";
        List<User> list = getUserList(sql);
        if (list.isEmpty()) {
            System.out.println("User '" + username + "' doesn't exist!");
            throw new RuntimeException("User does not exist!");
        }
        System.out.println("User '" + username + "' found");
        return list.get(0);
    }

    private List<User> getUserList(String sql) {
        List<User> list = new ArrayList<User>();
        for (Object o : template.query(sql, userRowMapper)) {
            list.add((User) o);
        }
        return list;
    }

    public void changePassword(String username, String newPassword) {
        try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"update users set password = ? where username = ?;");
			stmt.setString(1, newPassword);
			stmt.setString(2, Jsoup.clean(username, Whitelist.simpleText()));
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public boolean addUser(String username, String password, String email) {
        try {
            getUserByUsername(username);
            return false;
        } catch (RuntimeException e) {
			try{
				PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
					"insert into users (username, password, email, access) values (?, ?, ?, 0);");
				stmt.setString(1, Jsoup.clean(username, Whitelist.simpleText()));
				stmt.setString(2, password);
				stmt.setString(3, Jsoup.clean(email, Whitelist.simpleText()));
				stmt.execute();
			} catch(Exception ee) {
				ee.printStackTrace();
				return false;
			}
            return true;
        }
    }

    public String getUserEmail(String username) {
        return getUserByUsername(username).getEmail();
    }

    public void changeEmail(String username, String email) {
        String sql = "update users set email = '" + email + "' where username = '" + username + "';";
        template.update(sql);
        try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"update users set email = ? where username = ?;");
			stmt.setString(1, Jsoup.clean(email, Whitelist.simpleText()));
			stmt.setString(2, Jsoup.clean(username, Whitelist.simpleText()));
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
