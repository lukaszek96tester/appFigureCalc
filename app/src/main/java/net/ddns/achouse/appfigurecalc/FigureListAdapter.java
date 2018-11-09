package net.ddns.achouse.appfigurecalc;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class FigureListAdapter extends BaseAdapter {
    private ArrayList<Figure> listData;
    private LayoutInflater layoutInflater;
    public FigureListAdapter(Context aContext, ArrayList<Figure> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.table_row, null);
            holder = new ViewHolder();
            holder.uType = (ImageView) v.findViewById(R.id.type);
            holder.uArea = (TextView) v.findViewById(R.id.area);
            holder.uPerimeter = (TextView) v.findViewById(R.id.perimeter);
            holder.uLinearDimension = (TextView) v.findViewById(R.id.linearDimension);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        switch(listData.get(position).getType())
        {
            case "Square":
                holder.uType.setImageResource(R.drawable.icons8_square);
                break;
            case "Equilateral Triangle":
                holder.uType.setImageResource(R.drawable.icons8_triangle);
                break;
            case "Circle":
                holder.uType.setImageResource(R.drawable.icons8_circle);
                break;
        }
        holder.uLinearDimension.setText(String.valueOf(listData.get(position).getLinearDimension()));
        holder.uArea.setText(String.valueOf(listData.get(position).getArea()));
        holder.uPerimeter.setText(String.valueOf(listData.get(position).getPerimeter()));
        return v;
    }
    static class ViewHolder {
        ImageView uType;
        TextView uLinearDimension;
        TextView uArea;
        TextView uPerimeter;
    }
}
