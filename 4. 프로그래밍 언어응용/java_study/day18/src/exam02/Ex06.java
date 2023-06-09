package exam02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Ex06 {
    public static void main(String[] args) {
        ZoneId seoul = ZoneId.of("Asia/Seoul");
        LocalDateTime date1 = LocalDateTime.now();
        ZonedDateTime zdt1 = date1.atZone(seoul);

        System.out.println(zdt1);
    }
}
