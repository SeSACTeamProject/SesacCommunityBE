package com.everysesac.backend.domain.auth.jwt.controller;

import com.everysesac.backend.domain.auth.jwt.dto.JoinDTO;
import com.everysesac.backend.domain.auth.jwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;


    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return "ok";
    }





}
