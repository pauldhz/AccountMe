package org.pauldenhez.accountme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AccountMeApplication.class)
class AccountMeApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(1 == 1);
	}
}
