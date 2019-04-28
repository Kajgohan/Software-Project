package bishopfish.mbta;



import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.derby.client.am.DateTime;
import org.json.*;

import static bishopfish.mbta.JsonReader.readJsonFromUrl;


public class Mbta {

//    public Mbta() {
//
//        URL url = null;
//        try {
//            url = new URL("https://graph.facebook.com/search?q=java&type=post");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try (InputStream is = url.openStream();) {
//            JsonReader rdr = new JsonReader(is);
//            //JsonReader rdr = JsonObject.createReader(is)
//
//            JsonObject obj = rdr.readObject();
//            JsonArray results = obj.getJsonArray("data");
//            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//                System.out.print(result.getJsonObject("from").getString("name"));
//                System.out.print(": ");
//                System.out.println(result.getString("message", ""));
//                System.out.println("-----------");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public Mbta() {

    }


    public static ArrayList<String> getPredictionsBrigham() {
        ArrayList<String> predictions = new ArrayList<>();
        try {
            JSONObject json = readJsonFromUrl("https://api-v3.mbta.com/predictions?filter[stop]=place-brmnl");
            //System.out.println(json.toString());
            JSONArray data = (JSONArray) json.get("data");
           // System.out.println(data);

            for (int i = 0 ; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                int dir = (int) obj.getJSONObject("attributes").get("direction_id");
                String arriveTimeString = (String) obj.getJSONObject("attributes").get("arrival_time");
                String arriveTimeTrim = arriveTimeString.substring(0, 19);
                DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(arriveTimeTrim, inFormatter);

                long minutesUntil = Duration.between(LocalDateTime.now(), dateTime).toMinutes();

                if (minutesUntil > 0 && minutesUntil < 20) {
                    String direction = (dir == 1) ? "Heath St" : "Lechmere";
                    predictions.add(String.format("%s Train in %s minutes", direction, minutesUntil));
                }
             //   System.out.println("dir: " + dir + " arrive time: in " + minutesUntil + " minutes");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return predictions;
    }



    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json =  readJsonFromUrl("https://api-v3.mbta.com/predictions?filter[stop]=place-brmnl");
        System.out.println(json.toString());
        JSONArray data = (JSONArray) json.get("data");
        System.out.println(data);

//        Iterator<String> keys = json.keys();
//        while(keys.hasNext()) {
//            String key = keys.next();
//            System.out.println(json.get(key) instanceof JSONArray);
//            if (json.get(key) instanceof JSONObject) {
//                System.out.println(json.get(key));
//            }
//        }

        for (int i = 0 ; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            int dir = (int) obj.getJSONObject("attributes").get("direction_id");
            String arriveTimeString = (String) obj.getJSONObject("attributes").get("arrival_time");
            String arriveTimeTrim = arriveTimeString.substring(0, 19);
            System.out.println(arriveTimeTrim);
            DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//            LocalDateTime dateTime = (LocalDateTime) f.parse(arriveTimeTrim);
            LocalDateTime dateTime = LocalDateTime.parse(arriveTimeTrim, inFormatter);


            System.out.println("dir: " + dir + " arrive time: in " + Duration.between(LocalDateTime.now(), dateTime).toMinutes() + " minutes");

        }

//        for ()
//        JSONArray attributes = (JSONArray) data.get("attributes");
//        List<String> longnames = data.stream()
//                .filter(str -> str.length() > 6 && str.length() < 8) //Multiple conditions
//                .collect(Collectors.toList());
//
//        longnames.forEach(System.out::println);


//        System.out.println(data);
    }


}
