package com.smarltines.buhwarfull.colon.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.activity.detailGuard.ui.DetailGuardActivity;
import com.smarltines.buhwarfull.model.RegisterModel;

import java.util.List;

public class RegisterAdapater extends RecyclerView.Adapter<RegisterAdapater.CustomViewHolder>{
    private List<RegisterModel> dataList;
    private Activity context;

    public RegisterAdapater(List<RegisterModel> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

//Get a reference to the Views in our layout//

        public final View myView;
        TextView txtDay;
        TextView txtDesc;

        CardView cardView;
        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            txtDay = myView.findViewById(R.id.tvDay_list);
            txtDesc = myView.findViewById(R.id.tvDescription_list);
            cardView = myView.findViewById(R.id.cardRegister);
        }
    }

    @Override

//Construct a RecyclerView.ViewHolder//

    public RegisterAdapater.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_register, parent, false);
        return new RegisterAdapater.CustomViewHolder(view);
    }

    @Override

//Set the data//

    public void onBindViewHolder(RegisterAdapater.CustomViewHolder holder, final int position) {
        holder.txtDay.setText(dataList.get(position).getDay());

        holder.txtDesc.setText(dataList.get(position).getDescription());

        CardView mcardView = holder.cardView;
        mcardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(context, DetailGuardActivity.class);
                //context.startActivity(intent);
            }
        });


    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
