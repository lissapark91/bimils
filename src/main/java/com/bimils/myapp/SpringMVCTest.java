package com.bimils.myapp;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //웹 컨택스트 타입의 컨테이너를 생성
@ContextConfiguration(locations= {"file:src/main/resources/root-context.xml", "file:src/main/resources/servlet-context.xml"})
public class SpringMVCTest {

	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}

	@Test
	public void test() throws Exception {
		
		//resultActions return
/*		mockMvc.perform(MockMvcRequestBuilders.get("/member/members"))
		.andDo(print()) //root부터 test
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("memberList"));*/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("mem_id", "xxx1234");
		params.add("mem_name", "테스트");
		params.add("mem_pwd", "1234");
		params.add("mem_email", "master@gmail.com");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/member/form")
				.params(params)
				).andDo(print());
		
	}

}
