package bitc.ftp.teamproject.dto.admin;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminNoticeDTO {
    private int noticeNo;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Date uploadDate;
    private String title;
    private int userNo;
    private String name;

}
