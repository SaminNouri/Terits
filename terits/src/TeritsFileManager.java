import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Data on 3/25/2020.
 */
public class TeritsFileManager {
    private final String filePath = "extraFiles\\UserProperty.json";
    private HashMap<String, Object> map = new HashMap<>();

    public HashMap<String, Object> getPropertyMap() {


        HashMap<String, Object> map = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            java.lang.reflect.Type empMapType = new TypeToken<HashMap<String, Object>>() {
            }.getType();

            map = new Gson().fromJson(bufferedReader, empMapType);

            this.map = map;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;


    }

    public Color[][] getCells() {


        String temp = map.get("cells").toString();

        java.lang.reflect.Type empMapType = new TypeToken<Color[][]>() {
        }.getType();

        Color[][] c = new Gson().fromJson(temp, empMapType);

        return c;

    }

    public int getCurrentShapeIndex() {

        String temp = map.get("currentShapeIndex").toString();

        java.lang.reflect.Type empMapType = new TypeToken<Integer>() {
        }.getType();

        int index = new Gson().fromJson(temp, empMapType);

        return index;

    }
    public int getScore() {

        String temp = map.get("score").toString();

        java.lang.reflect.Type empMapType = new TypeToken<Integer>() {
        }.getType();

        int score= new Gson().fromJson(temp, empMapType);

        return score;

    }

    public ArrayList<Integer> getHighestScores() {


        String temp = map.get("highestScores").toString();

        java.lang.reflect.Type empMapType = new TypeToken<ArrayList<Integer>>() {
        }.getType();

        ArrayList<Integer> list = new Gson().fromJson(temp, empMapType);

        return list;

    }


    public void SaveFiles() {


        try {
            Gson gson = new Gson();
            //System.out.println(map);
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(map, fileWriter);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {

        }


    }
    public void SaveFiles(ArrayList<Integer> highScores,Color[][] c,int index,int score) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("highestScores",highScores);
        map.put("cells",c);
        map.put("currentShapeIndex",index);
        map.put("score",score);

        try {
            Gson gson = new Gson();
            //System.out.println(map);
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(map, fileWriter);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {

        }


    }
}
