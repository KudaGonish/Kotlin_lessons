package com.example.learnkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.learnkotlin.App
import com.example.learnkotlin.data.GithubUsersRepo
import com.example.learnkotlin.databinding.FragmentUserInfoBinding
import com.example.learnkotlin.presenters.UserInfoPresenter
import com.example.learnkotlin.ui.views.UserInfoView
import com.example.learnkotlin.utils.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            UserInfoFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "")
    }

    private var vb: FragmentUserInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(userLogin, GithubUsersRepo(), App.instance.router)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }



}