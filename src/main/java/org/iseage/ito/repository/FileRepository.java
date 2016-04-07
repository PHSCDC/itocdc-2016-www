package org.iseage.ito.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
        String sql = "update download set file='" + path + "';";
        template.update(sql);
    }
}
