package by.tms.onlinerclonec30onl.dao;


import by.tms.onlinerclonec30onl.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO implements InterfaceDAO<Customer> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Customer customer) {
jdbcTemplate.update("INSERT INTO customer VALUES (default,?,?,?,?,?)",customer.getAccount().getId(),customer.getFirstName(),customer.getLastName(),customer.getPhone(),customer.getAddress());
    }

    @Override
    public void delete(Customer customer) {
jdbcTemplate.update("DELETE FROM customer WHERE id=?",customer.getId());
    }

    @Override
    public void deleteById(long id) {
jdbcTemplate.update("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public void update(long id, Customer customer) {
        jdbcTemplate.update("UPDATE customer SET id_account = ?,first_name = ?,last_name = ?,phone = ?, address = ? WHERE id = ?",customer.getAccount().getId(),customer.getFirstName(),customer.getLastName(),customer.getPhone(),customer.getAddress(),id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public Customer findByID(long id) {
        return jdbcTemplate.query("SELECT * FROM customer WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Customer.class)).stream().findFirst().orElse(new Customer());
    }
}
