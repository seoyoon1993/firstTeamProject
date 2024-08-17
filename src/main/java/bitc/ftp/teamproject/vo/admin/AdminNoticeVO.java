package bitc.ftp.teamproject.vo.admin;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminNoticeVO {
    private int noticeNo;
    private String content;
    private Date startDate;
    private Date endDate;
    private Date uploadDate;
    private String title;
    private int userNo;
    private String name;
}
