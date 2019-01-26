package test.technical.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/******************************************************
 * **** IMPORTANT! READ THIS FIRST********
 *
 * Business Requirements :
 *  1. I would need API Services to link up with my Web interfaces. I would need the ability to
 *      A. Create a new Customer
 *      B. Update a Customer
 *      C. Delete a Customer
 *      D. Show me all the Customers
 *
 *  2. Persist the Customer Instance in a Database, of your choice (Bonus Marks)
 *
 *  If you have any questions, feel free to email us @ fyan@hendrickscorp.com or pchan@hendrickscorp.com .
 *
 *  **Note : Questions will not be penalized!
 */
@SpringBootApplication
public class mainFunction {

	public static void main(String[] args) {
		SpringApplication.run(mainFunction.class, args);
	}
}
