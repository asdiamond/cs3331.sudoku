package edu.utep.cs3331.sudoku.console.webservice;
import com.google.gson.Gson;

public class Info {
    private static final String requestUrl = "http://www.cs.utep.edu/cheon/ws/sudoku/info/ ";
    public int[] sizes = new int[]{};
    public int[] levels = new int[]{};
    public int defaultSize;
    public int defaultLevel;

    public static Info query(){
        String jsonResponse = new Client().sendGet(requestUrl);
        return new Gson().fromJson(jsonResponse, Info.class);
    }
}
