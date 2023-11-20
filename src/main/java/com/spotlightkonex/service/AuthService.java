package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.*;
import com.spotlightkonex.domain.entity.CompanyMember;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.exception.CustomException;
import com.spotlightkonex.exception.ErrorCode;
import com.spotlightkonex.repository.CompanyMemberRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import com.spotlightkonex.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TokenProvider tokenProvider;
    private final CompanyMemberRepository companyMemberRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final KonexStockRepository konexStockRepository;

    @Transactional
    public Long signUp(final CompanyMemberRequestDto companyMemberRequestDto) {
        KonexStock konexStock = konexStockRepository.findByCorpCode(companyMemberRequestDto.getCorpCode())
                .orElseThrow(() -> new NullPointerException("해당하는 기업 코드가 존재하지 않습니다."));

        CompanyMember companyMember = CompanyMember.builder()
                .email(companyMemberRequestDto.getEmail())
                .password(passwordEncoder.encode(companyMemberRequestDto.getPassword()))
                .phone(companyMemberRequestDto.getPhone())
                .konexStock(konexStock)
                .corpAuth(false)
                .createdAt(LocalDateTime.now())
                .build();

        CompanyMember save = companyMemberRepository.save(companyMember);

        return save.getMemberSeq();
    }

    @Transactional
    public SignInResponseDto signIn(final SignInRequestDto signInRequestDto) {
        CompanyMember companyUser = companyMemberRepository
                .findByEmail(signInRequestDto.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(signInRequestDto.getPassword(), companyUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return SignInResponseDto.builder()
                .email(signInRequestDto.getEmail())
                .accessToken(tokenProvider.createToken(signInRequestDto.getEmail()))
                .build();
    }

    @Transactional
    public SignOutResponseDto signOut(final SignOutRequestDto signOutRequestDto) {
        if (!tokenProvider.validateToken(signOutRequestDto.getAccessToken())) {
            System.out.println("잘못된 요쳥");
        }

        // 해당 Access Token 유효 시간을 가지고 와서 BlackList 로 저장하기
        Long expiration = tokenProvider.getExpiration(signOutRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(signOutRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return SignOutResponseDto.builder()
                .email(signOutRequestDto.getEmail())
                .build();
    }

    @Transactional
    public CompanyMemberResponseDto approveCompanyMember(CompanyMemberRequestDto companyMemberRequestDto) {
        CompanyMember companyMember = companyMemberRepository.findByEmail(companyMemberRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        CompanyMember approvedCompanyMember = CompanyMember.builder().memberSeq(companyMember.getMemberSeq())
                .email(companyMember.getEmail())
                .password(companyMember.getPassword())
                .phone(companyMember.getPhone())
                .konexStock(companyMember.getKonexStock())
                .corpAuth(true)
                .createdAt(companyMember.getCreatedAt())
                .build();

        companyMemberRepository.save(approvedCompanyMember);

        return CompanyMemberResponseDto.builder()
                .message(ErrorCode.COMPANY_MEMBER_APPROVED.getMessage())
                .memberSeq(approvedCompanyMember.getMemberSeq())
                .build();
    }

    public boolean isDuplicatedEmail(String email) {
        return companyMemberRepository.findByEmail(email).isPresent();
    }

    public List<CompanyMemberResponseDto> getCompanyMemberByCorpAuth(boolean authStatus) {
        List<CompanyMember> companyMembers = companyMemberRepository.findByCorpAuth(authStatus).orElseThrow();

        return companyMembers.stream()
                .map(CompanyMemberResponseDto::new)
                .collect(Collectors.toList());
    }
}
