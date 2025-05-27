// com/tepswords/back/service/RandomWordService.java
package com.tepswords.back.service;

import com.tepswords.back.dto.RandomWordDto;
import com.tepswords.back.model.*;
import com.tepswords.back.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomWordService {
    private final ExpressionRepository exprRepo;

    public RandomWordService(ExpressionRepository exprRepo) {
        this.exprRepo = exprRepo;
    }

    @Transactional(readOnly = true)
    public RandomWordDto getRandomByCategoryId(Integer categoryId) {
        List<Expression> list;

        if (categoryId == 0) {
            // categoryId가 0인 경우 모든 표현 데이터 조회
            list = exprRepo.findAll();
        } else {
            // 특정 카테고리의 표현 데이터 조회
            list = exprRepo.findByCategoryCategoryId(categoryId);
        }

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No expressions found.");
        }

        // 1) 랜덤 표현
        Expression expr = list.get(ThreadLocalRandom.current().nextInt(list.size()));

        // 2) 랜덤 뜻
        List<ExpressionMeaning> meanings = expr.getMeanings();
        String randMeaning = meanings.isEmpty() ? "" :
                meanings.get(ThreadLocalRandom.current().nextInt(meanings.size())).getMeaning();

        // 3) 랜덤 예문
        List<ExampleSentence> sents = expr.getExamples();
        if (sents.isEmpty()) {
            return new RandomWordDto(expr.getExpressionId(), expr.getEnglish(), randMeaning, "", "");
        }
        ExampleSentence sent = sents.get(ThreadLocalRandom.current().nextInt(sents.size()));

        // 4) 랜덤 예문 번역
        List<ExampleTranslation> trans = sent.getTranslations();
        String randTrans = trans.isEmpty() ? "" :
                trans.get(ThreadLocalRandom.current().nextInt(trans.size())).getTranslation();

        return new RandomWordDto(
                expr.getExpressionId(),
                expr.getEnglish(),
                randMeaning,
                sent.getSentence(),
                randTrans
        );
    }
}