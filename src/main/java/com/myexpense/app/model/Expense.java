package com.myexpense.app.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key: Unique identifier for the expense

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	private String description; // e.g., "Groceries", "Rent"

    private double amount; // The cost of the expense

    private LocalDate date; // When the expense occurred

    // ----------------------------------------------------
    // You should also include:
    // 1. A default (no-argument) constructor
    // 2. Getters and Setters for all fields (or use Lombok)
    // ----------------------------------------------------

    // Default Constructor (required by JPA)
    public Expense() {}

    // Getters and Setters (Example for 'id')
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // ... rest of the getters/setters for description, amount, date
}

