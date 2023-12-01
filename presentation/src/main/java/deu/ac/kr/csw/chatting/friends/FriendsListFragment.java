package deu.ac.kr.csw.chatting.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import deu.ac.kr.csw.chatting.databinding.FragmentFriendsListBinding;

public class FriendsListFragment extends Fragment {

    FragmentFriendsListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFriendsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
