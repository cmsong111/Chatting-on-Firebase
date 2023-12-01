package deu.ac.kr.csw.chatting.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import deu.ac.kr.csw.chatting.databinding.FragmentRegisterIdPwBinding;

public class RegisterIdPwFragment extends Fragment {
    FragmentRegisterIdPwBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterIdPwBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
