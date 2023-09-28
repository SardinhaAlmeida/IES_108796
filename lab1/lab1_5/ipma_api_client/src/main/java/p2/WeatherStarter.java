package p2;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    // todo: should generalize for a city passed as argument
    // private static final int CITY_ID_AVEIRO = 1010500;

    public static City getRandomCity() {
        // get a retrofit instance, loaded with the GSon lib to convert JSON into
        // objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint

        Call<IpmaCities> callSync = service.getRandom();

        try {
            Response<IpmaCities> apiResponse = callSync.execute();
            IpmaCities forecast = apiResponse.body();

            List<City> cities = forecast.getData();

            if (cities != null && !cities.isEmpty()) {
                // Get a random index to select a random city
                Random random = new Random();
                int randomIndex = random.nextInt(cities.size());

                City randomCity = cities.get(randomIndex);

                return randomCity;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public  static CityForecast getForecast(Integer CITY_ID) {

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint

        if(CITY_ID!=null){

            Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

            try {
                Response<IpmaCityForecast> apiResponse = callSync.execute();
                IpmaCityForecast forecast = apiResponse.body();
                
                return forecast.getData().get(0);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Please enter the city code as a parameter!");
            System.exit(0);
        }
        return null;
    }
}