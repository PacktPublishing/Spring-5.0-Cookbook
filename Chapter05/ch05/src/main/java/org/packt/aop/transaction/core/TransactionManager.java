package org.packt.aop.transaction.core;


import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

@Component
public class TransactionManager implements PlatformTransactionManager {
	@Override
    public void commit(TransactionStatus status) throws TransactionException { }
 
    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition)
                        throws TransactionException {
          return new SimpleTransactionStatus();
    }
 
    @Override
    public void rollback(TransactionStatus status) throws TransactionException { }
}
