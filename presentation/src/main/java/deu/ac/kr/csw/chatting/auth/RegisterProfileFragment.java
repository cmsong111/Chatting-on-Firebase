package deu.ac.kr.csw.chatting.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import deu.ac.kr.csw.chatting.databinding.FragmentRegisterProfileBinding;

public class RegisterProfileFragment extends Fragment {
    FragmentRegisterProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterProfileBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
