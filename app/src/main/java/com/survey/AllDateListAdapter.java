package com.survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Purnendu on 7/1/2017.
 */

public class AllDateListAdapter extends RecyclerView.Adapter<AllDateListAdapter.ViewHolder> {


    private Context mContext;
    private List<Long> mList;
    private String mSelectedDate;

    public AllDateListAdapter(Context context, List<Long> list,String selecteddate) {
        this.mContext = context;
        this.mList = list;
        this.mSelectedDate=selecteddate;
    }


    @Override
    public AllDateListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_get_all_date_list, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new AllDateListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final AllDateListAdapter.ViewHolder holder, int position) {
        final Long data=mList.get(position);
        FontTypeFace.setRobotoThinTypeFace(mContext,holder.text_date2,holder.text_date1,holder.text_date);
        String date = DateUtils.getdateFromnumberofDays(data);
            if (date != null) {
                // FontTypeFace.setRobotoMediumTypeFace(mContext,holder.prod_text_target_head,holder.prod_text_day_head,holder.prod_text_mtd_head,holder.prod_text_ytd_head,holder.pe_text_target_head,holder.pe_text_day_head,holder.pe_text_mtd_head,holder.pe_text_ytd_head,holder.me_text_target_head,holder.me_text_day_head,holder.me_text_mtd_head,holder.me_text_ytd_head);
                holder.text_date1.setText(getDay(date));
                holder.text_date.setText(getMonth(date));
                holder.text_date2.setText(getYear(date));
                holder.parentRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(mContext,MainActivity.class);
                        i.putExtra("date", data+"");

                        mContext.startActivity(i);
                    }
                });
            } else {
                holder.parentRL.setVisibility(View.GONE);
            }





    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }


        TextView text_date = (TextView) itemView.findViewById(R.id.text_date);
        TextView text_date1 = (TextView) itemView.findViewById(R.id.text_date1);
        TextView text_date2 = (TextView) itemView.findViewById(R.id.text_date2);
        LinearLayout parentRL=(LinearLayout)itemView.findViewById(R.id.parentRL);



    }
    private boolean isEmpty(String data){
        if(data.equalsIgnoreCase("0")||data.equalsIgnoreCase("")||data.equalsIgnoreCase("DATEVAL")){
            return true;
        }
        else{
            return false;
        }
    }
    private String getDay(String date){
        String[] day=date.split(" ");
        return day[0];

    }
    private String getMonth(String date){
        String month="";
        String[] day=date.split(" ");
        if(day[1].equalsIgnoreCase("1")||day[1].equalsIgnoreCase("01")){
            month="JAN";
        }
        else if(day[1].equalsIgnoreCase("2")||day[1].equalsIgnoreCase("02")){
            month="FEB";
        }
        else if(day[1].equalsIgnoreCase("3")||day[1].equalsIgnoreCase("03")){
            month="MAR";
        }
        else if(day[1].equalsIgnoreCase("4")||day[1].equalsIgnoreCase("04")){
            month="APR";
        }
        else if(day[1].equalsIgnoreCase("5")||day[1].equalsIgnoreCase("05")){
            month="MAY";
        }
        else if(day[1].equalsIgnoreCase("6")||day[1].equalsIgnoreCase("06")){
            month="JUN";
        }
        else if(day[1].equalsIgnoreCase("7")||day[1].equalsIgnoreCase("07")){
            month="JUL";
        }
        else if(day[1].equalsIgnoreCase("8")||day[1].equalsIgnoreCase("08")){
            month="AUG";
        }
        else if(day[1].equalsIgnoreCase("9")||day[1].equalsIgnoreCase("09")){
            month="SEP";
        }
        else if(day[1].equalsIgnoreCase("10")){
            month="OCT";
        }
        else if(day[1].equalsIgnoreCase("11")){
            month="NOV";
        }
        else if(day[1].equalsIgnoreCase("12")){
            month="DEC";
        }
        return month;

    }
    private String getYear(String date){
        String[] day=date.split(" ");
        return day[2];

    }
}
