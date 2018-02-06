//package com.services.micro.template.demo.resource;
//
//import org.hamcrest.Matchers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.ftd.acl.api.response.ServiceResponse;
//import com.ftd.acl.bl.MyService;
//import com.ftd.acl.resource.ServiceResource;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//@RunWith(SpringRunner.class)
//public class ServiceResourceTest {
//    private MockMvc mockMvc;
//
//
//    @Mock
//    private MyService myService;
//
//    @InjectMocks
//    private ServiceResource serviceResource;
//
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(serviceResource)
//                .build();
//    }
//
//
//    @Test
//    public void testString() throws Exception {
//
//        ServiceResponse serviceResponse = new ServiceResponse();
//        serviceResponse.setActionType("hello test");
//        when(myService.getResponse("test")).thenReturn(serviceResponse);
//
//        mockMvc.perform(get("/test/plain"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello test"));
//
//        verify(myService).getResponse("test");
//    }
//
//
//    @Test
//    public void testJson() throws Exception {
//
//        ServiceResponse serviceResponse = new ServiceResponse();
//        serviceResponse.setActionType("hello test");
////        serviceResponse.setType("MyType");
//        when(myService.getResponse("testkey")).thenReturn(serviceResponse);
//
//
//        mockMvc.perform(get("/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message", Matchers.is("hello test")))
//                .andExpect(jsonPath("$.type", Matchers.is("MyType")))
//                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
//    }
//
//
//    @Test
//    public void testPost() throws Exception {
//        String json = "{\n"
//                 + "  \"input\": \"MyInput\"\n"
//                 + "}";
//        mockMvc.perform(post("/test/post")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message", Matchers.is("Hello MyInput")))
//                .andExpect(jsonPath("$.type", Matchers.is("post")))
//                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
//    }
//}