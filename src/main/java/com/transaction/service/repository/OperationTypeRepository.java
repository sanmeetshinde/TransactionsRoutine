package com.transaction.service.repository;

import com.transaction.service.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Interface for OperationType.
 */
public interface OperationTypeRepository extends JpaRepository<OperationType, Integer> {
}
