package deu.ac.kr.csw.chatting.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.FragmentLoginBinding;
import deu.ac.kr.csw.chatting.user.usecase.LoginWithGoogleUserCase;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.googleLoginButton.setOnClickListener(v -> {

        });

        binding.loginButton.setOnClickListener(v -> {
            viewModel.login();
        });

    }



}
