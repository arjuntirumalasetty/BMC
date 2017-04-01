package viewholder;

import android.content.Context;
import android.support.constraint.solver.Cache;
import android.util.Log;

import java.io.IOException;

import businessojects.Coach;
import cache.UICacheImpl;
import cache.UiCache;

/**
 * Created by Arjun on 4/1/2017.
 */

public class Application extends android.app.Application {
    private static Context context;

    @Override

    public void onCreate(){
        super.onCreate();
        Application.context = this;
        initCache();
    }

    private void initCache() {
        boolean isPresentInServer = false;
        UICacheImpl cache = UICacheImpl.getInstance(this);
        try {
            cache.readCaoch(this);
            if(!cache.isCoachExistInCache){
                //getItFromServer
                if(isPresentInServer){
                    cache.writeCoach(this,new Coach());
                }
            }
        } catch (IOException e) {
            Log.e("Exception in intiCache",e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e("Exception in intiCache",e.getMessage());
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }

}
