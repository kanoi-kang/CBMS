package it.xdu.xzk.citybusmanagersystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Data
@Setter
public class SearchViolationByDriver {
    private String Sname;
    private Date StartDate;
    private Date EndDate;
    @Getter
    private String Punish;
    @Getter
    private String Vno;
    @Getter
    private String Vdate;
    @Getter
    private String Location;
    @Getter
    private String CarId;
    @Getter
    private String Vtype;
    @Getter
    private Integer time;

    private String Fname;
    @JsonProperty("Fname")
    public String getFname() {
         return Fname;
    }
    @JsonProperty("Sname")
    public String getSname() {
        return Sname;
    }
    @JsonProperty("StartDate")
    public Date getStartDate() {
        return StartDate;
    }
    @JsonProperty("EndDate")
    public Date getEndDate() {
        return EndDate;
    }
}
