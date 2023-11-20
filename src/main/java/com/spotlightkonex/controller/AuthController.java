package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.CompanyMemberDto;
import com.spotlightkonex.domain.dto.SignInRequestDto;
import com.spotlightkonex.domain.dto.SignOutRequestDto;
import com.spotlightkonex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CompanyMemberDto companyMemberDto) {
        Long corp_seq = authService.signUp(companyMemberDto);
        return ResponseEntity.ok().body(corp_seq);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok().body(authService.signIn(signInRequestDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody SignOutRequestDto signOutRequestDto) {
        return ResponseEntity.ok().body(authService.signOut(signOutRequestDto));
    }

}
