package com.wu.dao.play;

import com.wu.dto.CustomerDto;

public interface CustomerDao {
	
	/**
	 *  @param customer including phoneNo, name, email, ... 
	 *  @return boolean true if customer data are saved successfully
	 *  
	 * */
	boolean saveCustomer(CustomerDto customer);
	
}
