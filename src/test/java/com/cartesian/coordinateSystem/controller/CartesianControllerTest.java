package com.cartesian.coordinateSystem.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Test cases for the Cartesian Controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CartesianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Logger logger = LoggerFactory.getLogger(CartesianControllerTest.class);

    @Test
    public void testIsLineUsingTwoPointsTrue() throws Exception {
        logger.debug("test IsLineUsingTwoPoints() method");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-by-two-points/3/2/2/6"))
                .andExpect(status().isOk());
        assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testIsLineUsingTwoPointsFalse() throws Exception {
        logger.error("test IsLineUsingTwoPoints() method");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-by-two-points/3/3/3/3"))
                .andExpect(status().isOk());
        assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testFindGradientAndYIntercept() throws Exception {
        logger.debug("test testFindGradientAndYIntercept() method");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/y-intercept/5/2/2/7"))
                .andExpect(status().isOk());
        assertEquals(result.andReturn().getResponse().getContentAsString(), "10");
    }


    @Test
    public void testIsParallelTrue() throws Exception {
        logger.debug("test IsParallel() method");
        String input = "{ \"line1\": { \"start\": { \"x\": \"2\", \"y\": \"2\" }, \"end\": { \"x\": \"2\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"3\", \"y\": \"3\" }, \"end\": { \"x\": \"3\", \"y\": \"3\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/parallel").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testIsParallelFalse() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"-8\", \"y\": \"-8\" }, \"end\": { \"x\": \"-2\", \"y\": \"-8\" } }, \"line2\": { \"start\": { \"x\": \"5\", \"y\": \"7\" }, \"end\": { \"x\": \"3\", \"y\": \"3\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/parallel").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testIsPerpendicularTrue() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"1\", \"y\": \"2\" }, \"end\": { \"x\": \"1\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"0\", \"y\": \"1\" }, \"end\": { \"x\": \"8\", \"y\": \"1\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/perpendicular").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testIsPerpendicularFalse() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"8\", \"y\": \"0\" }, \"end\": { \"x\": \"11\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"0\", \"y\": \"1\" }, \"end\": { \"x\": \"8\", \"y\": \"1\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/perpendicular").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testLineIncidencePoint() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"1\", \"y\": \"1\" }, \"end\": { \"x\": \"4\", \"y\": \"4\" } }, \"line2\": { \"start\": { \"x\": \"1\", \"y\": \"8\" }, \"end\": { \"x\": \"5\", \"y\": \"0\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-incident-point").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        assertEquals(result.andReturn().getResponse().getStatus(), 200);
    }
}


