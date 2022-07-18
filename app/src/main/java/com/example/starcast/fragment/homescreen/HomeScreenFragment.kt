package com.example.starcast.fragment.homescreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starcast.*
import com.example.starcast.`interface`.RecyclerItemClickListener
import com.example.starcast.state.ResultState
import com.example.starcast.utils.CommonUtils
import com.example.starcast.utils.EventObserver
import com.example.starcast.utils.clickable
import kotlinx.android.synthetic.main.fragment_home_screen.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.ArrayList

private const val ARG_PARAM1 = "data"


class HomeScreenFragment : Fragment(),View.OnClickListener, RecyclerItemClickListener {
    private val characterListViewModel: CharacterSearchViewModel by sharedViewModel()
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var characterList: ArrayList<CharacterDetailResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        characterListViewModel.characterSearchApi().observe(this, EventObserver {
            btnSearch.clickable(true)
            progressBar.visibility=View.GONE
            when (it) {
                is ResultState.Success<*> -> {
                    it.data?.let {data->
                        val result: ArrayList<CharacterDetailResponse>? = CommonUtils.genericCastOrNull<ArrayList<CharacterDetailResponse>>(data)
                        result?.let {itt->
                            if(itt.isNotEmpty())
                            {
                                characterList.clear()
                                characterList.addAll(itt)
                                characterListAdapter.notifyItemRangeRemoved(0, characterList.size)
                                characterListAdapter.notifyItemRangeInserted(0, characterList.size)

                            }
                        }

                    }


                }
                is ResultState.Failure -> {
                    Toast.makeText(requireContext(),it.errorMessage,Toast.LENGTH_SHORT).show()
                }
            }
        })

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setonClickListener()
    }

    private fun setonClickListener() {
        btnSearch.setOnClickListener(this)
    }



    private fun initAdapter() {
        characterList = if(::characterList.isInitialized) {
            characterList
        } else {
            ArrayList()
        }

        rvMainScreen.apply {
            layoutManager = LinearLayoutManager(requireContext())
            characterListAdapter = CharacterListAdapter(characterList,this@HomeScreenFragment)
            itemAnimator = null
            adapter = characterListAdapter
        }
    }

    private fun isValidate(searchText: String):Boolean
    {
        if(searchText.isEmpty())
        {
            return false
        }
        return true
    }



    override fun onClick(view: View?) {
        val searchText=etSearchPeople.text.toString()
        if(isValidate(searchText))
        {
            btnSearch.clickable(false)
            val data: HashMap<String, String> = HashMap()
            data.apply {
                this[getString(R.string.text_search)] = searchText
            }
            progressBar.visibility=View.VISIBLE
            characterListViewModel.getDashboardCountList(data)
        }
        else
        {
            Toast.makeText(requireContext(),getString(R.string.text_enter_people_name),Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRecyclerItemClicked(view: View?, position: Int, extra: Any?) {
        Bundle().apply {
            this.putParcelable(ARG_PARAM1,characterList[position])
            view?.let { Navigation.findNavController(it).navigate(R.id.action_FirstFragment_to_SecondFragment,this) }
        }

    }

}