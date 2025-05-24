// src/main/java/com/yourorg/tepswords/model/Category.java
package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false)
    private String group1;

    @Column(nullable = false)
    private String group2;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Expression> expressions;
}
