package deu.ac.kr.csw.chatting.more;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {

    FragmentMoreBinding binding;

    @Override
    public View onCreateView(@NonNull android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        binding = deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding.inflate(inflater, container, false);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("더보기");

        return binding.getRoot();
    }
}
