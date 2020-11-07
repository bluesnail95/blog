package gdut.ff.controller;

import gdut.ff.security.WebResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author bluesnail95
 * @Date 2020/10/6 18:49
 * @Description
 */
@RestController
@RequestMapping("/security")
public class SecurityController {


    @GetMapping("/user")
    public WebResponse user(@AuthenticationPrincipal Principal principal){
        return WebResponse.success(principal.getName());
    }

}
