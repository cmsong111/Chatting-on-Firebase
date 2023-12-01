package deu.ac.kr.csw.chatting.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import deu.ac.kr.csw.chatting.databinding.FragmentChatRoomListBinding

class ChatRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentChatRoomListBinding.inflate(inflater, container, false).root
    }
}