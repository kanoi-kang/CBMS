package it.xdu.xzk.citybusmanagersystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Date;
@Data
@Setter
public class Violation {
     private String Vno;
     private String Sname;
     @Getter
     private String Sno;
     private Date Vdate;
     private Time Vtime;
     private String Location;
     private String CarId;
     private String Vtype;

     @JsonProperty("Vno")
     public String getVno() {
          return Vno;
     }
     @JsonProperty("Sname")
     public String getSname(){
          return Sname;
     }
     @JsonProperty("Vdate")
     public Date getVdate() {
          return Vdate;
     }
     @JsonProperty("Location")
     public String getLocation() {
          return Location;
     }
     @JsonProperty("CarId")
     public String getCarId() {
          return CarId;
     }
     @JsonProperty("Vtype")
     public String getVtype() {
          return Vtype;
     }
}
