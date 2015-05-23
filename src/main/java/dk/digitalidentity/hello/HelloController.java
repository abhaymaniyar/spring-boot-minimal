package dk.digitalidentity.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController combines @Controller w/ @ResponseBody, so that
// the web requests return data instead of views
@RestController
public class HelloController {

	@RequestMapping
	public String hello(){
		return "Hello !";
	}
}
