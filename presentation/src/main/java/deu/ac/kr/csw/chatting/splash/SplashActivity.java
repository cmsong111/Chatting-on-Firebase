package deu.ac.kr.csw.chatting.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.MainActivity;
import deu.ac.kr.csw.chatting.auth.AuthActivity;
import deu.ac.kr.csw.chatting.databinding.ActivitySplashBinding;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        startSplash();
    }

    private void startSplash() {
        new Handler().postDelayed(() -> {
            if (firebaseAuth.getCurrentUser() != null) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, AuthActivity.class));
                finish();
            }
            finish();
        }, 1500);
    }
}
