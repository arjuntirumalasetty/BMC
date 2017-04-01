package adapters;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


import com.example.bmc.R;
import com.rey.material.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

import customfonts.MyTextView;

/**
 * Created by Arjun on 3/18/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    List<String> parentList;
    Map<String, List<String>> childCollection;
    public ExpandableListAdapter(Activity context, List<String> parentList, Map<String, List<String>> childCollection){
        this.context = context;
        this.parentList=parentList;
        this.childCollection = childCollection;

    }
    @Override
    public int getGroupCount() {
        Log.i("Group Count ",parentList.size()+"");
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("child Count ",childCollection.get(parentList.get(groupPosition)).size()+"");
        return childCollection.get(parentList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.i("Group == ",parentList.get(groupPosition));
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.i("Child == ",childCollection.get(parentList.get(groupPosition)).get(childPosition));
        return childCollection.get(parentList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.i("Group Position ",groupPosition+"");
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.i("Child position ",childPosition+"");
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String stadiumName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.stadium_details,null);
        }
        MyTextView textView = (MyTextView) convertView.findViewById(R.id.stadium_parent_name);
        textView.setText(stadiumName);
        //textView.setTextSize();
        Log.i("View details ",stadiumName);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        try {
            String childData = (String) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.stadium_details_view, null);
            }
            MyTextView textView = (MyTextView)convertView.findViewById(R.id.stadium_details);
            Log.i("Text tag ", childData);
            if (textView != null)
                textView.setText(getChildData(childPosition,childData));
        }catch(Exception e){
            Log.e("Ex In getChildView ",e.getMessage());
        }
        return convertView;
    }

    private String getChildData(int childPosition, String childData) {
        try{
            switch (childPosition){
                case 0 : Log.i("Tag ID == ","stadium_name");return "Stadium Name \t"+childData;
                case 1 : Log.i("Tag ID == ","no_of_courts");return "No of courts \t"+childData;
                case 2 : Log.i("Tag ID == ","sport_Name");return "Sport Name \t"+childData;
                case 3 : Log.i("Tag ID == ","Timings");return "Timings \t"+childData;
            }
        }catch (Exception e){
            Log.e("Exception while ", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
