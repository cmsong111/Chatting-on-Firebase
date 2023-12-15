package deu.ac.kr.csw.chatting.auth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.ActivityAuthBinding;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


}
