package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.*;
import com.spotlightkonex.exception.ErrorCode;
import com.spotlightkonex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private String phoneRegex = "\\b\\d{2,3}-\\d{3,4}-\\d{4}\\b\n";

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CompanyMemberResponseDto> signUp(@RequestBody CompanyMemberRequestDto companyMemberRequestDto) {
        if (companyMemberRequestDto.getEmail() == null || companyMemberRequestDto.getPassword() == null) {
            ErrorCode errorCode = ErrorCode.INVALID_INPUT;
            return makeStatusResponse(errorCode);
        }
        if (!companyMemberRequestDto.getEmail().matches(emailRegex)) {
            ErrorCode errorCode = ErrorCode.WRONG_EMAIL_FORMAT;
            return makeStatusResponse(errorCode);
        }
        if (authService.isDuplicatedEmail(companyMemberRequestDto.getEmail())) {
            ErrorCode errorCode = ErrorCode.DUPLICATED_EMAIL;
            return makeStatusResponse(errorCode);
        }
        if (!companyMemberRequestDto.getPhone().matches(phoneRegex)) {
            ErrorCode errorCode = ErrorCode.WRONG_PHONE_FORMAT;
            return makeStatusResponse(errorCode);
        }
        Long corp_seq = authService.signUp(companyMemberRequestDto);
        ErrorCode code = ErrorCode.SIGNUP_SUCCESS;
        CompanyMemberResponseDto responseDto = CompanyMemberResponseDto
                .builder()
                .message(code.getMessage())
                .memberSeq(corp_seq)
                .build();
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        if (!signInRequestDto.getEmail().matches(emailRegex)) {
            ErrorCode errorCode = ErrorCode.WRONG_EMAIL_FORMAT;
            SignInResponseDto responseDto = SignInResponseDto.builder()
                    .email(signInRequestDto.getEmail())
                    .accessToken("")
                    .message(errorCode.getMessage())
                    .build();
            return ResponseEntity.status(errorCode.getStatus()).body(responseDto);
        }
        return ResponseEntity.ok().body(authService.signIn(signInRequestDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<SignOutResponseDto> signOut(@RequestBody SignOutRequestDto signOutRequestDto) {
        if (!signOutRequestDto.getEmail().matches(emailRegex)) {
            ErrorCode errorCode = ErrorCode.WRONG_EMAIL_FORMAT;
            SignOutResponseDto responseDto = SignOutResponseDto.builder()
                    .email(signOutRequestDto.getEmail())
                    .message(errorCode.getMessage())
                    .build();
            return ResponseEntity.status(errorCode.getStatus()).body(responseDto);
        }
        return ResponseEntity.ok().body(authService.signOut(signOutRequestDto));
    }

    @PostMapping("/corp-auth")
    public ResponseEntity<?> approveCompanyMember(@RequestBody CompanyMemberRequestDto companyMemberRequestDto) {
        return ResponseEntity.ok().body(authService.approveCompanyMember(companyMemberRequestDto));
    }

    @GetMapping("/corp-auth")
    public ResponseEntity<List<CompanyMemberResponseDto>> getCompanyMemberByCorpAuth(@RequestParam boolean corpAuth) {
        return ResponseEntity.ok().body(authService.getCompanyMemberByCorpAuth(corpAuth));
    }

    private ResponseEntity<CompanyMemberResponseDto> makeStatusResponse(ErrorCode code) {
        CompanyMemberResponseDto responseDto = CompanyMemberResponseDto
                .builder()
                .message(code.getMessage())
                .memberSeq(-1L)
                .build();
        return ResponseEntity.status(code.getStatus()).body(responseDto);
    }
}
