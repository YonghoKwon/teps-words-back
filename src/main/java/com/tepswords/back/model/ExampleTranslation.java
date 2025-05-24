// com/tepswords/back/model/ExampleTranslation.java
package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "example_translation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExampleTranslation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer translationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence_id", nullable = false)
    private ExampleSentence example;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String translation;

    @Column(nullable = false)
    private Integer position;
}
