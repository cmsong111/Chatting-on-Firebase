package deu.ac.kr.csw.chatting.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.MainActivity;
import deu.ac.kr.csw.chatting.databinding.FragmentRegisterIdPwBinding;

@AndroidEntryPoint
public class RegisterIdPwFragment extends Fragment {
    FragmentRegisterIdPwBinding binding;
    RegisterIdPwViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterIdPwBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterIdPwViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(viewModel);

        // 뒤로가기 버튼을 눌렀을 때, 이전 화면으로 돌아가도록 설정
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(RegisterIdPwFragment.this).navigateUp();
            }
        });


        return binding.getRoot();
    }

    public void register() {
        viewModel.register().observe(getViewLifecycleOwner(), result -> {
            if (result != null ) {
                Toast.makeText(getContext(), "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
