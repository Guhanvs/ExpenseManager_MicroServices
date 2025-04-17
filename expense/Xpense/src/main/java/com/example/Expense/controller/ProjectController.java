package com.example.Expense.controller;

import com.example.Expense.model.CategoryDTO;
import com.example.Expense.model.ExpenseDTO;
import com.example.Expense.model.ExpenseStatusDTO;
import com.example.Expense.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expenses")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProjectController {

@Autowired
        private ProjectService service;

    @PostMapping("add")
    public ExpenseStatusDTO addExpense(@RequestBody ExpenseDTO expense) {
        return service.addExpense( expense.getDate(),expense.getCategoryId(), expense.getAmount(), expense.getDescription());
    }


    @GetMapping("all")
    public List<ExpenseDTO> viewall() {
        return service.findall();
    }
        @GetMapping("amount")
        public List<ExpenseDTO> viewByAmount() {
            return service.getExpensesSortedByAmount();
        }

        @GetMapping("date")
        public List<ExpenseDTO> viewByDate() {
            return service.getExpensesSortedByDate();
        }


        @GetMapping("/export")
        public String exportToFile() {
            service.exportToFile();
            return "Exported successfully!";
        }
    @GetMapping("categories")
    public List<CategoryDTO> getAllCategories() {
        return service.getAllCategories();
    }
    }
