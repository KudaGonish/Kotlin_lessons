package com.example.learnkotlin.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnkotlin.App
import com.example.learnkotlin.data.database.RetrofitGithubUsersRepo
import com.example.learnkotlin.databinding.FragmentUsersBinding
import com.example.learnkotlin.presenters.UsersPresenter
import com.example.learnkotlin.ui.AndroidScreens
import com.example.learnkotlin.ui.UsersRVAdapter
import com.example.learnkotlin.ui.views.UserView
import com.example.learnkotlin.utils.ApiHolder
import com.example.learnkotlin.utils.BackButtonListener
import com.example.learnkotlin.utils.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router, AndroidScreens(),
        )
    }

    var adapter: UsersRVAdapter? = null


    private var vb: FragmentUsersBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(login: String) {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()
}
