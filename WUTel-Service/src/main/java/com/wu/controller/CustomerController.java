package com.wu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.dto.CustomerDto;
import com.wu.service.play.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/insert"  ,consumes = "application/json"  )
	public boolean saveCustomer(@RequestBody CustomerDto customerDto) {

		return customerService.saveCustomer(customerDto);

	}

}
