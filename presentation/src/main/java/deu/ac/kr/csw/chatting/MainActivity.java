package deu.ac.kr.csw.chatting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 초기 Fragment 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FriendsListFragment()).commit();

        // 바텀 네비게이션 클릭 시 Fragment 변경
        binding.bottomNavigationBar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_friends) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FriendsListFragment()).commit();
            } else if (item.getItemId() == R.id.navigation_chats) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ChatRoomFragment()).commit();
            } else if (item.getItemId() == R.id.navigation_settings) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MoreFragment()).commit();
            }
            return true;
        });

    }

}