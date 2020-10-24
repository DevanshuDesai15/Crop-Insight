package com.example.crp_recomendation.crpYojana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crp_recomendation.R;
import com.example.crp_recomendation.crpYojana.model.YojanaData;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

class YojanaAdapter extends FirestoreRecyclerAdapter<YojanaData, YojanaAdapter.YojanaHolder> {

    private OnItemClickListner listner;

    public YojanaAdapter(@NonNull FirestoreRecyclerOptions<YojanaData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull YojanaHolder yojanaHolder, int i, @NonNull YojanaData yojanaData) {
        yojanaHolder.titleEng.setText(yojanaData.getNameEng());
        yojanaHolder.titleGuj.setText(yojanaData.getNameGuj());
    }

    @NonNull
    @Override
    public YojanaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yojana,parent,false);
        return new YojanaHolder(v);
    }

    class YojanaHolder extends RecyclerView.ViewHolder{

        TextView titleEng;
        TextView titleGuj;


        public YojanaHolder(@NonNull View itemView) {
            super(itemView);
            titleEng = itemView.findViewById(R.id.title_eng);
            titleGuj = itemView.findViewById(R.id.title_guj);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION && listner!=null){
                        listner.onItemClick(getSnapshots().getSnapshot(position),position);
                    }

                }
            });
        }
    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot snapshot, int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){

        this.listner = listner;
    }
}
