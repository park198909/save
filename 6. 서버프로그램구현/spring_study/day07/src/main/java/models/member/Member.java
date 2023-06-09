package models.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Member {
    private long userNo;
    private String userId;
    private String userPw;
    private String userNm;
    private LocalDateTime regDt;
}
