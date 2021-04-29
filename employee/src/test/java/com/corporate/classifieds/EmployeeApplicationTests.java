package com.corporate.classifieds;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.corporate.classifieds.entity.Employee;
//import com.corporate.classifieds.repository.EmployeeRepository;
//import com.corporate.classifieds.service.EmployeeService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration 
@SpringBootTest
class EmployeeApplicationTests {
	@Test
    void contextLoads() {
    }
	
//    @InjectMocks
//    private EmployeeService empService;
//    
//    @Autowired
//    private EmployeeRepository empRepository;
    
//    @Autowired
//    private MockMvc mockMvc;
    
//    @Autowired
//    private EmployeeService empService;
//    
//    @MockBean
//    private EmployeeRepository employeeRepository;
    
    
//    @MockBean
//    private EmployeeController employeeController;
    
    
//    @Before
//     public void setUp() throws Exception {
//            mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
//                    .build();
//            System.out.println(mockMvc);
//     }
    
//    @Test
//    public void testEmployeeDetails() throws Exception {
//        
//        Employee employee=new Employee();
//        employee.setEmployeeid(124);
//        employee.setFirstname("Nikita");
//        employee.setLastname("Naik");
//        employee.setEmail("nikitanaik@gmail.com");
//        //employee.setPoints(100);
//        
//        Mockito.when(empService.getProfile(124)).thenReturn(employee);
//        
//        RequestBuilder request=MockMvcRequestBuilders.get("/viewProfile/124")
//                .accept(MediaType.APPLICATION_JSON);
//        
//        mockMvc.perform(request)
//        .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Nikita"));
//        .andExpect(content().json("{\"employeeID\":124,\"firstname\":\"Nikita\",\"lastname\":\"Naik\",\"email\":\"nikitanaik@gmail.com\"}"));                                 
//        //,\"points\":100\"
//    }
    
//    @Test
//    public void testEmployeeDetails2() {
//        
////        Employee employee=new Employee();
////        employee.setEmployeeid(124);
////        employee.setFirstname("Nikita");
////        employee.setLastname("Naik");
////        employee.setEmail("nikitanaik@gmail.com");
////        employee.setPoints(100);
////        empRepository.save(employee);
//        
//        List<Employee> emp= empRepository.findAll();
//        System.out.println(emp);
//        
//        //assertEquals("Natasha",emp.getFirstname());
//    }
    }
