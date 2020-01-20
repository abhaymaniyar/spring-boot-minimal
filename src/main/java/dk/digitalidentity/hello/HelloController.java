package dk.digitalidentity.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/hello", produces = "application/json")
	public ResponseEntity<String> checkHealth() {
		String currentVersion = "1.0.0";
		return new ResponseEntity<>(currentVersion, HttpStatus.OK);
	}
}
