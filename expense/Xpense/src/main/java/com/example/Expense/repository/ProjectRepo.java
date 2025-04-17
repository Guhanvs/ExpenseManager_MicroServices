package com.example.Expense.repository;
import com.example.Expense.model.CategoryDTO;
import com.example.Expense.model.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProjectRepo {
    @Autowired
    private  JdbcTemplate jdbcTemplate;



    public void saveExpense(ExpenseDTO expense) {
        String sql = "INSERT INTO expenses (transaction_id, date, amount, description,categoryId) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                expense.getTransactionID(),
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getCategoryId());
    }
    public void saveExpenseWithCategoryID(ExpenseDTO expense) {
        String sql = "INSERT INTO expenses (transaction_id, date, amount, description,category_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                expense.getTransactionID(),
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getCategoryId());
    }

    public List<ExpenseDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM expenses",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getInt("category_id"),
                        rs.getDouble("amount"),
                        rs.getString("description")


                ));
    }

    public List<ExpenseDTO> findAllSortedByAmount() {
        return jdbcTemplate.query("SELECT * FROM expenses ORDER BY amount",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getInt("category_id"),
                        rs.getDouble("amount"),
                        rs.getString("description")

                ));
    }

    public List<ExpenseDTO> findAllSortedByDate() {
        return jdbcTemplate.query("SELECT * FROM expenses ORDER BY date",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getInt("category_id"),
                        rs.getDouble("amount"),
                        rs.getString("description")



                ));
    }
    public List<CategoryDTO> getAllCategories() {
        return jdbcTemplate.query("SELECT * FROM categories",
                (rs, rowNum) -> new CategoryDTO(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    public int getCategoryIdByName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT id FROM categories WHERE name = ?",
                new Object[]{name}, Integer.class
        );
    }
}