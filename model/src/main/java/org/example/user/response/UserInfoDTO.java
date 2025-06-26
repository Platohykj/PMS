package org.example.user.response;

import lombok.Data;
import org.example.position.model.Position;

import java.time.LocalDate;

@Data
public class UserInfoDTO {
    private String name;
    private String jobId;
    private String gender;
    private LocalDate birth;
    private String idCard;
    private String maritalStatus;
    private String ethnicity;
    private String nativePlace;
    private String politicalStatus;
    private String email;
    private String phone;
    private String address;
    private String department;
    private String subDepartment;
    private Position position;
    private String titleName;
    private String titleLevel;
    private String employmentType;
    private LocalDate entryDate;
    private LocalDate promotionDate;
    private LocalDate contractStart;
    private LocalDate contractEnd;
    private Integer contractYears;
    private String education;
}
