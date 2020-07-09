package com.wu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.wu.dao.play.CustomerDao;
import com.wu.dto.CustomerDto;
import com.wu.exception.RequestViolationException;

@EnableTransactionManagement
@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);

	private static final String COMMON_SAVE_CUSTOMER_IN_DB = "call saveCustomer(?,?,?,?,?,?,?)";
    
    private static final String CUSTOMER_MAPPING = "CustomerMapping";
    
	@Autowired
	@PersistenceContext(unitName = "msSqlServerUnit")
	private EntityManager entityManager;

	@Transactional(value = "msSqlServerTransactionManager")
	@Override
	public boolean saveCustomer(CustomerDto customerDto) {

		Session session = entityManager.unwrap(Session.class);
		session.doWork(connection -> connection.setAutoCommit(true));

		Query query = entityManager.createNativeQuery(COMMON_SAVE_CUSTOMER_IN_DB);

		try {

			query.setParameter(1, customerDto.getPhoneNo());
			query.setParameter(2, customerDto.getName());
			query.setParameter(3, customerDto.getEmail());
			query.setParameter(4, customerDto.getAge());
			query.setParameter(5, customerDto.getGender());
			query.setParameter(6, customerDto.getPassword());
			query.setParameter(7, customerDto.getAddress());

			final int executeQueryResult = query.executeUpdate();
			LOG.info("Save result for customerDto : {}", customerDto + "is : {}" + executeQueryResult);
			return executeQueryResult == 0;

		} catch (PersistenceException pe) {

			LOG.error("Failed to update the customer for customerDto : {}", customerDto, pe);

			throw new RequestViolationException("Failed to update customer", pe.getCause());
		}

	}
	
}
