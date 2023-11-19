package com.spotlightkonex.security;

import com.spotlightkonex.domain.entity.CompanyMember;
import com.spotlightkonex.repository.CompanyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMemberDetailsService implements UserDetailsService {
    private final CompanyMemberRepository companyMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CompanyMember companyUser = companyMemberRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보 를 찾지 못했습니다."));
        return CompanyMemberDetails.of(companyUser);
    }
}
