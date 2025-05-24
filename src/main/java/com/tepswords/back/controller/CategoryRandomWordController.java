// com/tepswords/back/controller/CategoryRandomWordController.java
package com.tepswords.back.controller;

import com.tepswords.back.dto.RandomWordDto;
import com.tepswords.back.service.RandomWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRandomWordController {

    private final RandomWordService randomWordService;

    public CategoryRandomWordController(RandomWordService randomWordService) {
        this.randomWordService = randomWordService;
    }

    @GetMapping("/{categoryId}/random-word")
    public ResponseEntity<RandomWordDto> randomWord(@PathVariable Integer categoryId) {
        RandomWordDto dto = randomWordService.getRandomByCategoryId(categoryId);
        return ResponseEntity.ok(dto);
    }
}
