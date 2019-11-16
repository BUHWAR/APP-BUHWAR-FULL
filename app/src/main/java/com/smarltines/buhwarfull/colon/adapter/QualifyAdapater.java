package com.smarltines.buhwarfull.colon.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.activity.QualifyActivity;
import com.smarltines.buhwarfull.model.GuardModel;

import java.util.List;

public class QualifyAdapater extends RecyclerView.Adapter<QualifyAdapater.CustomViewHolder>{
    private List<GuardModel> dataList;
    private Activity context;

    public QualifyAdapater(List<GuardModel> dataList,Activity context) {
        this.dataList = dataList;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

//Get a reference to the Views in our layout//

        public final View myView;
        TextView txtName;
        TextView txtPhone;
        TextView txtEmail;
        TextView txtDesc;
        ImageView img;
        RelativeLayout relativeLayout;
        CardView cardView;
        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            txtName = myView.findViewById(R.id.tvNameGuard_list);
            txtPhone = myView.findViewById(R.id.tvNNumberGuard_list);
            txtEmail = myView.findViewById(R.id.tvEmailGuard_list);
            txtDesc = myView.findViewById(R.id.tvDescriptionGuard_list);
            img = myView.findViewById(R.id.imgGuard_list);
            cardView = myView.findViewById(R.id.cardQualify);
        }
    }

    @Override

//Construct a RecyclerView.ViewHolder//

    public QualifyAdapater.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_guard, parent, false);
        return new QualifyAdapater.CustomViewHolder(view);
    }

    @Override

//Set the data//

    public void onBindViewHolder(QualifyAdapater.CustomViewHolder holder, final int position) {
        holder.txtName.setText(dataList.get(position).getNameGuard());
        holder.txtPhone.setText(dataList.get(position).getNumberGuard());
        holder.txtEmail.setText(dataList.get(position).getEmailGuard());
        holder.txtDesc.setText(dataList.get(position).getDescriptionGuard());

        CardView mcardView = holder.cardView;
        mcardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QualifyActivity.class);
                context.startActivity(intent);
            }
        });


    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
