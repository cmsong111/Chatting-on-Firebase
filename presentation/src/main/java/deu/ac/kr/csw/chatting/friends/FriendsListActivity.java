package deu.ac.kr.csw.chatting.friends;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.R;
import deu.ac.kr.csw.chatting.chat.DialogActivity;
import deu.ac.kr.csw.chatting.databinding.ActivityFriendsListBinding;
import deu.ac.kr.csw.chatting.more.MoreActivity;

@AndroidEntryPoint
public class FriendsListActivity extends AppCompatActivity {

    ActivityFriendsListBinding binding;
    FriendListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsListBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(FriendListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        binding.friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setTitle("친구 목록");

        setContentView(binding.getRoot());
        setBottomNavigation();
    }

    public void setBottomNavigation() {
        binding.bottomNavigationBar.setSelectedItemId(R.id.navigation_friends);
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_chats) {
                Intent intent2 = new Intent(this, DialogActivity.class);
                startActivity(intent2);
                finish();

            } else if (item.getItemId() == R.id.navigation_settings) {
                Intent intent3 = new Intent(this, MoreActivity.class);
                startActivity(intent3);
                finish();
            }
            return true;
        });
    }

}