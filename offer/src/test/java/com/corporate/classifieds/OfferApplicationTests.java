package com.corporate.classifieds;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointsApplicationTests {

	@Test
	void contextLoads() {
	}

}

//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.hamcrest.Matchers;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
////import com.corporate.classifieds.controller.CorporateClassifiedsController;
//import com.corporate.classifieds.controller.OfferController;
////import com.corporate.classifieds.entity.Employee;
//import com.corporate.classifieds.repository.OfferRepository;
//import com.corporate.classifieds.service.OfferService;
//
////@ComponentScan(basePackages= {"com.corporate.classifieds"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class OfferApplicationTests {
//
////    @MockBean
////    private OfferRepository offerRepository;
////    
////    @Autowired
////    private OfferService offerService;
//
////    @MockBean
////    OfferController offerController;
////    
////    @MockBean
////    private OfferService offerService;
////   
//
//	@InjectMocks
//	private OfferController offerController;
//
//	@Autowired
//	protected MockMvc mockMvc;
//
//	@Before
//	public void setUp() throws Exception {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(offerController).build();
//	}
//
//	@Test
//    public void viewOfferTest() throws Exception {
////        when(offerRepository.findAll())
////        .thenReturn(Stream.of(new Offer(1,new Date(),new Date(2020-04-30),null,"TV","Brand tv",10,1,101,0)).collect(Collectors.toList()));
////        assertEquals(1,offerService.viewAllOffers().size());
//        
//    	mockMvc.perform( MockMvcRequestBuilders
//  		      .get("/ViewAllOffers")
//  		      .accept(MediaType.APPLICATION_JSON))
//  		      .andDo(print())
//  		      .andExpect(status().isOk())
//  		      .andExpect(content().contentType("application/json"))
////    	       .andExpect(MockMvcResultMatchers.jsonPath("$.offerId").value(1))
////	           .andExpect(MockMvcResultMatchers.jsonPath("$.viewEmployeeOffers[*].id").isNotEmpty())
//	            .andExpect(jsonPath("$.*", Matchers.hasSize(1)));
//    	
////    	this.mockMvc.perform(MockMvcRequestBuilders.get("/viewAllOffers")
////				.accept(MediaType.APPLICATION_JSON))
////    	.andDo(print())
////				.andExpect(MockMvcResultMatchers.status().isOk())
////				.andExpect(content().contentType("application/json"))
////				.andExpect(jsonPath("$.*",Matchers.hasSize(1)));
//    }
//}
//
////@Before
//////public void setUp() throws Exception {
//////     mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
//////             .build();
//////     System.out.println(mockMvc);
//////}
////
////@Test
////public void testEmployeeDetails() throws Exception {
//// 
//// Employee employee=new Employee();
//// employee.setEmployeeid(124);
//// employee.setFirstname("Nikita");
//// employee.setLastname("Naik");
//// employee.setEmail("nikitanaik@gmail.com");
//// //employee.setPoints(100);
//// 
//// Mockito.when(empService.getProfile(124)).thenReturn(employee);
//// 
//// RequestBuilder request=MockMvcRequestBuilders.get("/viewProfile/124")
////         .accept(MediaType.APPLICATION_JSON);
//// 
//// mockMvc.perform(request)
//// .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Nikita"));
//// .andExpect(content().json("{\"employeeID\":124,\"firstname\":\"Nikita\",\"lastname\":\"Naik\",\"email\":\"nikitanaik@gmail.com\"}"));                                 
//// //,\"points\":100\"
////}
//
////    @Test
////    public void viewOfferByDate() {
////        when(offerRepository.findByOfferOpenDate(new Date(2020-04-30))).thenReturn(Stream.of(new Offer(1,new Date(),new Date(2020-04-30),null,"TV","Brand tv",10,1,101,0)).collect(Collectors.toList()));
////        assertEquals(1,offerService.getDetailsByDate(new Date(2020-04-30)).size());    
////    }
////    
////    @Test
////    public void viewOffersByCategory() {
////        when(offerRepository.findByCategoryId(5)).thenReturn(Stream.of(new Offer(1,new Date(),new Date(2020-04-30),null,"TV","Brand tv",10,1,101,0)).collect(Collectors.toList()));
////        assertEquals(1,offerService.getDetailsByCategory(5).size());
////    }
//
////	 @Test
//// public void testHelloWorldJson() throws Exception {​​​
////	 mockMvc.perform( MockMvcRequestBuilders
////		      .get("/ViewAllOffers")
////		      .accept(MediaType.APPLICATION_JSON))
////		      .andDo(print())
////		      .andExpect(status().isOk())
////		      .andExpect(content().contentType("application/json"))
////		      .andExpect(jsonPath("$.offerTitle", Matchers.is("TV")));
////		      //.andExpect(MockMvcResultMatchers.jsonPath("$[0].offerId").value(1));
////		      //.andExpect(MockMvcResultMatchers.jsonPath("$.viewEmployeeOffers[*].id").isNotEmpty());
////		      //.andExpect(jsonPath("$.*", Matchers.hasSize(1)));
////	 System.out.println(mockMvc);
////    }​​​
////
