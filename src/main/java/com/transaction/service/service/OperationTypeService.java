package com.transaction.service.service;

import com.transaction.service.model.OperationType;
import com.transaction.service.model.Transaction;
import com.transaction.service.repository.OperationTypeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

import static com.transaction.service.constants.Constants.*;

/**
 * Service for managing Operation Types.
 */
@Service
public class OperationTypeService  implements InitializingBean {

    @Autowired
    OperationTypeRepository operationTypeRepository;

    /**
     * Returns the Operation Type for the corresponding ID.
     * @param operationTypeId
     * @return Optional<OperationType>
     */
    public Optional<OperationType> getOperationTypeById(int operationTypeId) {
        return operationTypeRepository.findById(operationTypeId);
    }

    /**
     * Overrides the afterPropertiesSet() method in InitializingBean.
     * Runs once after the Bean is initialized at application start up.
     * Implemented because an in-memory volatile store is used.
     * If persistent store is configured, this method should be removed.
     */
    @Override
    public void afterPropertiesSet() {
        OperationType operationType1 = new OperationType(1, NORMAL_PURCHASE_DESCRIPTION, true, new HashSet<Transaction>());
        OperationType operationType2 = new OperationType(2, INSTALLMENT_PURCHASE_DESCRIPTION, true, new HashSet<Transaction>());
        OperationType operationType3 = new OperationType(3, WITHDRAWAL_DESCRIPTION, true, new HashSet<Transaction>());
        OperationType operationType4 = new OperationType(4, CREDIT_VOUCHER_DESCRIPTION, false, new HashSet<Transaction>());
        operationTypeRepository.save(operationType1);
        operationTypeRepository.save(operationType2);
        operationTypeRepository.save(operationType3);
        operationTypeRepository.save(operationType4);
    }

}
