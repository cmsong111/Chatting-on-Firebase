package deu.ac.kr.csw.chatting;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {

    FragmentMoreBinding binding;

    @Override
    public View onCreateView(@NonNull android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        binding = deu.ac.kr.csw.chatting.databinding.FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
