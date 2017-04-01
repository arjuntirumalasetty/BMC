package restEndPoints;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import businessojects.Coach;

/**
 * Created by dgup27 on 3/30/2017.
 */

class RestCoachExecutor extends AsyncTask<Void, Void, Coach> {

    private String endPoint;

    private static final String restURI = "http://192.168.1.3:8080/coach/";

    private Coach coach;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    protected Coach doInBackground(Void... params) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Coach result = restTemplate.postForObject(restURI + getEndPoint(), coach, Coach.class);
        return result;
    }

}
