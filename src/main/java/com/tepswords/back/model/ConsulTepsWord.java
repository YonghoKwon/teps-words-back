package com.tepswords.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "consulteps_words")
@IdClass(ConsulTepsWord.TepsWordId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsulTepsWord {

    @Id
    private Integer seq;

    @Id
    private String word;

    @Id
    @Column(name = "part_of_speech")
    private String partOfSpeech;

    @Id
    private String meaning;

    // 복합 키 클래스
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class TepsWordId implements Serializable {
        private Integer seq;
        private String word;
        private String partOfSpeech;
        private String meaning;
    }
}

