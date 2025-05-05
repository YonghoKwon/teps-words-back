package com.tepswords.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teps_words")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TepsWord {

    @Id
    private Integer seq;

    private Integer des_seq;

    private String description;

    private String word;

    private String meaning;

    private String note;
}