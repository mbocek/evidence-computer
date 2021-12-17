package org.evidence;

import org.testcontainers.containers.PostgreSQLContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPostgresqlContainer extends PostgreSQLContainer<TestPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:14-alpine";
    private static TestPostgresqlContainer container;

    private TestPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static TestPostgresqlContainer getInstance() {
        log.info("Create test instance");
        if (container == null) {
            container = new TestPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
