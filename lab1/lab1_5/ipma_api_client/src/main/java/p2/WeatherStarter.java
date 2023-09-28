package p2;

import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //todo: should generalize for a city passed as argument
    //private static final int CITY_ID_AVEIRO = 1010500;
    private static int CITY_ID;

    public static void  main(String[] args ) {

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint

        if(args[0]!=null){
            Scanner input = new Scanner(args[0]);
            int city_id = input.nextInt();
            CITY_ID = city_id;
            Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

            try {
                Response<IpmaCityForecast> apiResponse = callSync.execute();
                IpmaCityForecast forecast = apiResponse.body();

                if (forecast != null) {
                    var firstDay = forecast.getData().listIterator().next();

                    System.out.printf( "The temperature for %s will oscilate between %4.1f and %4.1f. %n",
                            firstDay.getForecastDate(),
                            Double.parseDouble(firstDay.getTMin()),
                            Double.parseDouble(firstDay.getTMax()));

                    System.out.printf( "Today we predict a precipitation of %s %% and the wind speed will be around %1d km/h.\n",
                            firstDay.getPrecipitaProb(), firstDay.getClassWindSpeed());
                } else {
                    System.out.println( "No results for this request!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            input.close();
        }
        else{
            System.out.println("Please enter the city code as a parameter!");
            System.exit(0);
        }


    }
}