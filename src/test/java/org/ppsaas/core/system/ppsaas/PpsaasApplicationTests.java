package org.ppsaas.core.system.ppsaas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // This ensures that the 'test' profile is used, where we configure embedded MongoDB
public class PpsaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFileUpload() throws Exception {
    	ClassPathResource resource = new ClassPathResource("sample.csv");
        byte[] fileContent = resource.getInputStream().readAllBytes();

        MockMultipartFile mockFile = new MockMultipartFile(
            "file",                          // The name of the form field
            "sample.csv",                    // Original filename
            "text/csv",                      // Content type
            fileContent                      // Content of the file
        );

        mockMvc.perform(multipart("/api/upload")
                .file(mockFile)
                .param("orgId", "TeraBt")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded successfully"));
        
        
        // Perform the GET request and capture the result
        ResultActions resultActions = mockMvc.perform(get("/api/reports/total-employees")
                .param("orgId", "TeraBT")
                .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
     // Extract the response content as a String
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        
        // Print the response content (optional)
        System.out.println("Response Content: " + responseContent);
    }
    
	/*
	 * @Test public void testGetTotalEmployees() throws Exception { // Perform the
	 * GET request and capture the result ResultActions resultActions =
	 * mockMvc.perform(get("/api/reports/total-employees") .param("orgId", "TeraBT")
	 * .header("Content-Type", "application/json"))
	 * .andExpect(MockMvcResultMatchers.status().isOk());
	 * 
	 * // Extract the response content as a String String responseContent =
	 * resultActions.andReturn().getResponse().getContentAsString();
	 * 
	 * // Print the response content (optional)
	 * System.out.println("Response Content: " + responseContent);
	 * 
	 * // Define the expected value String expectedValue = "3";
	 * 
	 * // Compare the response content with the expected value
	 * assertEquals(expectedValue, responseContent,
	 * "The response content does not match the expected value."); }
	 */
}


