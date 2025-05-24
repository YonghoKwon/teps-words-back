// com/tepswords/back/dto/RandomWordDto.java
package com.tepswords.back.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RandomWordDto {
    private Integer expressionId;
    private String english;
    private String meaning;
    private String exampleSentence;
    private String exampleTranslation;
}
