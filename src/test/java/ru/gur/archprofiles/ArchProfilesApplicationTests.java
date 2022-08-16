package ru.gur.archprofiles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class ArchProfilesApplicationTests {

	@Test
	void contextLoads() {
	}
}
