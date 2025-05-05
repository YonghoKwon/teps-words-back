package com.tepswords.back.controller;


import com.tepswords.back.model.ConsulTepsWord;
import com.tepswords.back.service.ConsulTepsWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class ConsulTepsWordController {

    private final ConsulTepsWordService tepsWordService;

    @Autowired
    public ConsulTepsWordController(ConsulTepsWordService tepsWordService) {
        this.tepsWordService = tepsWordService;
    }

    // 모든 단어 조회
    @GetMapping
    public ResponseEntity<List<ConsulTepsWord>> getAllWords() {
        List<ConsulTepsWord> words = tepsWordService.getAllWords();
        return ResponseEntity.ok(words);
    }

    // ID로 단어 조회
    @GetMapping("/{seq}/{word}/{partOfSpeech}/{meaning}")
    public ResponseEntity<?> getWordById(
            @PathVariable Integer seq,
            @PathVariable String word,
            @PathVariable String partOfSpeech,
            @PathVariable String meaning) {

        return tepsWordService.getWordById(seq, word, partOfSpeech, meaning)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 랜덤 단어 가져오기
    @GetMapping("/random")
    public ResponseEntity<ConsulTepsWord> getRandomWord() {
        ConsulTepsWord randomWord = tepsWordService.getRandomWord();
        if (randomWord != null) {
            return ResponseEntity.ok(randomWord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // seq 범위로 단어 조회 (예: 1~20)
    @GetMapping("/range")
    public ResponseEntity<List<ConsulTepsWord>> getWordsBySeqRange(
            @RequestParam(defaultValue = "1") Integer startSeq,
            @RequestParam(defaultValue = "20") Integer endSeq) {

        // 범위 유효성 검사
        if (startSeq < 1) {
            startSeq = 1;
        }

        if (endSeq < startSeq) {
            endSeq = startSeq;
        }

        List<ConsulTepsWord> words = tepsWordService.getWordsBySeqRange(startSeq, endSeq);
        return ResponseEntity.ok(words);
    }

    // ConsulTepsWordController.java에 추가
    @GetMapping("/random/partOfSpeech/{partOfSpeech}")
    public ResponseEntity<ConsulTepsWord> getRandomWordByPartOfSpeech(@PathVariable String partOfSpeech) {
        ConsulTepsWord randomWord = tepsWordService.getRandomWordByPartOfSpeech(partOfSpeech);
        if (randomWord != null) {
            return ResponseEntity.ok(randomWord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

