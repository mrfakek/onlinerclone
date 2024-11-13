package by.tms.onlinerclonec30onl.dao;


import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.mappers.ProductTypeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTypeDAO implements InterfaceDAO<ProductType> {
    private final JdbcTemplate jdbcTemplate;
    private final ProductTypeMapper rowMapper;

    public ProductTypeDAO(JdbcTemplate jdbcTemplate, ProductTypeMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;

    }
    @Override
    public void save(ProductType entity) {
        jdbcTemplate.update("INSERT INTO product_type VALUES (default,?,?)",entity.getTypeName(),entity.getPhoto());
    }

    @Override
    public void delete(ProductType entity) {
        jdbcTemplate.update("DELETE FROM product_type WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product_type WHERE id=?",id);
    }

    @Override
    public void update(long id, ProductType entity) {
        jdbcTemplate.update("UPDATE product_type SET type_name = ?, photo = ? WHERE id = ?",entity.getTypeName(),entity.getPhoto(),id);
    }

    @Override
    public List<ProductType> findAll() {
        return jdbcTemplate.query("SELECT * FROM product_type", rowMapper);
    }

    @Override
    public ProductType findByID(long id) {
        return jdbcTemplate.query("SELECT * FROM product_type WHERE id=?",new Object[]{id},rowMapper).stream().findFirst().orElse(new ProductType());
    }
}
