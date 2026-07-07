package com.example.kupa;

import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    TextView edit_name, edit_email, edit_phone;
    RelativeLayout logout,location;
    ImageView edit,next;

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        edit_name = view.findViewById(R.id.edit_name);
        edit_email = view.findViewById(R.id.edit_email);
//        edit_phone = view.findViewById(R.id.edit_phone);
        logout = view.findViewById(R.id.logout);
        location =view.findViewById(R.id.location);
        edit = view.findViewById(R.id.edit);
        next = view.findViewById(R.id.next);

edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),EditActivity.class);
        startActivity(intent);
    }
});

      location.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getActivity(),location.class);
              startActivity(intent);
          }
      });


        sp = getActivity().getSharedPreferences("My Profile", Context.MODE_PRIVATE);
        logout.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            sp.edit().clear().apply();

            Intent intent = new Intent(getActivity(), first.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        String name = sp.getString("name", "");
        String email = sp.getString("email", "");
        String phone = sp.getString("phone", "");


        edit_name.setText(name);
        edit_email.setText(email);

    }
}