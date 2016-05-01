package com.example.android.movieapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mohamed Ramadan on 4/21/2016.
 */
public class MovieExtandableAdapter extends BaseExpandableListAdapter implements Serializable {

    Context context;
    ArrayList<String> dataHeaders;
    HashMap<String ,ArrayList<String>> dataItems;

    public MovieExtandableAdapter(Context context, ArrayList<String> dataHeaders,
                                  HashMap<String, ArrayList<String>> dataItems) {
        this.context = context;
        this.dataHeaders = dataHeaders;
        this.dataItems = dataItems;
    }

    public void setDataHeaders(ArrayList<String> dataHeaders) {
        this.dataHeaders = dataHeaders;
    }

    public void setDataItems(HashMap<String, ArrayList<String>> dataItems) {
        this.dataItems = dataItems;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return dataHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataItems.get(dataHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataItems.get(dataHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String parentData=(String)getGroup(groupPosition);
        if(convertView==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.movie_parentlayout,parent,false);

        }


        TextView textView= (TextView)convertView.findViewById(R.id.parent_text);

        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(parentData);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           String childData= (String) getChild(groupPosition,childPosition);

        if(convertView==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

           convertView=inflater.inflate(R.layout.movie_childlayout,parent,false);

        }

        TextView textView= (TextView)convertView.findViewById(R.id.child_text);

        textView.setText(childData);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

   if( dataHeaders.get(groupPosition).equals("Trailers")   )
        return true;
        return false ;
    }
}
