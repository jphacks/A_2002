package jphacks_a2002.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

	@PostMapping("/api")
    public List<String> json(@RequestBody String body) {
        return Arrays.asList(body);
    }
}
