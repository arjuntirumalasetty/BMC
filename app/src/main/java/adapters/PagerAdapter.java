package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.AddStadiumFragment;
import fragments.UpdateStadiumFragment;

/**
 * Created by Arjun on 3/23/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AddStadiumFragment();
            case 1:
                return new UpdateStadiumFragment();
            default:break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
