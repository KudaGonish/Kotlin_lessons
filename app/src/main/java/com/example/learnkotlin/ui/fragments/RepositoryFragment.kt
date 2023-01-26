package com.example.learnkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learnkotlin.App
import com.example.learnkotlin.R
import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.utils.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView,
    BackButtonListener {
    var adapter: ReposotoriesRVAdapter? = null
    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(router, repository)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)
    override fun init() {}
    override fun setId(text: String) {
        tv_id.text = text
    }
    override fun setTitle(text: String) {
        tv_title.text = text
    }
    override fun setForksCount(text: String) {
        tv_forksCount.text = text
    }
    override fun backPressed() = presenter.backPressed()
}
