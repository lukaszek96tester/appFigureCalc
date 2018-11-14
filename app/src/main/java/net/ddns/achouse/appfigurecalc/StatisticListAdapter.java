package net.ddns.achouse.appfigurecalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticListAdapter extends BaseAdapter {
    private ArrayList<Statistic> listData;
    private LayoutInflater layoutInflater;
    public StatisticListAdapter(Context aContext, ArrayList<Statistic> listData) {
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
        StatisticListAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.table_row_statistics, null);
            holder = new StatisticListAdapter.ViewHolder();
            holder.uType = (ImageView) v.findViewById(R.id.type);
            holder.uNumber = (TextView) v.findViewById(R.id.number);
            holder.uAverageArea = (TextView) v.findViewById(R.id.averageArea);
            holder.uAveragePerimeter = (TextView) v.findViewById(R.id.averagePerimeter);
            v.setTag(holder);
        } else {
            holder = (StatisticListAdapter.ViewHolder) v.getTag();
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

//        String NumberFixed = String.valueOf(listData.get(position).getNumber());
//        NumberFixed = (NumberFixed + "000000").substring(0, 5);
//        holder.uNumber.setText(NumberFixed);

        String AverageAreaFixed = String.valueOf(listData.get(position).getAverageArea());
        AverageAreaFixed = (AverageAreaFixed + "000000").substring(0, 5);
        holder.uAverageArea.setText(AverageAreaFixed);

        String AveragePerimeterFixed = String.valueOf(listData.get(position).getAveragePerimeter());
        AveragePerimeterFixed = (AveragePerimeterFixed + "000000").substring(0, 5);
        holder.uAveragePerimeter.setText(AveragePerimeterFixed);


        holder.uNumber.setText(String.valueOf((int)listData.get(position).getNumber()));
//        holder.uAverageArea.setText(String.valueOf(listData.get(position).getAverageArea()));
//        holder.uAveragePerimeter.setText(String.valueOf(listData.get(position).getAveragePerimeter()));
        return v;
    }
    static class ViewHolder {
        ImageView uType;
        TextView uNumber;
        TextView uAverageArea;
        TextView uAveragePerimeter;
    }
}
