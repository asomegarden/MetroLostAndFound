package com.example.metrolostandfound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectCategory {
    private static List<String> mainCategories;
    private static Map<String, List<String>> categories;

    static{
        mainCategories = Arrays.asList(new String[] {
                "가방", "귀중품", "도서", "안경", "공구", "스포츠용품", "의류", "자동차", "전자기기"
                , "지갑", "증명서", "카드", "현금", "휴대폰", "기타용품"
        }); //keySet이 되면 순서가 마구잡이가 돼서 순서를 임의로 고정시키기 위해 따로 리스트로 관리

        List<String> subBag = Arrays.asList(new String[]{
                "백팩", "핸드백/숄더백/메신저", "캐리어", "클러치백", "파우치"
                , "에코백", "쇼핑백", "기타"
        });

        List<String> subJewel = Arrays.asList(new String[]{
                "반지", "목걸이", "귀걸이", "시계", "기타"
        });//귀금속

        List<String> subBook = Arrays.asList(new String[]{
                "도서", "잡지", "서류", "기타"
        });//도서

        List<String> subGlasses = Arrays.asList(new String[]{
                "안경", "선글라스", "콘텍트렌즈", "기타"
        });//안경

        List<String> subTool = Arrays.asList(new String[]{
                "공구", "기타"
        });//공구

        List<String> subSports = Arrays.asList(new String[]{
                "스포츠용품"
        });//스포츠용품

        List<String> subClothes = Arrays.asList(new String[]{
                "여성의류", "남성의류", "스포츠의류", "아동의류", "모자", "신발", "기타"
        });//의류

        List<String> subCar = Arrays.asList(new String[]{
                "자동차열쇠", "기타"
        });//자동차

        List<String> subDigital = Arrays.asList(new String[]{
                "태블릿PC", "카메라", "노트북", "이어폰", "스마트워치", "기타"
        });//전자기기

        List<String> subWallet = Arrays.asList(new String[]{
                "여성용지갑", "남성용지갑"
        });//지갑

        List<String> subIDCard = Arrays.asList(new String[]{
                "신분증", "면허증", "여권", "기타"
        });//증명서

        List<String> subCard = Arrays.asList(new String[]{
                "신용(체크)카드", "교통카드", "기타"
        });//카드

        List<String> subMoney = Arrays.asList(new String[]{
                "현금", "수표", "상품권", "외화", "기타"
        });//현금

        List<String> subPhone = Arrays.asList(new String[]{
                "스마트폰", "아이폰", "피처폰", "케이스", "충전기", "기타"
        });//휴대폰

        List<String> subEtc = Arrays.asList(new String[]{
                "기타용품"
        });

        categories = new HashMap<>();
        
        categories.put("가방", subBag);
        categories.put("귀중품", subJewel);
        categories.put("도서", subBook);
        categories.put("안경", subGlasses);
        categories.put("공구", subTool);
        categories.put("스포츠용품", subSports);
        categories.put("의류", subClothes);
        categories.put("자동차", subCar);
        categories.put("전자기기", subDigital);
        categories.put("지갑", subWallet);
        categories.put("증명서", subIDCard);
        categories.put("카드", subCard);
        categories.put("현금", subMoney);
        categories.put("휴대폰", subPhone);
        categories.put("기타용품", subEtc);
    }

    public static List<String> getMainCategories(){return mainCategories;}
    public static List<String> getSubCategories(String mc){return categories.get(mc);}
}
