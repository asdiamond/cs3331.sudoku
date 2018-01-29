package edu.utep.cscs3331.sudoku.webservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Info {
    public int[] sizes = new int[]{4, 9};
    public int[] levels = new int[]{1, 2, 3};
    public int defaultSize = 4;
    public int defaultLevel = 1;

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
