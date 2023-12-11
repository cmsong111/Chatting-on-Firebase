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

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.MainActivity;
import deu.ac.kr.csw.chatting.databinding.FragmentRegisterIdPwBinding;
import deu.ac.kr.csw.chatting.widget.LoadingDialog;

@AndroidEntryPoint
public class RegisterIdPwFragment extends Fragment {
    FragmentRegisterIdPwBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterIdPwBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);

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
        String email = binding.editTextId.getText().toString();
        String password = binding.editTextPassword.getText().toString();
        String passwordConfirm = binding.editTextConfirmPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(getContext(), "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(getContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(RegisterIdPwFragment.this).navigate(
                                        RegisterIdPwFragmentDirections.actionRegisterIdPwFragmentToRegisterProfileFragment()
                                );
                            } else {
                                Toast.makeText(getContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

        loadingDialog.dismiss();
    }
}
