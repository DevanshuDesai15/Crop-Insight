package com.example.crp_recomendation.crprecom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crp_recomendation.CostStorage;
import com.example.crp_recomendation.R;


public class budgetTracker extends Fragment {

    EditText editText1,editText2,editText3,editText4,editText5,editText6 ;
    Button calculate;
    TextView show;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget_tracker,container,false);


        editText1 = (EditText)view.findViewById(R.id.et1);
        editText2 = (EditText)view.findViewById(R.id.et2);
        editText3 = (EditText)view.findViewById(R.id.et3);
        editText4 = (EditText)view.findViewById(R.id.et4);
        editText5 = (EditText)view.findViewById(R.id.et5);
        editText6 = (EditText)view.findViewById(R.id.et6);
        calculate = (Button)view.findViewById(R.id.button);
        show = (TextView)view.findViewById(R.id.tv1);

        editText1.setText(CostStorage.getBuy());
        editText5.setText(CostStorage.getSrr());
        editText6.setText(CostStorage.getSyr());


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double n1,n2,n3,n4,n5,n6,totBuyPrice,totIncome;

                n1= Double.parseDouble(editText1.getText().toString());
                n2= Double.parseDouble(editText2.getText().toString());
                n3= Double.parseDouble(editText3.getText().toString());
                n4= Double.parseDouble(editText4.getText().toString());
                n5= Double.parseDouble(editText5.getText().toString());
                n6= Double.parseDouble(editText6.getText().toString());


                totBuyPrice = ((n5*n4)*(1.1*n1))+ n2+ n3;
                totIncome = (n6*n4)*n1*100;

                show.setText(String.valueOf("ખરીદી કિંમત/Buying Price: "+totBuyPrice+"\n"+
                                            "આવક/Total Income"+ totIncome             ));

                calculate.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }


}
