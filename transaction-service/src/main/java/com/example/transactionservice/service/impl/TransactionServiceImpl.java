package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.entities.Transaction;
import com.example.transactionservice.repository.TransactionsRepo;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionsRepo transactionsRepo;
    @Override
    public TransactionDto saveTransaction(Transaction transaction) {
        Transaction saveTransaction=transactionsRepo.save(transaction);
        TransactionDto transactionDto=TransactionDto.builder()
                .orderId(saveTransaction.getOrderId())
                .transactionCode(saveTransaction.getTransactionCode())
                .paymentMethod(saveTransaction.getPaymentMethod())
                .total(saveTransaction.getTotal())
                .build();
        return transactionDto;
    }
}
