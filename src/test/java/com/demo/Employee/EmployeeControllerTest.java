package com.demo.Employee;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.internal.MethodSorter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.employee.controller.EmployeeController;
import com.demo.employee.domain.Employee;
import com.demo.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private EmployeeService employeeService;
	
	protected void setUp() {
		
	}
	@Test
	public void getAllEmployeesTest() throws Exception {
		Mockito.when(
				employeeService.getAllEmployes()).thenReturn(null);
		
		RequestBuilder requestBuilder =MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		assertEquals(result.getResponse().getStatus(),200);
	}
	@Test
	public void registerEmployeeTest() throws Exception {
		
		Employee mockEmployee=new Employee();
		mockEmployee.setId("1");
		mockEmployee.setFirstName("A");
		mockEmployee.setLastName("B");
		mockEmployee.setGender("Male");
//		mockEmployee.setDob(LocalDate.of(1999,10,10));
		mockEmployee.setDob("1999,10,10");
		mockEmployee.setDepartment("tech");
		
		String employeeJson = "{\"id\":\"1\",\"firstName\":\"A\",\"lastName\":\"B\",\"gender\":\"male\",\"dob\":\"1999-10-10\",\"department\":\"tech\"}";

		Mockito.when(
				employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").accept(MediaType.APPLICATION_JSON)
										.content(employeeJson)
										.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String content = result.getResponse().getContentAsString();
		Employee emp = new ObjectMapper().readValue(content, Employee.class);
		assertEquals("tech",emp.getDepartment());
		assertEquals(200, response.getStatus());

	}
	
}
		