package com.survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.survey.service.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purnendu on 22-02-2017.
 */

public class JobListingAdapter extends RecyclerView.Adapter<JobListingAdapter.ViewHolder> {


    private Context mContext;
    private List<Datum> mList;
    private String mSelectedDate;

    public JobListingAdapter(Context context, List<Datum> list,String selecteddate) {
        this.mContext = context;
        this.mList = list;
        this.mSelectedDate=selecteddate;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_joblisting_row1, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new JobListingAdapter.ViewHolder(view);
    }

    private void setvisibilityaccordingdata(String data,LinearLayout view,TextView mTxtView){
        if(isEmpty(data)){
            view.setVisibility(View.GONE);
        }
        else{
            mTxtView.setText(data);
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Datum data=mList.get(position);
        FontTypeFace.setRobotoThinTypeFace(mContext,holder.prod_text_target,holder.prod_text_ytd,holder.prod_text_mtd,holder.prod_text_day,holder.pe_text_target,holder.pe_text_ytd,holder.pe_text_mtd,holder.pe_data_day,holder.me_text_target,holder.me_text_ytd,holder.me_text_mtd,holder.me_text_day,holder.text_date2,holder.text_date1,holder.text_date,holder.text_line_name,holder.text_item_name, holder.text_user_name);
        FontTypeFace.setRobotoMediumTypeFace(mContext,holder.prod_text_target_head,holder.prod_text_day_head,holder.prod_text_mtd_head,holder.prod_text_ytd_head,holder.pe_text_target_head,holder.pe_text_day_head,holder.pe_text_mtd_head,holder.pe_text_ytd_head,holder.me_text_target_head,holder.me_text_day_head,holder.me_text_mtd_head,holder.me_text_ytd_head);

         holder.text_date1.setText(getDay(mSelectedDate));
        holder.text_date.setText(getMonth(mSelectedDate));
        holder.text_date2.setText(getYear(mSelectedDate));
          holder.text_line_name.setText(data.getLINENAME());
          holder.text_item_name.setText(data.getITEMNAME());
           holder.text_user_name.setText(data.getUSERNAME());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
               if(holder.detail.getVisibility()==View.VISIBLE){
                   //holder.detail.setVisibility(View.GONE);
                   ExpandCollapseView.collapse(holder.detail);
               }
                else if(holder.detail.getVisibility()==View.GONE){
                   //holder.detail.setVisibility(View.VISIBLE);
                   ExpandCollapseView.expand(holder.detail);
                }
            }
        });

        if(isEmpty(data.getMED())&&isEmpty(data.getMEM())&&isEmpty(data.getMEY())&&isEmpty(data.getTARGETME())){
            holder.me_id.setVisibility(View.GONE);
        }
        else{
            setvisibilityaccordingdata(data.getMED(),holder.me_data_day,holder.me_text_day);
            setvisibilityaccordingdata(data.getMEM(),holder.me_data_mtd,holder.me_text_mtd);
            setvisibilityaccordingdata(data.getMEY(),holder.me_data_ytd,holder.me_text_ytd);
            setvisibilityaccordingdata(data.getTARGETME(),holder.me_data_target,holder.me_text_target);
        }
        if(isEmpty(data.getPED())&&isEmpty(data.getPEM())&&isEmpty(data.getPEY())&&isEmpty(data.getTARGETPE())){
            holder.pe_id.setVisibility(View.GONE);
        }
        else{

            setvisibilityaccordingdata(data.getPED(),holder.pe_data_day,holder.pe_text_day);
            setvisibilityaccordingdata(data.getPEM(),holder.pe_data_mtd,holder.pe_text_mtd);
            setvisibilityaccordingdata(data.getPEY(),holder.pe_data_ytd,holder.pe_text_ytd);
            setvisibilityaccordingdata(data.getTARGETPE(),holder.pe_data_target,holder.pe_text_target);

        }
        if(isEmpty(data.getPRD())&&isEmpty(data.getPRM())&&isEmpty(data.getPRY())&&isEmpty(data.getTARGETPR())){
            holder.prod_id.setVisibility(View.GONE);
        }
        else{

            setvisibilityaccordingdata(data.getPRD(),holder.prod_data_day,holder.prod_text_day);
            setvisibilityaccordingdata(data.getPRM(),holder.prod_data_mtd,holder.prod_text_mtd);
            setvisibilityaccordingdata(data.getPRY(),holder.prod_data_ytd,holder.prod_text_ytd);
            setvisibilityaccordingdata(data.getTARGETPR(),holder.prod_data_target,holder.prod_text_target);

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
        LinearLayout me_id=(LinearLayout)itemView.findViewById(R.id.me_id);
        LinearLayout pe_id=(LinearLayout)itemView.findViewById(R.id.pe_id);
        LinearLayout prod_id=(LinearLayout)itemView.findViewById(R.id.prod_id);


        LinearLayout me_data_target=(LinearLayout)itemView.findViewById(R.id.me_data_target);
        LinearLayout pe_data_target=(LinearLayout)itemView.findViewById(R.id.pe_data_target);
        LinearLayout prod_data_target=(LinearLayout)itemView.findViewById(R.id.prod_data_target);

        LinearLayout me_data_day=(LinearLayout)itemView.findViewById(R.id.me_data_day);
        LinearLayout pe_data_day=(LinearLayout)itemView.findViewById(R.id.pe_data_day);
        LinearLayout prod_data_day=(LinearLayout)itemView.findViewById(R.id.prod_data_day);

        LinearLayout me_data_mtd=(LinearLayout)itemView.findViewById(R.id.me_data_mtd);
        LinearLayout pe_data_mtd=(LinearLayout)itemView.findViewById(R.id.pe_data_mtd);
        LinearLayout prod_data_mtd=(LinearLayout)itemView.findViewById(R.id.prod_data_mtd);

        LinearLayout me_data_ytd=(LinearLayout)itemView.findViewById(R.id.me_data_ytd);
        LinearLayout pe_data_ytd=(LinearLayout)itemView.findViewById(R.id.pe_data_ytd);
        LinearLayout prod_data_ytd=(LinearLayout)itemView.findViewById(R.id.prod_data_ytd);

        TextView me_text_target = (TextView) itemView.findViewById(R.id.me_text_target);
        TextView me_text_day = (TextView) itemView.findViewById(R.id.me_text_day);
        TextView me_text_mtd = (TextView) itemView.findViewById(R.id.me_text_mtd);
        TextView me_text_ytd = (TextView) itemView.findViewById(R.id.me_text_ytd);

        TextView pe_text_target = (TextView) itemView.findViewById(R.id.pe_text_target);
        TextView pe_text_day = (TextView) itemView.findViewById(R.id.pe_text_day);
        TextView pe_text_mtd = (TextView) itemView.findViewById(R.id.pe_text_mtd);
        TextView pe_text_ytd = (TextView) itemView.findViewById(R.id.pe_text_ytd);

        TextView prod_text_target = (TextView) itemView.findViewById(R.id.prod_text_target);
        TextView prod_text_day = (TextView) itemView.findViewById(R.id.prod_text_day);
        TextView prod_text_mtd = (TextView) itemView.findViewById(R.id.prod_text_mtd);
        TextView prod_text_ytd = (TextView) itemView.findViewById(R.id.prod_text_ytd);

        TextView text_date = (TextView) itemView.findViewById(R.id.text_date);
        TextView text_line_name = (TextView) itemView.findViewById(R.id.text_line_name);
        TextView text_item_name = (TextView) itemView.findViewById(R.id.text_item_name);
        TextView text_user_name = (TextView) itemView.findViewById(R.id.text_user_name);

        TextView text_date1 = (TextView) itemView.findViewById(R.id.text_date1);
        TextView text_date2 = (TextView) itemView.findViewById(R.id.text_date2);
        LinearLayout btn=(LinearLayout)itemView.findViewById(R.id.main);
        LinearLayout detail=(LinearLayout)itemView.findViewById(R.id.details);

        TextView me_text_target_head=(TextView)itemView.findViewById(R.id.me_text_target_head);
        TextView me_text_day_head=(TextView)itemView.findViewById(R.id.me_text_day_head);
        TextView me_text_mtd_head=(TextView)itemView.findViewById(R.id.me_text_mtd_head);
        TextView me_text_ytd_head=(TextView)itemView.findViewById(R.id.me_text_ytd_head);

        TextView pe_text_target_head=(TextView)itemView.findViewById(R.id.pe_text_target_head);
        TextView pe_text_day_head=(TextView)itemView.findViewById(R.id.pe_text_day_head);
        TextView pe_text_mtd_head=(TextView)itemView.findViewById(R.id.pe_text_mtd_head);
        TextView pe_text_ytd_head=(TextView)itemView.findViewById(R.id.pe_text_ytd_head);

        TextView prod_text_target_head=(TextView)itemView.findViewById(R.id.prod_text_target_head);
        TextView prod_text_day_head=(TextView)itemView.findViewById(R.id.prod_text_day_head);
        TextView prod_text_mtd_head=(TextView)itemView.findViewById(R.id.prod_text_mtd_head);
        TextView prod_text_ytd_head=(TextView)itemView.findViewById(R.id.prod_text_ytd_head);


    }
private boolean isEmpty(String data){
    if(data.equalsIgnoreCase("0")||data.equalsIgnoreCase("")){
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
