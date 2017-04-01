package services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import businessojects.Coach;

public class HttpRequestTask extends AsyncTask<Void, Void, Coach> {

    private static String reqestURL;
    private static Coach coach;

    @Override
        protected Coach doInBackground(Void... params) {
            Log.i("Is it called", "Yes WTF");
            try {

                final String uri = "http://10.0.2.2:8080/play/getCoach";

                Map<String, String> param = new HashMap<String, String>();
                param.put("emailID", "dravit.info@gmail.com");

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Coach coach = new Coach();
                coach.setLoginEmail("dravit.info@gmail.com");
                Coach result = restTemplate.postForObject(uri, coach, Coach.class);

                /*System.out.println(result);


                RestTemplate restTemplate = new RestTemplate();
                //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Coach coachEntity = restTemplate.getForObject("http://10.0.2.2:8080/play/getCoach/{emailID}", Coach.class,
                        new HashMap<String, String>().put("emailID", "dravit.info@gmail.com"));
               *//* Coach coachResponseEntity = restTemplate.postForObject("http://10.0.2.2:8080/play/"+reqestURL, coach, Coach.class);
                Log.i("HttpRequestTask", coachResponseEntity.getId().toString());*//*
                Log.i("coachEntity", ">>>>>>>>>"+coachEntity.getName());*/
                return result;
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