package com.spotlightkonex.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력값 입니다."),

    WRONG_PHONE_FORMAT(HttpStatus.BAD_REQUEST, "잘못된 휴대폰 형식 입니다."),

    WRONG_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "잘못된 이메일 형식 입니다."),

    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),

    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 입니다."),

    /*
     * 403 Forbidden: 승인을 거부함
     */
    UNAUTHORIZED_REQUEST(HttpStatus.FORBIDDEN, "허용되지 않은 요청입니다."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버 정보를 찾을 수 없습니다."),

    NEWS_NOT_FOUND(HttpStatus.NOT_FOUND, "뉴스를 찾을 수 없습니다."),

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일을 찾을 수 없습니다."),

    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "회사를 찾을 수 없습니다."),


    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),

    /*
     * 200 OK
     */
    SIGNUP_SUCCESS(HttpStatus.OK, "회원가입 성공."),

    COMPANY_MEMBER_APPROVED(HttpStatus.OK, "기업 회원 승인 완료."),

    SIGN_IN_SUCCESS(HttpStatus.OK, "로그인 성공."),

    SIGN_OUT_SUCCESS(HttpStatus.OK, "로그아웃 성공.")
    ;

    private final HttpStatus status;
    private final String message;

}
