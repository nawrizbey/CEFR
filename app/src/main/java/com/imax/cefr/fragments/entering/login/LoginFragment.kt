package com.imax.cefr.fragments.entering.login

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.data.api.LoginViewModel
import com.imax.cefr.data.models.UserType
import com.imax.cefr.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


data class FakeUser(
    val username: String,
    val password: String = "12345678",
    val fullName: String = "",
    val twitchChannelUsername: String = "",
    val streamKey: String = "",
    val userType: String = UserType.TEACHER.token
)

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel by viewModel<com.imax.cefr.presentation.LoginViewModel>()

    private val viewModel: LoginViewModel by viewModels()
    override fun FragmentLoginBinding.observeViewModel() {
        loginViewModel.loginFlow.onEach {}
    }

    override fun FragmentLoginBinding.setUpViews() {

        btnSignIn.setOnClickListener {

            //val email = etEmail.text.toString()
            //val password = etPassword.text.toString()
            val email = "nauka@umail.uz"
            val password = "123456"
            viewModel.login(email, password)

        }
        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { loginResponse ->

                localStorage.login = "login"// loginResponse.login
                localStorage.fullName = "Nauka"// loginResponse.fullName
                localStorage.streamKey = "streamKey"// loginResponse.streamKey
                localStorage.twitchChannelUsername =
                    "twitchChannelUsername"// loginResponse.twitchChannelUsername
                localStorage.isLoggedIn = true
                localStorage.type = UserType.TEACHER.token

                val navGraph = when (1) {
                    1 -> R.navigation.teacher_nav
                    2 -> R.navigation.student_nav
                    else -> throw UnknownError("Unknown UserType")
                }

                requireActivity().supportFragmentManager.changeNavGraph(
                    R.id.activity_container_view,
                    navGraph
                )

                requireActivity().supportFragmentManager.changeNavGraph(
                    R.id.activity_container_view,
                    navGraph
                )

                Log.d("LoginFragment", "User token: ${loginResponse.token}")
            }.onFailure { exception ->
            }.onFailure { exception ->
                // Xatolikni ko'rsatish
                Toast.makeText(
                    requireContext(),
                    "Login failed: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("error", "onViewCreated: ${exception.message}")
            }
        })
    }

    override fun FragmentLoginBinding.navigation() {}

}


/*

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.data.api.LoginViewModel
import com.imax.cefr.data.models.UserType
import com.imax.cefr.databinding.FragmentLoginBinding

data class FakeUser(
    val username: String,
    val password: String = "12345678",
    val fullName: String = "",
    val twitchChannelUsername: String = "",
    val streamKey: String = "",
    val userType: String = UserType.TEACHER.token
)

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val fakeUsers
        get() = listOf(
            FakeUser(
                username = "teacher1",
                fullName = "Amir Baymuratov",
                streamKey = "live_509012821_J6GzFWy6vraGlObtJC0XHI7QbLh43v",
                twitchChannelUsername = "amir_b1"
            ), FakeUser(
                username = "teacher2",
                fullName = "Ruslan Joldasbaev",
                streamKey = "live_1103815460_Je3dwgeuSYh27Ne85T9IgIHWvTuoQJ",
                twitchChannelUsername = "user_nukus"
            ), FakeUser(
                username = "teacher3",
                fullName = "Asadbek Qogambaev",
                streamKey = "live_1103829928_ET0hwqJ8vC4vqf7rG7VNW2Xrj9NNy6",
                twitchChannelUsername = "user_xojeli"
            ), FakeUser(
                username = "teacher4",
                fullName = "Nawrizbay Baltabaev",
                streamKey = "live_1103831671_EN0Ej9jg4taRKWDhTt1nTQeXBZuIkq",
                twitchChannelUsername = "user_shomanay"
            ), FakeUser(
                username = "teacher5",
                fullName = "Damir Dilmuratov",
                streamKey = "live_1103834869_DRdaYLWC790kGLxX7bB02gO8Odw0LQ",
                twitchChannelUsername = "user_kegeyli"
            ), FakeUser(
                username = "student1",
                fullName = "Ruslan Joldasbaev",
                userType = UserType.STUDENT.token
            )
        )


    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            //val email = binding.etEmail.text.toString()
            //val password = binding.etPassword.text.toString()
            val email = "nauka@umail.uz"
            val password = "123456"
            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { loginResponse ->
                // Login muvaffaqiyatli bo'ldi, keyingi ekranga o'tish yoki boshqa amallar

                val navGraph = when (1) {
                    1 -> R.navigation.teacher_nav
                    2 -> R.navigation.student_nav
                    else -> throw UnknownError("Unknown UserType")
                }



                requireActivity().supportFragmentManager.changeNavGraph(
                    R.id.activity_container_view,
                    navGraph
                )

                Log.d("LoginFragment", "User token: ${loginResponse.token}")
            }.onFailure { exception ->
            }.onFailure { exception ->
                // Xatolikni ko'rsatish
                Toast.makeText(
                    requireContext(),
                    "Login failed: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("error", "onViewCreated: ${exception.message}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
*/