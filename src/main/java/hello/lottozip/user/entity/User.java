package hello.lottozip.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter //setter메서드의 사용을 지양하여 객체의 일관성, 안정성을 보장
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
@ToString
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_birth")
    @Temporal(TemporalType.DATE)
    private Date userBirth;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "user_email")
    private String userEmail;
}
