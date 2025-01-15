package com.santobucle.VaseDB.entity;

import java.util.Date;

import jakarta.persistence.Column;
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

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "stage_build_id", nullable = false)
    private StageBuild stageBuild;

    @Column(name = "elapsed_time")
    private double elapsedTime;

    @Column(name = "speed_qualifier")
    private String speedQualifier;
    
    @Column(name = "vase_attributes", columnDefinition = "json")
    private String vaseAttributesDto;

    @Column(name="solved_at")
    private Date date;
}
