package com.example.kupa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;
    Adapter(List<ModelClass> userList){this.userList=userList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource=userList.get(position).getImagefood();
        String name=userList.get(position).getTextname();
        int count=userList.get(position).getTextcount();
        int amount=userList.get(position).getTextamount();
       // int sausecoun=userList.get(position).getSausecount();
        int sause=userList.get(position).getSauseamount();
       // int toppcoun=userList.get(position).getToppingcount();
        int topping=userList.get(position).getToppingamount();
        int totalcart=userList.get(position).getTotalcart();
        int delete=userList.get(position).getDelete();

        holder.delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove item from the data set
                userList.remove(position);
                // Notify adapter about item removal
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, userList.size());
                Toast.makeText(v.getContext(), "Removed from Cart", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setData(resource,name,count,amount,sause,topping,totalcart,delete);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageview1;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
     //   private TextView textView4;
        private TextView textView5;
      //  private TextView textView6;
        private TextView textView7;
        private TextView textView8;
        private ImageView delete1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview1=itemView.findViewById(R.id.imagefood);
            textView1=itemView.findViewById(R.id.textname);
            textView2=itemView.findViewById(R.id.textcount);
            textView3=itemView.findViewById(R.id.textamount);
          //  textView4=itemView.findViewById(R.id.sausecount);
            textView5=itemView.findViewById(R.id.sauseamount);
          //  textView6=itemView.findViewById(R.id.toppingcount);
            textView7=itemView.findViewById(R.id.toppingamount);
            textView8=itemView.findViewById(R.id.totalcart);
            delete1=itemView.findViewById(R.id.delete);

        }
        public void setData(int resource, String name, int count, int amount,int sause,int topping,int totalcart,int delete) {

            imageview1.setImageResource(resource);
            textView1.setText(name);
            textView2.setText(""+count);
            textView3.setText(""+amount);
         //   textView4.setText(""+sausecoun);
            textView5.setText(""+sause);
          //  textView6.setText(""+toppcoun);
            textView7.setText(""+topping);
            textView8.setText(""+totalcart);
            delete1.setImageResource(delete);

        }
    }
}
