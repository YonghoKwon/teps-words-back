// com/tepswords/back/model/ExampleSentence.java
package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "example_sentence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExampleSentence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sentenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expression_id", nullable = false)
    private Expression expression;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sentence;

    @Column(nullable = false)
    private Integer position;

    @OneToMany(mappedBy = "example", fetch = FetchType.LAZY)
    private List<ExampleTranslation> translations;
}
