package deu.ac.kr.csw.chatting.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.MainActivity;
import deu.ac.kr.csw.chatting.R;
import deu.ac.kr.csw.chatting.databinding.FragmentLoginBinding;
import deu.ac.kr.csw.chatting.user.usecase.LoginWithGoogleUserCase;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.setFragment(this);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();


        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.isLogin().observeForever(isLogin -> {
            if (isLogin) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void login() {
        Log.d("LoginFragment", "login");
        viewModel.login();
    }

    public void register() {
        Log.d("LoginFragment", "register");
        NavHostFragment.findNavController(this).navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterIdPwFragment());
    }
}
