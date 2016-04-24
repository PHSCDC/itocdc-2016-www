package org.iseage.ito.repository;

import org.iseage.ito.model.ActiveSession;
import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActiveSessionRepository {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    @Autowired
    ActiveSessionRowMapper sessRowMapper;

    public List<ActiveSession> getActiveSessions() {
        String sql = "select * from active_sessions;";
        List<ActiveSession> list = new ArrayList<>();
        for (Object o : template.query(sql, sessRowMapper)) {
            list.add((ActiveSession) o);
        }
        return list;
    }

    public void addActiveSession(long id, String ip) {
        String sql = "insert into active_sessions (id, ip) values (" + id + ", '" + ip + "');";
		template.update(sql);
    }
}
