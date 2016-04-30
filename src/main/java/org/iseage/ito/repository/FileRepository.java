package org.iseage.ito.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;

import javax.annotation.Resource;

import org.jsoup.safety.Whitelist;
import org.jsoup.Jsoup;

@Repository
public class FileRepository {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    public String getGameDownloadPath() {
        String sql = "select file from download";
        return template.queryForObject(sql, String.class);
    }

    public void setGameDownloadPath(String path) {
		if(!path.startsWith("/var/game/")) return;
		try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"update download set file=?;");
			stmt.setString(1, Jsoup.clean(path, Whitelist.none()));
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
