package com.wu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.dao.play.CustomerDao;
import com.wu.dto.CustomerDto;
import com.wu.service.play.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public boolean saveCustomer(CustomerDto customerDto) {

		return customerDao.saveCustomer(customerDto);

	}

}
