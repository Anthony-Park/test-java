package org.springframework.samples.petclinic.kpop;

//import hello.kpop.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "user_kpop")
public class User {
    //회원 번호
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //회원 이름
    @Column(name = "user_name", length = 100)
    private String userName;

    //회원 이메일
    @Column(name = "user_email", length = 100)
    private String userEmail;

    //회원 비밀번호
    @Column(name = "password")
    private String password;

    //회원 닉네임
    @Column(name = "nickname", length = 100)
    private String nickname;

//    @Column(name = "user_type", length = 10)
//    @Enumerated(EnumType.STRING)
//    private Role userType; // 역할

    @Column(name = "user_stat_code", length = 10)
    private String userStatCode; // 상태코드

    @Column(name = "lock_yn", length = 1)
    private Character lockYn; // 잠금 여부

    @Column(name = "lock_cnt")
    private Integer lockCnt; // 잠금 횟수

    @Column(name = "lock_last_date")
    private LocalDateTime lockLastDate; // 잠금 최종 시점

//    @Column(name = "social_type", length = 100)
//    @Enumerated(EnumType.STRING)
//    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    @Column(name = "social_id", length = 200)
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    @Column(name = "refresh_token", length = 1000)
    private String refreshToken; // 리프레시 토큰

    @Column(name = "join_date")
    @CreatedDate
    private LocalDateTime joinDate; //생성일시

    @Column(name = "last_mod_date")
    @LastModifiedDate
    private LocalDateTime lastModDate; //최종 수정일시

//    // 유저 권한 설정 메소드
//    public void authorizeUser() {
//        this.userType = Role.USER;
//    }

//    // 비밀번호 암호화 메소드
//    public void passwordEncode(PasswordEncoder passwordEncoder) {
//        this.password = passwordEncoder.encode(this.password);
//    }

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

//    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
//        this.password = passwordEncoder.encode(updatePassword);
//    }

    //RefreshToken 재발급 메소드
    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

//    public User(UserRequestDto requestDto) {
//        this.userName = requestDto.getUserName();
//        this.userEmail = requestDto.getUserEmail();
//        this.password = requestDto.getPassword();
//        this.nickname = requestDto.getNickname();
//    }

//    public void update(UserRequestDto requestDto, PasswordEncoder passwordEncoder) {
//        this.userName = requestDto.getUserName();
//        this.userEmail = requestDto.getUserEmail();
//        this.nickname = requestDto.getNickname();
//
//        // 새로운 비밀번호가 null이 아니고, 기존 비밀번호와 다를 때만 인코딩하여 업데이트
//        if (requestDto.getPassword() != null && !requestDto.getPassword().equals(this.password)) {
//            this.password = passwordEncoder.encode(requestDto.getPassword());
//        }
//    }
}