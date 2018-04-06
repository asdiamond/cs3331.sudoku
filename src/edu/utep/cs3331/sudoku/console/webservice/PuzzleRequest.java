package edu.utep.cs3331.sudoku.console.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Author: Aleksandr Diamond on 1/29/2018
 * Assignment: sudoku
 * Purpose:
 */
public class PuzzleRequest {
    //url for formatting. not actual request url. FIXME?
    private String requestUrl = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
    private int size;
    private int level;

    public PuzzleRequest(){
        this(1, 4);//by default
    }

    public PuzzleRequest(int size, int level){
        this.size = size;
        this.level = level;
    }

    public Puzzle query(){
        //query the web service and return what is returned by it
        String url = String.format(requestUrl, size, level);
        String jsonResponse = new Client().sendGet(url);
        //TODO serialize this json response and return its POJO
//        System.out.println(toPrettyFormat(jsonResponse));

        //return conversion of web service to Puzzle POJO
        return new Gson().fromJson(jsonResponse, Puzzle.class);
    }

    public static String toPrettyFormat(String jsonString){
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(json);
    }

    //the response will be a puzzle
    public class Puzzle{
        private final String requestUrl = "http://www.cs.utep.edu/cheon/ws/sudoku/new/[?size][&level]";
        private int size;

        //response vars
        private Boolean response;
        private Square[] squares;

        public Puzzle(int size){
            this.size = size;
        }

        public Square[] getSquares() {
            return squares;
        }

        public Boolean getResponse() {
            return response;
        }

        public int getSize() {
            return size;
        }
    }
}
