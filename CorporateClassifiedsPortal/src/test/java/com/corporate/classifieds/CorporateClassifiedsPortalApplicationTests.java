package com.corporate.classifieds;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeApplicationTests {
	@Test
    void contextLoads() {
    }
}


//package com.corporate.classifieds;
//
//import static org.junit.Assert.assertEquals;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.mockito.MockitoAnnotations;
//
//import com.corporate.classifieds.controller.CorporateClassifiedsController;
//
////@WebMvcTest(CorporateClassifiedsController.class)
////@WithMockUser(password="password",username="Nikhil")
////@WithAnonymousUser
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringBootTest
//@AutoConfigureMockMvc
//class CorporateClassifiedsPortalApplicationTests {
//
//	
//	
//	@InjectMocks
//	private CorporateClassifiedsController corporateClassifiedsController;
//	
////	@Autowired
////	private WebApplicationContext context;
//	
//	@Autowired
//	protected MockMvc mockMvc;
//	
//	@Before
//	public void setUp() throws Exception{
//		//MockitoAnnotations.initMocks(this);
//		
//		this.mockMvc= MockMvcBuilders.standaloneSetup(corporateClassifiedsController).build();
//			//this.mockMvc=MockMvcBuilders.webAppContextSetup(this.context).build();
//	}
//	
//	@Test
//	public void helloworldTest() throws Exception {
//		RequestBuilder request=MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON);
//		
//		MvcResult result= mockMvc.perform(request).andReturn();
//		
//		assertEquals("Hi",result.getResponse().getContentAsString());
//	}
//	
//	@Test
//	@WithMockUser(username="Nikhil", password="password")
//	public void testGetAllOffers() throws Exception{  
//		
//	this.mockMvc.perform(MockMvcRequestBuilders.get("/viewAllOffers")
//				).andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(jsonPath("$.*",Matchers.hasSize(1)));
//
//		mockMvc.perform(get("/viewAllOffers").with(user("Nikhil")))
//		   .andExpect(status().isOk())
//		   .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	
//	}
//	
//}
