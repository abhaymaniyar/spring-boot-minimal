package dk.digitalidentity.hello;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import org.jooq.DSLContext;

@RestController
public class HelloController {

	private final DSLContext dsl;

	private final Bucket bucket;

	public HelloController(DSLContext dsl) {
		this.dsl = dsl;

		long capacity = 10;
		Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
		Bandwidth limit = Bandwidth.classic(capacity, refill);
		this.bucket = Bucket4j.builder().addLimit(limit).build();

		// OR
		// Bandwidth limit = Bandwidth.simple(10, Duration.ofMinutes(1));
	}

	@GetMapping(value = "/hello", produces = "application/json")
	public ResponseEntity<String> checkHealth() {
		String currentVersion = "1.0.0";
		return new ResponseEntity<>(currentVersion, HttpStatus.OK);
	}
}
