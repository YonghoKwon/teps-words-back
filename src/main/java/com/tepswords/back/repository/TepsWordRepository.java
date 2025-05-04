package com.tepswords.back.repository;

import com.tepswords.back.model.TepsWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TepsWordRepository extends JpaRepository<TepsWord, TepsWord.TepsWordId> {

    // 단어로 검색
    List<TepsWord> findByWordContaining(String word);

    // 품사로 검색
    List<TepsWord> findByPartOfSpeech(String partOfSpeech);

    // seq로 검색
    List<TepsWord> findBySeq(Integer seq);

    // 랜덤 단어 가져오기
    @Query(value = "SELECT * FROM teps_words ORDER BY RAND() LIMIT 1", nativeQuery = true)
    TepsWord findRandomWord();

    // seq 범위로 검색
    List<TepsWord> findBySeqBetweenOrderBySeqAsc(Integer startSeq, Integer endSeq);
}

