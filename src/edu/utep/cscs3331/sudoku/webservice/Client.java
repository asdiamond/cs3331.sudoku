package edu.utep.cscs3331.sudoku.webservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Author: Aleksandr Diamond
 * Assignment: sudoku
 * Purpose:
 */
//http://www.studytrails.com/java/json/java-google-json-parse-json-to-java/
//http://www.cs.utep.edu/cheon/ws/sudoku/
public class Client {
        /**
         * Retrieve the document at the specified URL by sending a GET request;
         * return null if the request/connection fails.
         */
        public String sendGet(String urlString) {
            HttpURLConnection con = null;
            try {
                URL url = new URL(urlString);
                con = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return null;
        }

        public static void main(String[] args) {
//            String url = "http://www.cs.utep.edu/cheon/ws/sudoku/info/";
//            String response = new Client().sendGet(url);
//            System.out.println(response);

            PuzzleRequest request = new PuzzleRequest(4, 2);
            PuzzleRequest.Puzzle response = request.query();

//            for (Square t : response.getSquares()) {
//                System.out.println(t);
//            }
        }
}
