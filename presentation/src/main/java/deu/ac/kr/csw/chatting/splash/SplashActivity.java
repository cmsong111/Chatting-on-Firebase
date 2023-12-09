package deu.ac.kr.csw.chatting.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.MainActivity;
import deu.ac.kr.csw.chatting.auth.AuthActivity;
import deu.ac.kr.csw.chatting.databinding.ActivitySplashBinding;
import deu.ac.kr.csw.chatting.splash.viewmodel.SplashViewModel;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        startSplash();
    }

    private void startSplash() {
        new Handler().postDelayed(() -> {
            if (viewModel.get_isLogin()) {
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
