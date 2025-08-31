package com.santobucle.VaseDB.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @Column(name = "total_time")
    private float totalTime;

    @Column(name = "score")
    private int score;

    @Column(name = "is_hi_score")
    private boolean isHiScore;

    @ManyToOne
    @JoinColumn(name = "build_id")
    private Build build;

    // @JsonIgnore
    @OneToMany(mappedBy = "game", orphanRemoval = true)
    private List<Resolution> resolutions;

    @Column(name = "played_at")
    private Date date;
}
