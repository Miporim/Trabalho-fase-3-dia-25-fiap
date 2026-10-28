package com.example.smartcollector.testes.config;

public final class TestConfig {
    private TestConfig() {}

    public static String baseUrl() {
        return System.getProperty(
                "api.base.url",
                System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080")
        );
    }

    public static String testPassword() {
        return System.getenv().getOrDefault("BDD_TEST_PASSWORD", "SenhaTeste123");
    }
}
