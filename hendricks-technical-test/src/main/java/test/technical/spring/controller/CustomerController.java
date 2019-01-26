package test.technical.spring.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import test.technical.spring.model.Customer;
import test.technical.spring.model.CustomerNotFoundException;
import test.technical.spring.repositories.CustomerRepository;

@Controller
public class CustomerController {

	
	private final CustomerRepository customerRepository;
	
	@Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	//use to print all customers 
	//@GetMapping("/viewcust")
	@RequestMapping("/viewcust")  
    public String viewemp(Model m){   
        m.addAttribute("customer",customerRepository.findAll());
        return "showAll";  
    }  

	//Shows the customer form from template 
	@RequestMapping("/customerform")
    public String showCustomerForm(Customer c) {
        return "custform";
    }

	//customer form submit post mapping
	@PostMapping("/add-cust")
	public String createCustomer(@Valid Customer customer, BindingResult result, Model m) {
		
		if (result.hasErrors()) {
            return "add-cust";
        }
		
		customerRepository.save(customer);
		m.addAttribute("customer", customerRepository.findAll());
		return "showAll";

	}
	
	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Customer cust = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cust Id:" + id));
        model.addAttribute("customer", cust);
        return "update-user";
    }
	
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    	Customer user = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cust Id:" + id));
    	customerRepository.delete(user);
        model.addAttribute("customer", customerRepository.findAll());
        return "index";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Customer cust, BindingResult result, Model model) {
        
        //if customer ID is not found in database
    	Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()){
				throw new CustomerNotFoundException("id-" + id);
			}
		
		//if form contains error
		if (result.hasErrors()) {
	           cust.setId(id);
	           return "update-user";
	    }
		
		//save the changes from the form, and redirect back to the show all customers page
        customerRepository.save(cust);
        model.addAttribute("customer", customerRepository.findAll());
        return "showAll";
    }

	

//	//retrieve a single customer by ID 
//	@GetMapping("/customers/{id}")
//	public Customer retrieveCustomer(@PathVariable long id) {
//		Optional<Customer> customer = customerRepository.findById(id);
//
//		if (!customer.isPresent())
//			throw new CustomerNotFoundException("id-" + id);
//
//		return customer.get();
//	}

	//delete customer by ID
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerRepository.deleteById(id);
	}
	
	//update customer by post form
	@PutMapping("/customers/{id}")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable long id, String name, String gender, String email) {

		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (!customerOptional.isPresent())
			return ResponseEntity.notFound().build();

		customer.setId(id);
		
		customerRepository.save(customer);

		return ResponseEntity.noContent().build();
	}
}
