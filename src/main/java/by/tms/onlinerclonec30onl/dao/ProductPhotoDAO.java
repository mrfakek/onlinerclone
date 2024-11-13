package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import by.tms.onlinerclonec30onl.mappers.ProductPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductPhotoDAO implements InterfaceDAO<ProductPhoto> {
    private final JdbcTemplate jdbcTemplate;
    private final ProductPhotoMapper rowMapper;
@Autowired
    public ProductPhotoDAO(JdbcTemplate jdbcTemplate, ProductPhotoMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }
    @Override
    public void save(ProductPhoto entity) {
        jdbcTemplate.update("INSERT INTO product_photo VALUES (default,?,?)",entity.getProduct().getId(),entity.getPhoto());
    }

    @Override
    public void delete(ProductPhoto entity) {
        jdbcTemplate.update("DELETE FROM product_photo WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product_photo WHERE id=?",id);
    }

    @Override
    public void update(long id, ProductPhoto entity) {
        jdbcTemplate.update("UPDATE product_photo SET photo = ? WHERE id = ?",entity.getPhoto(),id);
    }

    @Override
    public List<ProductPhoto> findAll() {
        return jdbcTemplate.query("SELECT * FROM product_photo", rowMapper);
    }

    @Override
    public ProductPhoto findByID(long id) {
        return jdbcTemplate.query("SELECT * FROM product_photo WHERE id=?",new Object[]{id},rowMapper).stream().findFirst().orElse(new ProductPhoto());
    }
}
