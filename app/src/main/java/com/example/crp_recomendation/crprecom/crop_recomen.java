package com.example.crp_recomendation.crprecom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.crp_recomendation.CostStorage;
import com.example.crp_recomendation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class crop_recomen extends Fragment {

    FirebaseFirestore objectFirebaseFirestore;
    DocumentReference objectDocumentReference;
    Button btnGetCrop;
    Button btnToBudget;

    TextInputEditText tieCrop;
    TextView tvDisplayCrop;
    View view;
    String district;

    EditText edtSeedsBuy;

    String Crop_name="";
    String Crop_type="";
    String Crop_term="";
    String Crop_price="";
    String seedRateRatio="";
    String seedYieldRatio="";


    public crop_recomen(String disrict) {
        this.district=disrict;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_crop_recomen,container,false);
        objectFirebaseFirestore = FirebaseFirestore.getInstance();
        tieCrop =  view.findViewById(R.id.crop);
        //tieCrop = district;
        tvDisplayCrop = view.findViewById(R.id.displaycrop);
     //   tvDisplayCrop = district;
        btnGetCrop = view.findViewById(R.id.btnGetCrop);

        tieCrop.setText(district);
        btnGetCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCropData(v);
            }
        });


        btnToBudget = view.findViewById(R.id.btnGetBudget);
        btnToBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment budget = new budgetTracker();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                CostStorage.setBuy(Crop_price);
                CostStorage.setSrr(seedRateRatio);
                CostStorage.setSyr(seedYieldRatio+"00");


                transaction.replace(R.id.fragment_container,budget);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


    public void getCropData(View view){
        try {
            if(!tieCrop.getText().toString().isEmpty()) {
           // if(district!="/0"){
                objectDocumentReference = objectFirebaseFirestore.collection("District_list").document(
                        tieCrop.getText().toString()
                       // district
                );

                objectDocumentReference.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                final String Soil = documentSnapshot.getString("Soil_id");

                                objectFirebaseFirestore.collection("Crop_list")
                                        .whereEqualTo("Soil_id",Soil)
                                        .limit(2)
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                List<DocumentSnapshot> doc=  queryDocumentSnapshots.getDocuments();

                                                int size = doc.size();


                                            try {
                                                Crop_name = doc.get(0).getString("crop_name");
                                                Crop_type = doc.get(0).getString("Crop_type");
                                                Crop_term = doc.get(0).getString("Crop_term");
                                                //String soil = doc.get(0).getString("Soil_id");
                                                Crop_price = doc.get(0).getString("crop_prices");
                                                //String Crop_selling_Price = doc.get(0).getString("crop_selling_price");
                                                seedRateRatio = doc.get(0).getString("srr");
                                                seedYieldRatio = doc.get(0).getString("syr");

                                                tvDisplayCrop.setText(
                                                        "Crop Name  \t\t\t\t\t" + Crop_name + "\n" +
                                                                "Term  \t\t\t\t\t" + Crop_type + "\n" +
                                                                "Type  \t\t\t\t\t" + Crop_term + "\n" +
                                                                "Buying Price  \t\t\t\t\t" + Crop_price + "\n" +
                                                                "Seed Rate Ratio  \t\t\t\t\t" + seedRateRatio + "\n" +
                                                                "Seed Yield Ratio  \t\t\t\t\t" + seedYieldRatio
                                                );

                                                btnToBudget.setVisibility(View.VISIBLE);

                                            }
                                            catch (Exception e){
                                                Toast.makeText(getActivity(),"Please Enter Location Correctly",Toast.LENGTH_SHORT).show();
                                            }


                                            }
                                        });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "field is empty" , Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
