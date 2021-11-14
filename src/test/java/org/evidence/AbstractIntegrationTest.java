package org.evidence;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ComputerApplication.class, H2TestProfileJPAConfig.class},
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {
}
