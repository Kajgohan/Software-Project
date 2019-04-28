package bishopfish.utils;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class SayWeather {
    public static void sayWeather() throws APIException {
        // declaring object of "OWM" class
        OWM owm = new OWM("a50ad26122793d64d9911f26cdb749c4");

        // getting current weather data for the "Boston" city
        CurrentWeather cwd = owm.currentWeatherByCityName("Boston");

        //printing city name from the retrieved data
        double fMin = ((((cwd.getMainData().getTempMin())-273.0)*(9/5))+32.0);
        double fMax = ((((cwd.getMainData().getTempMax())-273.0)*(9/5))+32.0);
        String tempText = String.format("Welcome to Brygum and women's Hospital, It is currently %.0f Fahrenheit. Today's high is expected to be %.0f Fahrenheit. Please log in or press Continue as Guest.",fMin,fMax);
        TextToSpeech.speak(tempText);
    }
}
