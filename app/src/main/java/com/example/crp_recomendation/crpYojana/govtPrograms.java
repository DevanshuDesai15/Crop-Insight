package com.example.crp_recomendation.crpYojana;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crp_recomendation.R;
import com.example.crp_recomendation.crpYojana.model.YojanaData;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class govtPrograms extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference yojanaRef = db.collection("Yojana_list");

    private YojanaAdapter adapter;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_govt_programs,container,false);

        Query query = yojanaRef.orderBy("NameEng",Query.Direction.ASCENDING);


        FirestoreRecyclerOptions<YojanaData> options = new FirestoreRecyclerOptions.Builder<YojanaData>()
                .setQuery(query,YojanaData.class)
                .build();

        adapter= new YojanaAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.yojanaList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListner(new YojanaAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(DocumentSnapshot snapshot, int position) {
                YojanaData yojana = snapshot.toObject(YojanaData.class);

                String id = snapshot.getId();

                String descEng = snapshot.get("DescEng").toString();
                String descGuj = snapshot.get("DescGuj").toString();
                String link = snapshot.get("Link").toString();

                Intent i = new Intent(getActivity(),YojanaDetail.class);
                Bundle yojanaBundle = new Bundle();
                yojanaBundle.putString("descEng",descEng);
                yojanaBundle.putString("descGuj",descGuj);
                yojanaBundle.putString("link",link);
                i.putExtras(yojanaBundle);
                startActivity(i);
            }
        });


        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
