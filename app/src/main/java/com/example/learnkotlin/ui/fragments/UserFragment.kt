package com.example.learnkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnkotlin.App
import com.example.learnkotlin.data.database.Database
import com.example.learnkotlin.data.database.RetrofitGithubRepositoriesRepo
import com.example.learnkotlin.data.internet.models.GithubUser
import com.example.learnkotlin.presenters.UserPresenter
import com.example.learnkotlin.ui.AndroidScreens
import com.example.learnkotlin.ui.views.UserView
import com.example.learnkotlin.utils.AndroidNetworkStatus
import com.example.learnkotlin.utils.ApiHolder
import com.example.learnkotlin.utils.BackButtonListener
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    @Inject lateinit var database: Database
    @Inject lateinit var router: Router
    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            App.instance.appComponent.inject(this)
        }
    }
    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder.api,
                AndroidNetworkStatus(App.instance), RoomGithubRepositoriesCache(database)),
            router,
            user,
            AndroidScreens()
        )
    }
    private var vb: FragmentUserBinding? = null
    var adapter: ReposotoriesRVAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
    override fun init() {
        vb?.rvRepositories?.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        vb?.rvRepositories?.adapter = adapter
    }
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
    override fun backPressed() = presenter.backPressed()
}
