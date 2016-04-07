package org.iseage.ito.repository;

import org.iseage.ito.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            String sql = "insert into images (image, approved) values ('" + image + "', 0);";
            template.update(sql);
            return true;
        }
    }

    public void approveImage(String image) {
        String sql = "update images set approved = 1 where image = '" + image + "';";
        template.update(sql);
    }

    public void rejectImage(String image) {
        String sql = "update images set approved = 2 where image = '" + image + "';";
        template.update(sql);
    }

    private boolean imageAlreadyExists(String image) {
        String sql = "select * from images where image = '" + image + "';";
        return getImages(sql).size() > 0;
    }

    private List<Image> getImages(String sql) {
        List<Image> list = new ArrayList<>();
        for (Object o : template.query(sql, imageRowMapper)) {
            list.add((Image) o);
        }
        return list;
    }
}
