package it.xdu.xzk.citybusmanagersystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Car {

    private String CarId; // 主键字段，非自增
    private Integer Rno;
    private Integer Seat;
    private String Ctype;
    private Integer Cage;
// @JsonProperty("Sno")!!!很重要，就是没配置这个导致前端json返回给后端的driver实体全是空，导致他们匹配不上
    @JsonProperty("CarId")
    public String getCarId() {
        return CarId;
    }
    @JsonProperty("Rno")
    public Integer getRno() {
        return Rno;
    }
    @JsonProperty("Seat")
    public Integer getSeat() {
        return Seat;
    }
    @JsonProperty("Ctype")
    public String getCtype() {
        return Ctype;
    }
    @JsonProperty("Cage")
    public Integer getCage() {
        return Cage;
    }
}
