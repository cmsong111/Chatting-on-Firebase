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
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.FragmentLoginBinding;
import deu.ac.kr.csw.chatting.friends.FriendsListActivity;
import deu.ac.kr.csw.chatting.widget.LoadingDialog;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoadingDialog loadingDialog;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();
        return binding.getRoot();
    }

    /**
     * 로그인 버튼을 눌렀을 때 호출되는 함수
     */
    public void login() {
        Log.d("LoginFragment", "login");

        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();

        String email = binding.emailEditText.getText().toString();
        String password = binding.passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            loadingDialog.dismiss();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String userUid = task.getResult().getUser().getUid();
                Intent intent = new Intent(getContext(), FriendsListActivity.class);
                loadingDialog.dismiss();
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    public void register() {
        Log.d("LoginFragment", "register");
        NavHostFragment.findNavController(this).navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterIdPwFragment());
    }

    public void loginWithGoogle() {
        Log.d("LoginFragment", "loginWithGoogle");
        Toast.makeText(getContext(), "구글 로그인(미구현)", Toast.LENGTH_SHORT).show();
    }

}
