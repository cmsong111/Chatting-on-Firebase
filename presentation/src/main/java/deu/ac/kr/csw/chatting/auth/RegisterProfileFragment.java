package deu.ac.kr.csw.chatting.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.FragmentRegisterProfileBinding;
import deu.ac.kr.csw.chatting.user.model.User;
import deu.ac.kr.csw.chatting.user.usecase.SetUserInfoUseCase;

@AndroidEntryPoint
public class RegisterProfileFragment extends Fragment {
    FragmentRegisterProfileBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    RegisterProfileViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterProfileBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(RegisterProfileViewModel.class);
        return binding.getRoot();
    }


    private void setProfileImage() {

    }

    public void saveProfile() {
        User user = new User(
                firebaseAuth.getCurrentUser().getEmail(),
                binding.editTextNickname.getText().toString(),
                "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png",
                false,
                "fcm-token",
                binding.editTextStatusMessage.getText().toString()
        );


    }

}
