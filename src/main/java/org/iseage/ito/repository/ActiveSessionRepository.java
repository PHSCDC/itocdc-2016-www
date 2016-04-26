package org.iseage.ito.repository;

import org.iseage.ito.model.ActiveSession;
import org.iseage.ito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

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
    
    public ActiveSession getActiveSessionById(String id) {
        String sql = "select * from active_sessions WHERE id='" + id.toString() + "';";
        List<ActiveSession> list = new ArrayList<>();
        for (Object o : template.query(sql, sessRowMapper)) {
            list.add((ActiveSession) o);
        }
        if(list.isEmpty())
			return null;
		else
			return list.get(0);
    }

    public void addActiveSession(String id, String ip) {
		Long time = Instant.now().getEpochSecond() + 3600;
        String sql = "insert into active_sessions (id, ip, expiry) values ('" + id + "', '" + ip + "', " + time.toString() + ");";
		template.update(sql);
		flushRepo();
    }
    
    public void rmActiveSession(String id) {
        String sql = "DELETE FROM active_sessions WHERE id='" + id + "';";
		template.update(sql);
		flushRepo();
    }
    
    private void flushRepo()
    {
		Long time = Instant.now().getEpochSecond();
		String sql = "DELETE FROM active_sessions WHERE expiry<" + time.toString() + ";";
		template.update(sql);
	}
}
