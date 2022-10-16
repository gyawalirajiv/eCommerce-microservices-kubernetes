package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionDTO;
import com.example.transactionservice.entities.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    public TransactionDTO saveTransaction(Transaction transaction);
}
