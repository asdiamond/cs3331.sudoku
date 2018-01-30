package edu.utep.cscs3331.sudoku.webservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    //seems to serialize to info correctly
    public static void main(String[] args){
         Info info = new Info();
         GsonBuilder builder = new GsonBuilder();
         Gson gson = builder.create();
         String jsonStr = gson.toJson(info);
         System.out.println(jsonStr);

         Info serializedInfo = gson.fromJson(jsonStr, Info.class);
         int[] szs = serializedInfo.sizes;
        for (int i = 0; i < szs.length; i++) {
            System.out.println(szs[i]);
        }
   }
}
