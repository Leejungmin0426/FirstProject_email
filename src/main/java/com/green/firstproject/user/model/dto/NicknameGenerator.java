package com.green.firstproject.user.model.dto;


import java.util.Random;

public class NicknameGenerator {
    private static final String[] ADJECTIVES = {"귀여운", "사랑스러운", "소중한", "똑똑한", "재빠른"};
    private static final String[] NOUNS = {"토끼", "강아지", "고양이", "햄스터", "앵무새"};
    private static final Random RANDOM = new Random();

    public static String generateDefaultNickname() {
        String adjective = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[RANDOM.nextInt(NOUNS.length)];
        return adjective + noun;
    }
}