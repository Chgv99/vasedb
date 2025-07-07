package com.santobucle.VaseDB.entity;

import java.util.Date;

import com.santobucle.VaseDB.dto.enums.Decision;
import com.santobucle.VaseDB.dto.enums.Result;
import com.santobucle.VaseDB.mapper.DecisionConverter;
import com.santobucle.VaseDB.mapper.ResultConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id") // , nullable = false, columnDefinition = new Game(-1l, -1f, new
                                  // ArrayList<Resolution>(), new Date())
    private Game game;

    @Column
    @Convert(converter = DecisionConverter.class)
    private Decision decision;

    @Column
    @Convert(converter = ResultConverter.class)
    private Result result;

    @Column(name = "elapsed_time")
    private double elapsedTime;

    @Column(name = "speed_qualifier")
    private String speedQualifier;

    @Column(name = "vase_attributes", columnDefinition = "json")
    private String vaseAttributesDto;

    @Column(name = "solved_at")
    private Date date;
}
