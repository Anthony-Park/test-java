package org.springframework.samples.petclinic.kpop;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.samples.petclinic.artist.Artist;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "event")
@Builder
public class Place { //extends BaseTimeEntity {

    //이벤트 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    //이벤트 카테고리 코드 - 생일카페:100, 팝업스토어:200, 팬미팅:300, 팬싸인회:400, 전시회:500, 광고:600
    @Column(name = "event_category_cd", nullable = false)
    private Integer eventCategoryCd;

    //이벤트 명
    @Column(name = "event_name", nullable = false, length = 200)
    private String eventName;

    //이벤트 장소
    @Column(name = "place_name", length = 200)
    private String placeName;

    //우편번호
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    //지번주소(=기본주소)
    @Column(name = "street_address", nullable = false, length = 1000)
    private String streetAddress;

    //상세주소
    @Column(name = "detail_address", length = 1000)
    private String detailAddress;

    //이벤트 설명
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    //주의사항
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    //이벤트 외부 링크
    @Column(name = "event_url", length = 5000)
    private String eventUrl;

    //위도
    @Column(name = "latitude")
    private Double latitude;

    //경도
    @Column(name = "longitude")
    private Double longitude;

    //시작 날짜
    @Column(name = "start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    //종료 날짜
    @Column(name = "end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    //이벤트 시작 시간, "HH:mm:ss"형식으로 입력
    @Column(name = "start_time")
    private LocalTime startTime;

    //이벤트 종료 시간, "HH:mm:ss"형식으로 입력
    @Column(name = "end_time")
    private LocalTime endTime;

    //조회수
    @Column(name = "count", columnDefinition = "integer default 0")
    private int count;

    //이벤트 좋아요 수
    @Column(name = "follow_cnt", columnDefinition = "integer default 0")
    private int followCnt;

    //삭제여부
    @Column(name = "del_yn", length = 1)
    private String delYN;

    //등록 아이디
    @Column(name = "reg_id", length = 100)
    private String regId;

    //수정 아이디
    @Column(name = "mod_id", length = 100)
    private String modId;

    //Artist 조인
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "artist_fk")
    private Artist artist; //fk(=Artist_pk)

    //Image 조인
//    @OneToMany
//    private List<Image> image;

}
