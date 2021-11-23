package com.example.metrolostandfound;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;

public class MetroSchedule {
    private static Map<String, Map<String, List<String>>> suinbundang;
    private static Map<String, List<String>> suinbundangTrainTimes;
    private static List<String> suinbundangStationList;
    private static List<String> suinbundangTrainList;
    //순서를 맞추기 위해
    private static List<String> lines;

    //역 이름 별로 출발시간 리스트와 도착시간 리스트 가 있는 구조
    static{
        suinbundang = new HashMap<String, Map<String, List<String>>>();
        suinbundangStationList = new ArrayList<>();
        lines = new ArrayList<>();
        lines.add("수인분당선");
    }
    
    //역별로 따로 함수를 만들어뒀음 이게 좋을지는 모르겠는데 뭔가 이게 나을듯
    public static void readSuinbundangExcel(InputStream is) {
        try {
            Workbook wb = Workbook.getWorkbook(is);

            if (wb != null) {
                Sheet sheet = wb.getSheet(0);
                if (sheet != null) {
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 5;
                    int rowTotal = sheet.getColumn(0).length;
                    for (int i = 1; i < colTotal; i++) {
                        if (rowTotal < sheet.getColumn(i).length)
                            rowTotal = sheet.getColumn(i).length;
                    }

                    String name = null;


                    for (int row = rowIndexStart; row < rowTotal; row++) {
                        List<String> times = new ArrayList<>();
                        for (int col = 1; col < colTotal; col++) {
                            if (col == 1) {
                                String contents = sheet.getCell(col, row).getContents();
                                contents = contents.replaceAll(" ", "");
                                if (contents.length() != 0) {
                                    name = contents;
                                    suinbundangStationList.add(contents);
                                }
                            } else {
                                String contents = sheet.getCell(col, row).getContents();
                                contents = contents.replaceAll(" ", "");
                                if (contents.length() != 0) {
                                    times.add(contents);
                                }
                            }
                        }
                        if (row % 2 == 1) { //홀수 인덱스에는 도착시간 (역에 도착하는 시간)
                            Map<String, List<String>> d = new HashMap<String, List<String>>();
                            d.put("도착시간", times);
                            suinbundang.put(name, d);
                        } else { //짝수 인덱스에는 출발시간 (역에서 출발하느 시간)
                            Map<String, List<String>> d = new HashMap<String, List<String>>();
                            d.put("출발시간", times);
                            suinbundang.put(name, d);
                        }
                    }
                }
            }
            if (wb != null) {
                Sheet sheet = wb.getSheet(0);
                if (sheet != null) {
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 2;
                    int rowTotal = sheet.getColumn(0).length;
                    for (int i = 1; i < colTotal; i++) {
                        if (rowTotal < sheet.getColumn(i).length)
                            rowTotal = sheet.getColumn(i).length;
                    }

                    String name = null;

                    for (int col = 2; col < colTotal; col++) {
                        List<String> times = new ArrayList<>();
                        name = "";
                        for (int row = rowIndexStart; row < rowTotal; row++) {
                            if (row == rowIndexStart) {
                                String contents = sheet.getCell(col, row).getContents();
                                contents = contents.replaceAll(" ", "");
                                if (contents.length() != 0) {
                                    name = contents;
                                    suinbundangTrainList.add(contents);
                                }
                            } else {
                                String contents = sheet.getCell(col, row).getContents();
                                contents = contents.replaceAll(" ", "");
                                if (contents.length() != 0) {
                                    times.add(contents);
                                }
                            }
                        }
                        if (!name.equals("")) { //홀수 인덱스에는 도착시간 (역에 도착하는 시간)
                            suinbundangTrainTimes.put(name, times);
                            Log.d("열차별시간", name + times.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d("Main", "에러" + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<String> getLines(){return lines;}

    public static List<String> getSuinbundangArrivalTimes(String station){
        return suinbundang.get(station).get("도착시간");
    }
    public static List<String> getSuinbundangDepartureTimes(String station){
        return suinbundang.get(station).get("출발시간");
    }
    public static List<String> getSuinbundangStationList(){
        return suinbundangStationList;
    }
    
}
