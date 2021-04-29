//package com.corporate.classifieds;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
////import org.hamcrest.Matchers;
////import org.junit.Before;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.mockito.InjectMocks;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
////import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
////import org.springframework.test.web.servlet.setup.MockMvcBuilders;
////
////import com.corporate.classifieds.controller.CorporateClassifiedsController;
//
//@SpringBootTest
////@RunWith(SpringJUnit4ClassRunner.class)
//public class CorporateClassifiedsPortalControllerTest {
//	
////	@Autowired
////	private MockMvc mockMvc;
////	
////	@InjectMocks
////	private CorporateClassifiedsController corporateClassifiedsController;
////	
////	@Before
////	public void setUp() throws Exception{
////		mockMvc= MockMvcBuilders.standaloneSetup(corporateClassifiedsController).build();
////				
////	}
////	
////	@Test
////	public void testGetAllOffers() throws Exception{
////		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllOffers")
////				).andExpect(MockMvcResultMatchers.status().isOk())
////				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
////	}
////	
//}
