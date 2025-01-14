package com.cosmocats;

import com.cosmocats.service.SpaceExchangeRateResponse;
import com.cosmocats.service.SpaceExchangeRateService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExternalApiIT {

    private static WireMockServer wireMockServer;

    @Autowired
    private SpaceExchangeRateService spaceExchangeRateService;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(9999);
        wireMockServer.start();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setUp() {
        wireMockServer.resetAll();
    }

    @Test
    void testGetExchangeRate() {
        wireMockServer.stubFor(
                get(urlEqualTo("/space-exchange-rate"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"rate\": 42.5 }"))
        );

        SpaceExchangeRateResponse response = spaceExchangeRateService.getExchangeRate();
        assertNotNull(response, "Response should not be null");
        assertEquals(42.5, response.getRate(), 0.0001, "Rate should be 42.5 from WireMock stub");
    }
}











