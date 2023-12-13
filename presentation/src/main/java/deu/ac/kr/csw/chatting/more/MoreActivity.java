package deu.ac.kr.csw.chatting.more;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.R;
import deu.ac.kr.csw.chatting.chat.DialogActivity;
import deu.ac.kr.csw.chatting.databinding.ActivityMoreBinding;
import deu.ac.kr.csw.chatting.friends.FriendsListActivity;
import deu.ac.kr.csw.chatting.splash.SplashActivity;

@AndroidEntryPoint
public class MoreActivity extends AppCompatActivity {

    ActivityMoreBinding binding;

    MoreViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MoreViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        getSupportActionBar().setTitle("더보기");

        setContentView(binding.getRoot());
        setBottomNavigation();
    }

    public void logout() {
        viewModel.logout();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }


    public void setBottomNavigation() {
        binding.bottomNavigationBar.setSelectedItemId(R.id.navigation_settings);
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_friends) {
                Intent intent = new Intent(this, FriendsListActivity.class);
                startActivity(intent);
                finish();

            } else if (item.getItemId() == R.id.navigation_chats) {
                Intent intent2 = new Intent(this, DialogActivity.class);
                startActivity(intent2);
                finish();

            }
            return true;
        });
    }
}
