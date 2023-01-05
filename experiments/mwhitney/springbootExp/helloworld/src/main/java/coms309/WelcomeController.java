package coms309;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello World";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello from " + name;
    }
    
    
    @GetMapping("/{name}/{number}")
    public String welcome(@PathVariable String name, @PathVariable int number) {
        return "Hello From " + name + " my favorite number is " + number;
    }

    
    
    @GetMapping("/List")
    public List<String> getList(){
    	List<String> list = Arrays.asList("Com s 309", "FrontEnd", "backend");
    	return list ;
    }
}
