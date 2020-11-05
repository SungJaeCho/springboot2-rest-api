package com.rest.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity //jpa entity임을 알림
@NoArgsConstructor //인자없는 생성자 자동 생성
@AllArgsConstructor //인자를 모두 갖춘 생성자 자동생성
@Table(name = "user") //user테이블과 매핑됨을 명시
public class User {

    @Id //pk알림
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성전략을 db에 위임한다는 의미 mysql로 보면 pk필드를 auto_increment로 설정한 상태
    private long mrsl;
    @Column(nullable = false, unique = true, length = 30)
    private String uid;
    @Column(nullable = false, length = 100)
    private String name;
}
