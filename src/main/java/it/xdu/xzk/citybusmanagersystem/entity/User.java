//domain中的User.java
package it.xdu.xzk.citybusmanagersystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "user")
@Entity
public class User {
    // 注意属性名要与数据表中的字段名一致
    // 主键自增int(10)对应long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    // 用户名属性varchar对应String
    private String username;

    // 密码属性varchar对应String
    private String password;

}
