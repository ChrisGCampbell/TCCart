package edu.gatech.seclass.tccart;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Omega on 3/26/2016.
 * Package: edu.gatech.seclass.tccart
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter{
    private Context h_context;
    private List<String> list_data_header;  // For header titles
    private HashMap<String, List<String>> list_data_child;

    public ExpandableListAdapter(Context c, List<String> list_dh, HashMap<String, List<String>> list_dc)
    {
        this.h_context = c;
        this.list_data_header = list_dh;
        this.list_data_child = list_dc;
    }

    @Override
    public Object getChild(int group_pos, int child_pos)
    {
        return this.list_data_child.get(this.list_data_header.get(group_pos)).get(child_pos);
    }

    @Override
    public long getChildId(int group_pos, int child_pos)
    {
        return child_pos;
    }

    @Override
    public View getChildView(int group_pos, final int child_pos, boolean is_last_child, View convert_view, ViewGroup parent)
    {
        final String child_text = (String)getChild(group_pos, child_pos);

        if(convert_view == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.h_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = inflater.inflate(R.layout.list_item, null);
        }

        TextView txt_list_child = (TextView) convert_view.findViewById(R.id.label_ListItem);

        txt_list_child.setText(child_text);
        return convert_view;
    }

    @Override
    public int getChildrenCount(int group_pos)
    {
        return this.list_data_child.get(this.list_data_header.get(group_pos)).size();
    }

    @Override
    public Object getGroup(int group_pos)
    {
        return this.list_data_header.get(group_pos);
    }

    @Override
    public int getGroupCount()
    {
        return this.list_data_header.size();
    }

    @Override
    public long getGroupId(int group_pos)
    {
        return group_pos;
    }

    @Override
    public View getGroupView(int group_pos, boolean is_expanded, View convert_view, ViewGroup parent)
    {
        String header_title = (String) getGroup(group_pos);
        if(convert_view == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.h_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = inflater.inflate(R.layout.list_group, null);
        }

        TextView label_list_header = (TextView) convert_view.findViewById(R.id.label_ListHeader);
        label_list_header.setTypeface(null, Typeface.BOLD);
        label_list_header.setText(header_title);

        return convert_view;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int group_pos, int child_pos)
    {
        return true;
    }
}
