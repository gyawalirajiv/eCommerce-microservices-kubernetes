package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransactionDTO;
import com.example.transactionservice.entities.Transaction;
import com.example.transactionservice.repository.TransactionsRepo;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionsRepo transactionsRepo;

    @Override
    public TransactionDTO saveTransaction(Transaction transaction) {
        Transaction saveTransaction = transactionsRepo.save(transaction);
        TransactionDTO transactionDto = new TransactionDTO();

        transactionDto.setOrderId(saveTransaction.getOrderId());
        transactionDto.setTransactionCode(saveTransaction.getTransactionCode());
        transactionDto.setPaymentMethod(saveTransaction.getPaymentMethod());
        transactionDto.setTotal(saveTransaction.getTotal());

        return transactionDto;
    }
}
