package com.myexpense.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myexpense.app.model.Expense;
import com.myexpense.app.repository.ExpenseRepository;

// 1. @Service marks this as the business logic component.
@Service
public class ExpenseService {

    // 2. Dependency Injection: We automatically get an instance of ExpenseRepository.
    @Autowired
    private ExpenseRepository expenseRepository;

    // ----------------------------------------------------
    // Implement CRUD Operations
    // ----------------------------------------------------

    // CREATE and UPDATE (save/save and flush is used for both by JPA)
    public Expense saveExpense(Expense expense) {
        // You can add validation logic here before calling the repository
        return expenseRepository.save(expense);
    }

    // READ ALL (findAll)
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // READ ONE (findById)
    public Optional<Expense> getExpenseById(Long id) {
        // We return an Optional<Expense> to force the caller (the Controller)
        // to handle the case where the expense ID isn't found.
        return expenseRepository.findById(id);
    }

    // DELETE (deleteById)
    public void deleteExpense(Long id) {
        // You could add permission checks or history logging here
        expenseRepository.deleteById(id);
    }
}