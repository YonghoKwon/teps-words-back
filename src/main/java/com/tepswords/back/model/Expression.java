// com/tepswords/back/model/Expression.java
package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "expression")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Expression {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expressionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, length = 255)
    private String english;

    @OneToMany(mappedBy = "expression", fetch = FetchType.LAZY)
    private List<ExpressionMeaning> meanings;

    @OneToMany(mappedBy = "expression", fetch = FetchType.LAZY)
    private List<ExampleSentence> examples;
}
