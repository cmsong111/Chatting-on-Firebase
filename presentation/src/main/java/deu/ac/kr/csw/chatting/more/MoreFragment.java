package deu.ac.kr.csw.chatting.more;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding;
import deu.ac.kr.csw.chatting.splash.SplashActivity;

@AndroidEntryPoint
public class MoreFragment extends Fragment {

    FragmentMoreBinding binding;

    MoreViewModel viewModel;

    @Override
    public View onCreateView(@NonNull android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        binding = deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding.inflate(inflater, container, false);
        viewModel = new androidx.lifecycle.ViewModelProvider(this).get(MoreViewModel.class);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("더보기");

        binding.logoutButton.setOnClickListener(v -> {
            viewModel.logout();
            Intent intent = new Intent(getContext(), SplashActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return binding.getRoot();
    }
}
