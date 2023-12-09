package deu.ac.kr.csw.chatting.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import deu.ac.kr.csw.chatting.databinding.FragmentChatRoomListBinding


class ChatRoomFragment : Fragment() {

    private lateinit var binding: FragmentChatRoomListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatRoomListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "채팅방"

        return binding.root
    }
}