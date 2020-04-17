package com.twitter.TwitterWS.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date created_at;

    @Column( length = 100000 )
    private String text;

    private String location;

    @ManyToOne
    private User user;

    private boolean validated;
}
