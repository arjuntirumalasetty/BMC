package cache;

import android.content.Context;

import com.example.bmc.globalvariable.GlobalClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import businessojects.Coach;
import businessojects.CoachDetails;
import businessojects.StadiumDetails;
/**
 * Created by Arjun on 3/18/2017.
 */

public class UICacheImpl implements UiCache{
    private static final String COACH_DATA = "COACH";
    private static final String PLAYER_DATA = "PLAYER";
    public static boolean isCoachExistInCache = false;
    private static UICacheImpl instance = null;
    private static Coach coach;
    Calendar cal = Calendar.getInstance();
    int month;
    int year;
    private Context ctx;

    private UICacheImpl(Context context){
        ctx = context;
    }

    public static UICacheImpl getInstance(Context context){
        if(instance == null){
            synchronized (UICacheImpl.class){
                if(instance == null){
                    instance = new UICacheImpl(context);
                }
            }
        }
        return instance;
    }

    public Coach getCoach() {
        return coach;
    }

    public static void setCoach(Coach coach) {
        UICacheImpl.coach = coach;
    }

    public void writeCoach(Context context, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(COACH_DATA, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    @Override
    public void upDateCoachDetails(CoachDetails coachDetails) throws IOException {
        if(coach== null){
            coach = new Coach();
        }
        coach.setCoachDetails(coachDetails);
        writeCoach(GlobalClass.getContext(),coach);
    }

    public void readCaoch(Context context) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(COACH_DATA);
        if(fis !=null){
            ObjectInputStream ois = new ObjectInputStream(fis);
            coach = (Coach) ois.readObject();
            if(coach!= null){
                isCoachExistInCache = true;
            }
        }
    }

    @Override
    public CoachDetails getCoachDetails() throws IOException, ClassNotFoundException {
        CoachDetails coachDetails = null;
        if(coach==null){
       //     readCaoch(Application.getContext());
        }else{
            coachDetails = coach.getCoachDetails();
        }
        return coachDetails;
    }

    @Override
    public StadiumDetails getStadiumDetails() {
        return null;
    }

    @Override
    public String getUserEmail() {
        return null;
    }
}
