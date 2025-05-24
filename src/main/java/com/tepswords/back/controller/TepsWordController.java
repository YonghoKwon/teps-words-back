package com.tepswords.back.controller;

import com.tepswords.back.model.TepsWord;
import com.tepswords.back.service.TepsWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teps-words")
public class TepsWordController {

    private final TepsWordService tepsWordService;

    @Autowired
    public TepsWordController(TepsWordService tepsWordService) {
        this.tepsWordService = tepsWordService;
    }

    // 모든 단어 조회
    @GetMapping
    public ResponseEntity<List<TepsWord>> getAllWords() {
        List<TepsWord> words = tepsWordService.getAllWords();
        return ResponseEntity.ok(words);
    }

    // seq(PK)로 단어 조회
    @GetMapping("/{seq}")
    public ResponseEntity<?> getWordById(@PathVariable Integer seq) {
        return tepsWordService.getWordById(seq)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 단어로 검색
    @GetMapping("/search/word/{word}")
    public ResponseEntity<List<TepsWord>> searchByWord(@PathVariable String word) {
        List<TepsWord> words = tepsWordService.searchByWord(word);
        return ResponseEntity.ok(words);
    }

    // 설명으로 검색
    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<TepsWord>> searchByDescription(@PathVariable String description) {
        List<TepsWord> words = tepsWordService.searchByDescription(description);
        return ResponseEntity.ok(words);
    }

    // 랜덤 단어 가져오기
    @GetMapping("/random")
    public ResponseEntity<TepsWord> getRandomWord() {
        TepsWord randomWord = tepsWordService.getRandomWord();
        if (randomWord != null) {
            return ResponseEntity.ok(randomWord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // seq 범위로 단어 조회 (예: 1~20)
    @GetMapping("/range")
    public ResponseEntity<List<TepsWord>> getWordsBySeqRange(
            @RequestParam(defaultValue = "1") Integer startSeq,
            @RequestParam(defaultValue = "20") Integer endSeq) {

        // 범위 유효성 검사
        if (startSeq < 1) {
            startSeq = 1;
        }

        if (endSeq < startSeq) {
            endSeq = startSeq;
        }

        List<TepsWord> words = tepsWordService.getWordsBySeqRange(startSeq, endSeq);
        return ResponseEntity.ok(words);
    }

    // 단어 추가
    @PostMapping
    public ResponseEntity<TepsWord> addWord(@RequestBody TepsWord tepsWord) {
        TepsWord savedWord = tepsWordService.saveWord(tepsWord);
        return new ResponseEntity<>(savedWord, HttpStatus.CREATED);
    }

    // 단어 수정
    @PutMapping("/{seq}")
    public ResponseEntity<?> updateWord(
            @PathVariable Integer seq,
            @RequestBody TepsWord tepsWord) {

        if (!tepsWordService.getWordById(seq).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // ID 필드 설정 확인
        tepsWord.setSeq(seq);

        TepsWord updatedWord = tepsWordService.saveWord(tepsWord);
        return ResponseEntity.ok(updatedWord);
    }

    // 단어 삭제
    @DeleteMapping("/{seq}")
    public ResponseEntity<?> deleteWord(@PathVariable Integer seq) {
        if (!tepsWordService.getWordById(seq).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        tepsWordService.deleteWord(seq);
        return ResponseEntity.noContent().build();
    }
}