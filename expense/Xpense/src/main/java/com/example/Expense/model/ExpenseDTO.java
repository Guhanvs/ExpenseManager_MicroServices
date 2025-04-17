package com.example.Expense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String transactionID;
    private String date;
    private int categoryId;
    private double amount;
    private String description;



}