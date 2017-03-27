package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bmc.MapsActivity;
import com.example.bmc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arjun on 3/23/2017.
 */

public class AddStadiumFragment extends Fragment {
    @Bind(R.id.btn_tap_to_pin)
    Button pinMyLocation_Btn;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_stadium_fragment,container,false);
        ButterKnife.bind(this,view);
        pinMyLocation_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(getActivity(), MapsActivity.class);
                mapView.putExtra("ENABLE_PIN","enable");
                getActivity().startActivity(mapView);
            }
        });

        return view;
    }
    
}
