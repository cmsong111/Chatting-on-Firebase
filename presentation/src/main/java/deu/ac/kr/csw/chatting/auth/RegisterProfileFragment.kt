package deu.ac.kr.csw.chatting.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import deu.ac.kr.csw.chatting.databinding.FragmentRegisterProfileBinding
import deu.ac.kr.csw.chatting.friends.FriendsListActivity
import deu.ac.kr.csw.chatting.user.model.User
import deu.ac.kr.csw.chatting.user.usecase.SetUserInfoUseCase
import deu.ac.kr.csw.chatting.widget.LoadingDialog
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@AndroidEntryPoint
class RegisterProfileFragment : Fragment() {

    private lateinit var binding: FragmentRegisterProfileBinding
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    @Inject
    lateinit var setUserInfoUseCase: SetUserInfoUseCase
    lateinit var loadingDialog: LoadingDialog;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterProfileBinding.inflate(inflater, container, false)
        binding.fragment = this
        loadingDialog = LoadingDialog(this.requireContext())
        return binding.root
    }

    private fun setProfileImage() {}

    fun saveProfile() {
        loadingDialog.show()

        // corutine scope
        lifecycleScope.launch {

            val fcmToken = FirebaseMessaging.getInstance().token.await()

            val user = User(
                firebaseAuth.getCurrentUser()!!.getEmail(),
                binding.editTextNickname.text.toString(),
                "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png",
                false,
                fcmToken!!,
                binding.editTextStatusMessage.text.toString(),
                firebaseAuth.getCurrentUser()!!.getUid()
            )

            setUserInfoUseCase(user)
            startFriendsListActivity()
            loadingDialog.dismiss()
        }
    }

    private fun startFriendsListActivity() {
        val intent = Intent(activity, FriendsListActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}