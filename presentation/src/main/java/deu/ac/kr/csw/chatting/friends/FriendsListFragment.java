package deu.ac.kr.csw.chatting.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.FragmentFriendsListBinding;
import deu.ac.kr.csw.chatting.user.model.UserInfo;

@AndroidEntryPoint
public class FriendsListFragment extends Fragment {

    FragmentFriendsListBinding binding;
    FriendListViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFriendsListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(FriendListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        binding.friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("친구 목록");

        return binding.getRoot();
    }

}