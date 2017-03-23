package com.example.bmc;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRequestTask extends AsyncTask<Void, Void, Coach> {
        @Override
        protected Coach doInBackground(Void... params) {
            Log.i("Is it called", "Yes WTF");
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Coach coach = new Coach();
                coach.setName("arjun");
                coach.setEmail("arjun");
                coach.setMobile("arjun");
                Coach coachResponseEntity = restTemplate.postForObject("http://10.0.2.2:8080/play/register", coach, Coach.class);
                Log.i("MainActivity", coachResponseEntity.getName().toString());
                return coachResponseEntity;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
}