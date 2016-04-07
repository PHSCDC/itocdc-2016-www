package org.iseage.ito.repository;

import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "update users set password = '" + newPassword + "' where username = '" + username + "';";
        template.update(sql);
    }

    public boolean addUser(String username, String password, String email) {
        try {
            getUserByUsername(username);
            return false;
        } catch (RuntimeException e) {
            String sql = "insert into users (username, password, email, access) values ('" + username + "', '" + password + "', '" + email + "', 0);";
            template.update(sql);
            return true;
        }
    }

    public String getUserEmail(String username) {
        return getUserByUsername(username).getEmail();
    }

    public void changeEmail(String username, String email) {
        String sql = "update users set email = '" + email + "' where username = '" + username + "';";
        template.update(sql);
    }
}
