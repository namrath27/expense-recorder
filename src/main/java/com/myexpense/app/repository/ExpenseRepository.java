package com.myexpense.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myexpense.app.model.Expense; // Import your Expense entity

// The core step: extending JpaRepository<EntityClass, PrimaryKeyType>
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	

    // You don't need to write any methods here for basic CRUD operations!
    // By extending JpaRepository, you automatically get:
    // .save()         (Create/Update)
    // .findAll()      (Read All)
    // .findById()     (Read One)
    // .deleteById()   (Delete)
    
}

