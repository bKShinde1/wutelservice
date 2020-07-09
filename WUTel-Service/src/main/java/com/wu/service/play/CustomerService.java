package com.wu.service.play;

import com.wu.dto.CustomerDto;

public interface CustomerService {

	/**
	 *  @param customer including phoneNo, name, email, ... 
	 *  @return boolean true if customer data update successfully
	 *  
	 * */
	
	boolean saveCustomer(CustomerDto customer);
			 
}
