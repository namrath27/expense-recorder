package com.myexpense.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// ⬅️ Import all mapping annotations
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myexpense.app.model.Expense; // ⬅️ NOW USED for return types/parameters
import com.myexpense.app.service.ExpenseService;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // 1. CREATE (POST)
    // Http Methods: POST
    // Sending Data and RequestBody: Takes Expense object from the request body.
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    // 2. READ ALL (GET)
    // Http Methods: GET
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // 3. READ ONE (GET with PathVariable)
    // Http Methods: GET
    // PathVariable: Extracts the ID from the URL (e.g., /expenses/1).
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)        // If found (200 OK)
                .orElse(ResponseEntity.notFound().build()); // If not found (404 Not Found)
    }

    // 4. UPDATE (PUT)
    // Put and Delete Mapping: Uses @PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseService.getExpenseById(id).map(existingExpense -> {
            // Update the existing expense details
            existingExpense.setDescription(expenseDetails.getDescription());
            existingExpense.setAmount(expenseDetails.getAmount());
            existingExpense.setDate(expenseDetails.getDate()); 
            
            Expense updatedExpense = expenseService.saveExpense(existingExpense);
            return ResponseEntity.ok(updatedExpense);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE (DELETE)
    // Put and Delete Mapping: Uses @DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (expenseService.getExpenseById(id).isPresent()) {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}