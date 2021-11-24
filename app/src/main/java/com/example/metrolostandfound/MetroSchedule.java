package com.example.metrolostandfound;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;

public class MetroSchedule {
    private static Map<String, Map<String, List<String>>> sb;

    //순서를 맞추기 위해
    private static List<String> sbStationList;
    
    //호선 리스트
    private static List<String> lines;

    //공백까지 모두 넣기
    private static List<String> sbAllStationList;
    private static List<String> sbAllTrainList;
    private static List<List<String>> sbAllTimeList;

    //몇분까지 같은 시간으로 쳐줄 것인가 (단위 : 분) 기본값 10;
    private static int bound;

    //역 이름 별로 출발시간 리스트와 도착시간 리스트 가 있는 구조
    static{
        sb = new HashMap<String, Map<String, List<String>>>();

        lines = new ArrayList<>();
        lines.add("수인분당선");

        sbStationList = new ArrayList<>();
        sbAllTimeList = new ArrayList<>();
        sbAllStationList = new ArrayList<>();
        sbAllTrainList = new ArrayList<>();

        bound = 20;
    }
    
    //역별로 따로 함수를 만들어뒀음 이게 좋을지는 모르겠는데 뭔가 이게 나을듯
    public static void readSbExcel(InputStream is) {
        try {

            Workbook wb = Workbook.getWorkbook(is);
            //region save1
            if (wb != null) {
                Sheet sheet = wb.getSheet(0);
                if (sheet != null) {
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 3;
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
                                sbAllStationList.add(contents);
                                contents = contents.replaceAll(" ", "");
                                if (contents.length() != 0) {
                                    name = contents;
                                    sbStationList.add(contents);
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
                            sb.put(name, d);
                        } else { //짝수 인덱스에는 출발시간 (역에서 출발하느 시간)
                            Map<String, List<String>> d = new HashMap<String, List<String>>();
                            d.put("출발시간", times);
                            sb.put(name, d);
                        }
                    }


                    for (int row = 3; row < rowTotal; row++) {
                        List<String> times = new ArrayList<>();
                        for (int col = 2; col < colTotal; col++) {
                            String contents = sheet.getCell(col, row).getContents();
                            times.add(contents);
                        }
                        sbAllTimeList.add(times);
                    }
                    for(int col = 2; col<colTotal; col++){
                        String contents = sheet.getCell(col, 2).getContents();
                        sbAllTrainList.add(contents);
                    }
                }
            }
            //endregion
        } catch (Exception e) {
            Log.d("Main", "에러" + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<String> getLines(){ return lines; }

    public static void setBound(int b){bound = b;}

    public static List<String> getSuinbundangArrivalTimes(String station){
        return sb.get(station).get("도착시간");
    }
    public static List<String> getSuinbundangDepartureTimes(String station){
        return sb.get(station).get("출발시간");
    }
    public static List<String> getSuinbundangStationList(){
        return sbStationList;
    }

    public static String whatTrain(LostObject o){
        if(o.getLine().equals("수인분당선")) {
            if(o.getDateTime() == null) return null;

            String[] dateTime = o.getDateTime().split(":");
            String station = o.getStation();

            if(station == null) return null;

            String hour = dateTime[3];
            String minute = dateTime[4];

            int row = 0;
            for(String s : sbAllStationList){
                if(s.equals(station)) break;
                ++row;
            }
            if(row == sbAllStationList.size()) return null;

            int col = 0;
            for(String t : sbAllTimeList.get(row)){

                t = t.replaceAll(" ", "");
                if(t.length()>0) {
                    Calendar calTarget = getDateTime(hour + ":" + minute);
                    Calendar calStart = getDateTime(t);
                    Calendar calEnd = getDateTime(t);

                    calStart.add(Calendar.MINUTE, -bound/2);
                    calEnd.add(Calendar.MINUTE, bound/2);

                    if (calStart.before(calTarget) && calEnd.after(calTarget)) {
                        break;
                    }
                }
                ++col;
            }

            if(col == sbAllTrainList.size()) return null;
            return sbAllTrainList.get(col);
        }
        return null;
    }

    private static Calendar getDateTime(String str){
        Calendar cal = Calendar.getInstance();
        String[] dateTime = str.split(":");

        String hour = dateTime[0];
        String minute = dateTime[1];

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), Integer.parseInt(hour), Integer.parseInt(minute));

        return cal;
    }
}
