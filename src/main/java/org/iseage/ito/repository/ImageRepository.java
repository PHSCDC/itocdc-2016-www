package org.iseage.ito.repository;

import org.iseage.ito.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.safety.Whitelist;
import org.jsoup.Jsoup;

@Repository
public class ImageRepository {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    @Autowired
    ImageRowMapper imageRowMapper;

    public List<String> getApprovedImages() {
        String sql = "select * from images where approved = 1;";
        return getImages(sql).stream().map(Image::getImage).collect(Collectors.toList());
    }

    public List<String> getUnApprovedImages() {
        String sql = "select * from images where approved = 0;";
        return getImages(sql).stream().map(Image::getImage).collect(Collectors.toList());
    }

    public boolean addImage(String image) {
        if (imageAlreadyExists(image)) {
            return false;
        } else {
            try{
				PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
					"insert into images (image, approved) values (?, 0);");
				stmt.setString(1, Jsoup.clean(image, Whitelist.none()));
				stmt.execute();
			} catch(Exception e) {
				e.printStackTrace();
			}
            return true;
        }
    }

    public void approveImage(String image) {
        try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"update images set approved = 1 where image = ?;");
			stmt.setString(1, Jsoup.clean(image, Whitelist.none()));
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public void rejectImage(String image) {
        try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"update images set approved = 2 where image = ?;");
			stmt.setString(1, Jsoup.clean(image, Whitelist.none()));
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    private boolean imageAlreadyExists(String image) {
        try{
			PreparedStatement stmt = template.getDataSource().getConnection().prepareStatement(
				"select * from images where image = ?;");
			stmt.setString(1, Jsoup.clean(image, Whitelist.none()));
			ResultSet r = stmt.executeQuery();
			return r.next();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return true; // Is this an acceptable way to handle exceptions?
    }

    private List<Image> getImages(String sql) {
        List<Image> list = new ArrayList<>();
        for (Object o : template.query(sql, imageRowMapper)) {
            list.add((Image) o);
        }
        return list;
    }
}
