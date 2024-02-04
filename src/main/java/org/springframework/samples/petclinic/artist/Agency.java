package org.springframework.samples.petclinic.artist;

import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;

@Entity
@Data // -> getter랑 setter 역할까지 다 해줌
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "AGENCY")
@Builder
public class Agency {

    //소속사ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agencyId")
    private Long agencyId;

    //소속사 이름
    @Column(name = "agencyName")
    private String agencyName;
}
