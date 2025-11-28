package com.example.kupa.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kupa.ModelClass;
import com.example.kupa.MycartFragment;
import com.example.kupa.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SandwichSheet extends BottomSheetDialogFragment {

    View view;
    ImageView min, max,like;
    TextView coun, cartamou,addcart;
    CheckBox toma, mayo, chee, omel, saus;
    private int increse = 0;
    int result = 0;
    int results=0;
    int resultz=0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.sheet_sandwich,container, false);

        like = view.findViewById(R.id.likedimg);
        min = view.findViewById(R.id.minus1);
        max = view.findViewById(R.id.plus1);
        coun = view.findViewById(R.id.count1);
        cartamou = view.findViewById(R.id.cartamount1);
        toma = view.findViewById(R.id.Tomato1);
        mayo = view.findViewById(R.id.Mayonnaise1);
        chee = view.findViewById(R.id.Cheese1);
        omel = view.findViewById(R.id.Omelet1);
        saus = view.findViewById(R.id.Sausage1);
        addcart = view.findViewById(R.id.addcart);

        removeAddons();

        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increse = increse + 1;
                updateResult(); // Cart Add
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (increse != 0) {
                    increse = increse - 1;
                    updateResult(); // Cart Minus
                }

            }
        });

        toma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSause(); // Tomato Add
            }
        });

        mayo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSause(); // Mayonisse Add
            }
        });
        chee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTopping(); // Cheese Add
            }
        });
        omel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTopping(); // Omelet Add
            }
        });
        saus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTopping(); // Sausage Add
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if (context != null) {
                    Toast.makeText(context, "You Liked Sandwich", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MycartFragment.userList.add(new ModelClass(R.drawable.sand,"Sandwich",increse,(increse*100),1,results,1,resultz,((increse*100)+results+resultz),R.drawable.baseline_delete_forever_24));
                Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                Dissmis();
            }
        });

        return view;
    }

    private void Dissmis() {
        dismiss();
    }

    private void updateSause() {
        int num1 = 30;
        int num2 = 25;

        results = 0;

        if (increse == 0) {
            coun.setText("Add");
            removeAddons();
        } else {
            toma.setEnabled(true);
            mayo.setEnabled(true);
            coun.setText(String.valueOf(increse));
        }
        if (toma.isChecked()) {
            results += num1;
        }
        if (mayo.isChecked()) {
            results += num2;
        }
        cartamou.setText(String.valueOf((increse * 100) + results+resultz+result));
    }


    private void updateTopping() {
        int num3 = 30;
        int num4 = 20;
        int num5 = 50;

        resultz = 0;

        if (increse == 0) {
            coun.setText("Add");
            removeAddons();
        } else {
            chee.setEnabled(true);
            omel.setEnabled(true);
            saus.setEnabled(true);
            coun.setText(String.valueOf(increse));
        }
        if (chee.isChecked()) {
            resultz += num3;
        }
        if (omel.isChecked()) {
            resultz += num4;
        }
        if (saus.isChecked()) {
            resultz += num5;
        }
        cartamou.setText(String.valueOf((increse * 100) + resultz+result+results));
    }

    private void updateResult() {
        int num1 = 30;
        int num2 = 25;
        int num3 = 30;
        int num4 = 20;
        int num5 = 50;

         result = 0;
        if (increse == 0) {
            coun.setText("Add");
            removeAddons();
        } else {
            toma.setEnabled(true);
            mayo.setEnabled(true);
            chee.setEnabled(true);
            omel.setEnabled(true);
            saus.setEnabled(true);
            coun.setText(String.valueOf(increse));
        }
        if (toma.isChecked()) {
            result += num1;
        }
        if (mayo.isChecked()) {
            result += num2;
        }
        if (chee.isChecked()) {
            result += num3;
        }
        if (omel.isChecked()) {
            result += num4;
        }
        if (saus.isChecked()) {
            result += num5;
        }
        cartamou.setText(String.valueOf((increse * 100) + result));
    }

    private void removeAddons() {
        toma.setChecked(false);
        mayo.setChecked(false);
        chee.setChecked(false);
        omel.setChecked(false);
        saus.setChecked(false);
        toma.setEnabled(false);
        mayo.setEnabled(false);
        chee.setEnabled(false);
        omel.setEnabled(false);
        saus.setEnabled(false);
    }


}