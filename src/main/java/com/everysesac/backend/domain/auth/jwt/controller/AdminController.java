package com.everysesac.backend.domain.auth.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


///  jwt 예시입니다.
@Controller
@ResponseBody
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        return "admin Controller";
    }
}
