package com.santobucle.VaseDB.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "StageBuild")
public class StageBuild {

    /** TODO: CREATE PRIMARY KEY OUT OF STAGE AND BUILD (AND ID?)
     * TO HELP IDENTIFY STAGEBUILDS AND AVOID DUPLICATES */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;
    
    @ManyToOne
    @JoinColumn(name = "build_id", nullable = false)
    private Build build;

    private int duration;

    @Column(columnDefinition = "json")
    private String qualifierData;

    @OneToMany(mappedBy = "stageBuild", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resolution> resolutions;
}
