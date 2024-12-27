package com.green.firstproject.user.model.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NicknameGenerator {
    private static final String[] ADJECTIVES = {"귀여운", "사랑스러운", "소중한", "똑똑한", "재빠른", "사악한", "무서운", "토실한", "둔둔한", "고능한", "깜찍한", "애교부리는", "차분한", "배고픈", "인사하는", "계절타는", "얼죽아"};
    private static final String[] NOUNS = {"토끼", "강아지", "고양이", "햄스터", "앵무새", "거북이", "금붕어", "물고기", "물개", "물범", "팬더", "열대어", "재롱이", "도마뱀", "펭귄", "친칠라", "카피바라", "카멜레온", "사슴", "북극곰", "하트물범", "너구리", "라쿤","알파카"};
    private static final Random RANDOM = new Random();
    private static final List<String> generatedNicknames = new ArrayList<>(); // 생성된 닉네임을 저장하는 리스트

    public static String generateDefaultNickname() {
        String nickname;
        do {
            nickname = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)] + NOUNS[RANDOM.nextInt(NOUNS.length)];
        } while (generatedNicknames.contains(nickname)); // 중복 체크

        generatedNicknames.add(nickname); // 새로운 닉네임 추가
        return nickname;
    }
}
