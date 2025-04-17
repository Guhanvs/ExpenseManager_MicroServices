package com.example.Expense.service;

import com.example.Expense.model.CategoryDTO;
import com.example.Expense.model.ExpenseDTO;
import com.example.Expense.model.ExpenseStatusDTO;
import com.example.Expense.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

        @Autowired
        private ProjectRepo repository;

        @Autowired
        private ExpenseStatusDTO expenseStatusDTO;

    public ProjectService(ProjectRepo repository) {
        this.repository = repository;
    }

    public ExpenseStatusDTO addExpense(String date, int categoryId, double amount, String description) {
//        int categoryId = repository.getCategoryIdByName(categoryName);
        ExpenseDTO expense = new ExpenseDTO(UUID.randomUUID().toString(), date, categoryId, amount, description);
        repository.saveExpenseWithCategoryID(expense);
        expenseStatusDTO.setMessage("Expense saved with category");
        expenseStatusDTO.setStatus("Success");
        return expenseStatusDTO;
    }


    public List<ExpenseDTO> findall() {
        return repository.findAll();
    }

        public List<ExpenseDTO> getExpensesSortedByAmount() {
            return repository.findAllSortedByAmount();
        }

        public List<ExpenseDTO> getExpensesSortedByDate() {
            return repository.findAllSortedByDate();
        }

        public void exportToFile() {
            List<ExpenseDTO> expenses = repository.findAll();
            try (FileWriter writer = new FileWriter("expenses.txt")) {
                for (ExpenseDTO e : expenses) {
                    writer.write(e.toString() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error exporting to file", e);
            }
        }

        @Scheduled(fixedRate = 5000)
        public void autoSaveToFile() {
            exportToFile();
        }

    public List<CategoryDTO> getAllCategories() {
        return repository.getAllCategories();
    }
}


