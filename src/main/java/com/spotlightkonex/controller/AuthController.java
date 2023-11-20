package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.*;
import com.spotlightkonex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CompanyMemberDto companyMemberDto) {
        if (companyMemberDto.getEmail() == null || companyMemberDto.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L);
        }
        if (!companyMemberDto.getEmail().matches(emailRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L);
        }
        if (authService.isDuplicatedEmail(companyMemberDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L);
        }
        Long corp_seq = authService.signUp(companyMemberDto);
        return ResponseEntity.ok().body(corp_seq);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        if (!signInRequestDto.getEmail().matches(emailRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SignInResponseDto());
        }
        return ResponseEntity.ok().body(authService.signIn(signInRequestDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<SignOutResponseDto> signOut(@RequestBody SignOutRequestDto signOutRequestDto) {
        if (!signOutRequestDto.getEmail().matches(emailRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SignOutResponseDto());
        }
        return ResponseEntity.ok().body(authService.signOut(signOutRequestDto));
    }

}
