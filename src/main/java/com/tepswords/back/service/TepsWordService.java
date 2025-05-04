package com.tepswords.back.service;

import com.tepswords.back.model.TepsWord;
import com.tepswords.back.repository.TepsWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TepsWordService {

    private final TepsWordRepository tepsWordRepository;

    @Autowired
    public TepsWordService(TepsWordRepository tepsWordRepository) {
        this.tepsWordRepository = tepsWordRepository;
    }

    // 모든 단어 조회
    public List<TepsWord> getAllWords() {
        return tepsWordRepository.findAll();
    }

    // 단어로 검색
    public List<TepsWord> searchByWord(String word) {
        return tepsWordRepository.findByWordContaining(word);
    }

    // 품사로 검색
    public List<TepsWord> searchByPartOfSpeech(String partOfSpeech) {
        return tepsWordRepository.findByPartOfSpeech(partOfSpeech);
    }

    public Optional<TepsWord> getWordById(Integer seq, String word, String partOfSpeech, String meaning) {
        TepsWord.TepsWordId id = new TepsWord.TepsWordId(seq, word, partOfSpeech, meaning);
        return tepsWordRepository.findById(id);
    }

    // 랜덤 단어 가져오기
    public TepsWord getRandomWord() {
        return tepsWordRepository.findRandomWord();
    }

    // seq 범위로 단어 조회
    public List<TepsWord> getWordsBySeqRange(Integer startSeq, Integer endSeq) {
        return tepsWordRepository.findBySeqBetweenOrderBySeqAsc(startSeq, endSeq);
    }
}

