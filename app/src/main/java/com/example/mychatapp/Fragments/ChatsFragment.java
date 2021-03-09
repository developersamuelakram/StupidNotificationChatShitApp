package com.example.mychatapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mychatapp.Adapters.UserAdapter;
import com.example.mychatapp.Model.Chatslist;
import com.example.mychatapp.Model.Users;
import com.example.mychatapp.Notification.Token;
import com.example.mychatapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ChatsFragment extends Fragment {


    List<Chatslist> userlist;
    List<Users> mUsers;
    RecyclerView recyclerView;
    UserAdapter mAdapter;
    FirebaseUser firebaseUser;
    String tokenshit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chats, container, false);


        userlist = new ArrayList<>();

        recyclerView = view.findViewById(R.id.chat_recyclerview_chatfrag);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Chatslist")
                .child(firebaseUser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userlist.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Chatslist chatslist = ds.getValue(Chatslist.class);

                    userlist.add(chatslist);



                }

                ChatsListings();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


/*

        String token =   FirebaseInstanceId.getInstance().getToken();
        stToken(token);
*/


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {

                 tokenshit = instanceIdResult.getToken();

                 stToken(tokenshit);
            }
        });





        return view;
    }
    private void stToken (String token) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tokens");

        Token token1 = new Token(token);
        databaseReference.child(firebaseUser.getUid()).setValue(token1);



    }


    private void ChatsListings() {

        mUsers = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUsers.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {

                    Users users = ds.getValue(Users.class);

                    for (Chatslist chatslist: userlist) {


                        if (users.getId().equals(chatslist.getId())) {


                            mUsers.add(users);




                        }




                    }


                }

                mAdapter = new UserAdapter(getContext(), mUsers, true);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}