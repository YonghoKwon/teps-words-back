// com/tepswords/back/model/ExpressionMeaning.java
package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expression_meaning")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExpressionMeaning {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meaningId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expression_id", nullable = false)
    private Expression expression;

    @Column(nullable = false, length = 255)
    private String meaning;

    @Column(nullable = false)
    private Integer position;
}