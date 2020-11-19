package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Entity //jpa entity임을 알림
@NoArgsConstructor //인자없는 생성자 자동 생성
@AllArgsConstructor //인자를 모두 갖춘 생성자 자동생성
@Table(name = "user") //user테이블과 매핑됨을 명시
public class User implements UserDetails {

    @Id //pk알림
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성전략을 db에 위임한다는 의미 mysql로 보면 pk필드를 auto_increment로 설정한 상태
    private long mrsl;

    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //json으로 출력하지 않는 데이터
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }

    //계정이 만료가 안되었는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true; //사용하지 않으므로 true로 변경
    }

    //계정이 잠기지 않았는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 패스워드가 만료 안되었는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용가능한지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
