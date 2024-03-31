package webCalendarSpring.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

//    @GetMapping("/")
    @RequestMapping("/")
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}
