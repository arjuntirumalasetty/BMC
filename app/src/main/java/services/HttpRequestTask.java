package services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import businessojects.Coach;

public class HttpRequestTask extends AsyncTask<Void, Void, Coach> {
    private RestTemplate restTemplate = new RestTemplate();
    private static String reqestURL;
    private static Coach coach;

    @Override
        protected Coach doInBackground(Void... params) {
            Log.i("Is it called", "Yes WTF");
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Coach coachResponseEntity = restTemplate.postForObject("http://10.0.2.2:8080/play/"+reqestURL, coach, Coach.class);
                Log.i("HttpRequestTask", coachResponseEntity.getId().toString());
                return coachResponseEntity;
            } catch (Exception e) {
                Log.e("HttpRequestTask", e.getMessage(), e);
            }

            return null;
        }

    public static String getReqestURL() {
        return reqestURL;
    }

    public static void setReqestURL(String reqestURL) {
        HttpRequestTask.reqestURL = reqestURL;
    }

    public static Coach getCoach() {
        return coach;
    }

    public static void setCoach(Coach coach) {
        HttpRequestTask.coach = coach;
    }

}