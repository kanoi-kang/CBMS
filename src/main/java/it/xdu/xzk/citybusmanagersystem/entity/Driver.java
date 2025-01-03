package it.xdu.xzk.citybusmanagersystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Driver {
    private String Sno; // 主键字段，非自增
    private String Sname;
    private String Gender;
    private Integer Sage;
    private String Stel;
    private Integer Rno;
    private String Fname;
// @JsonProperty("Sno")!!!很重要，就是没配置这个导致前端json返回给后端的driver实体全是空，导致他们匹配不上
    @JsonProperty("Sno")
    public String getSno() {
        return Sno;
    }
    @JsonProperty("Sname")
    public String getSname() {
        return Sname;
    }
    @JsonProperty("Gender")
    public String getGender() {
        return Gender;
    }
    @JsonProperty("Sage")
    public Integer getSage() {
        return Sage;
    }
    @JsonProperty("Stel")
    public String getStel() {
        return Stel;
    }
    @JsonProperty("Rno")
    public Integer getRno() {
        return Rno;
    }
    @JsonProperty("Fname")
    public String getFname() {
        return Fname;
    }

}
