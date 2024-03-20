package org.springframework.samples.petclinic.calendar;

import org.springframework.samples.petclinic.artist.Artist;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // SEQUENCE for H2, IDENTITY for MySQL
//	@SequenceGenerator(name= "CALENDAR_SEQUENCE", sequenceName = "CALENDAR_SEQUENCE_ID", initialValue=2, allocationSize = 1)
//	@GeneratedValue(strategy=GenerationType.AUTO, generator="CALENDAR_SEQUENCE")
    private Long id;

    @ManyToOne // artist could have many scheduled events
    @JoinColumn(name = "artist_fk")
    private Artist artist; // fk, artist table's pk

    @Column
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start", nullable = false)
    private Date start; // start date, time

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended", nullable = false)
    private Date end;

    @Column
    private String link; // external URL
    private String meta; // reserved

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
