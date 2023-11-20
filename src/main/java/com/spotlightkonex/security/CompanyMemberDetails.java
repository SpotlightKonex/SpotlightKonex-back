package com.spotlightkonex.security;

import com.spotlightkonex.domain.entity.CompanyMember;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
public class CompanyMemberDetails implements UserDetails {
    private Long memberSeq;
    private String email; // 관리자 이메일
    private String password; // 패스워드
    private String corpName; // 기업 명
    private String phone; // 관리자 연락처
    private String corpCode; // 기업 고유번호

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CompanyMemberDetails of(CompanyMember companyUser) {
        return CompanyMemberDetails.builder()
                .memberSeq(companyUser.getMemberSeq())
                .email(companyUser.getEmail())
                .password(companyUser.getPassword())
                .corpName(companyUser.getCorpName())
                .phone(companyUser.getPhone())
                .corpCode(companyUser.getKonexStock().getCorpCode())
                .build();
    }
}
