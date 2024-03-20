package org.springframework.samples.petclinic.artist;

import lombok.*;

//import javax.persistence.*;
import jakarta.persistence.*;

@Entity
@Data // -> getter랑 setter 역할까지 다 해줌
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ARTIST")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId; // 아티스트 ID (PK)

    @Column
    private String artistImg; // 대표 이미지

    @Column(name = "artistContent", columnDefinition = "TEXT", nullable = false)
    private String artistContent; // 아티스트 설명

    @Column(nullable = false, unique = true)
    private String artistName; // 아티스트 이름

    @Column
    private int artistCount; // 팔로워수

    //Agency 조인
    @ManyToOne(fetch= FetchType.LAZY) //소속사는 여러 명의 아티스트를 가질 수 있음
    @JoinColumn(name = "agencyId")
    private Agency agency; // fk(=Agency_pk)

    public Artist(ArtistDto requestDto) {
        this.artistImg = requestDto.getArtistImg();
        this.artistContent = requestDto.getArtistContent();
        this.artistName = requestDto.getArtistName();
        this.artistCount = requestDto.getArtistCount();
    }

    public void update(ArtistDto requestDto) {
        this.artistImg = requestDto.getArtistImg();
        this.artistContent = requestDto.getArtistContent();
        this.artistName = requestDto.getArtistName();
        this.artistCount = requestDto.getArtistCount();
    }
}
/*
   //아티스트 ID (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

    //아티스트 코드
    @Column(name = "artist_cd", nullable = false, length = 100)
    private String artistCd;

    //아티스트 이름
    @Column(name = "artist_name", nullable = false, unique = true, length = 20)
    private String artistName;

    //아티스트 설명
    @Column(name = "comment", length = 500, nullable = false)
    private String comment;

    //아티스트 팔로워 수
    @Column(name = "follow_cnt", columnDefinition = "integer default 0")
    private int followCnt;

    //성별
    @Column(name = "gender", length = 1)
    private String gender;

    //유닛여부
    @Column(name = "unit_yn", length = 1)
    private String unitYN;

    //삭제여부
    @Column(name = "del_yn", length = 1)
    private String delYN;

    //등록 아이디
    @Column(name = "reg_id", length = 100)
    private String regId;

    //수정 아이디
    @Column(name = "mod_id", length = 100)
    private String modId;

    //Agency 조인
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY) //소속사는 여러 명의 아티스트를 가질 수 있음
    @JoinColumn(name = "agency_fk")
    private Agency agency; // fk(=Agency_pk)

 */
