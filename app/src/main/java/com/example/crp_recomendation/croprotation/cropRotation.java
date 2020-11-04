package com.example.crp_recomendation.croprotation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crp_recomendation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class cropRotation extends Fragment {

    FirebaseFirestore objectFirebaseFirestore;
    DocumentReference objectDocumentReference;
    Button btnGetCrop;

    TextInputEditText tieCropRotate;
    TextView tvDisplayCropRotate;
    View view;

    boolean i = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crop_rotation,container,false);
        objectFirebaseFirestore = FirebaseFirestore.getInstance();
        tieCropRotate =  view.findViewById(R.id.CropRotat);
        tvDisplayCropRotate = view.findViewById(R.id.displaycroprotat);
        btnGetCrop = view.findViewById(R.id.btnGetCroprotat);


        btnGetCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCropRotateData(v);
            }
        });

        return view;
    }


    public void getCropRotateData(View view){
        try {
            if(!tieCropRotate.getText().toString().isEmpty()) {
                objectDocumentReference = objectFirebaseFirestore.collection("Crop_list").document(
                        tieCropRotate.getText().toString()
                );

                objectDocumentReference.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                final String Soil = documentSnapshot.getString("Soil_id");


                                    objectFirebaseFirestore.collection("Crop_list")
                                            .whereEqualTo("Soil_id",Soil)
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                @Override
                                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                    List<DocumentSnapshot> doc=  queryDocumentSnapshots.getDocuments();

                                                    int size = doc.size();

                                                    for(int i=0;i<size;i++){
                                                        String Crop_name = doc.get(i).getId();

                                                        if(!(tieCropRotate.getText().toString().equals(Crop_name))){
                                                            String Crop_type = doc.get(i).getString("Crop_type");
                                                            String Crop_term = doc.get(i).getString("Crop_term");

                                                            String Crop_price = doc.get(i).getString("crop_prices");
                                                            String srr = doc.get(i).getString("srr");
                                                            String syr = doc.get(i).getString("syr");

                                                            tvDisplayCropRotate.setText(
                                                                    "Crop Name : " + Crop_name + "\n" +
                                                                            "Term :  " + Crop_term + "\n" +
                                                                            "Type :  " + Crop_type + "\n" +
                                                                            "Buying Price :  " + Crop_price + "\n"+
                                                                            "Seed Rate Ratio :  " + srr + "\n"+
                                                                            "Seed Yield Ratio :  " + syr + "\n"
                                                            );
                                                            break;
                                                        }
                                                    }

                                                }
                                            });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Google Firestore Error" , Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                Toast.makeText(getActivity(),"કૃપા કરીને પાકનું નામ દાખલ કરો",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}

