package com.example.starcast.fragment.characterdetail

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.starcast.R
import com.example.starcast.fragment.homescreen.CharacterDetailResponse
import com.example.starcast.fragment.homescreen.CharacterSearchViewModel
import com.example.starcast.state.ResultState
import com.example.starcast.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_detail_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


private const val ARG_PARAM1 = "data"
private var characterDetailResponse: CharacterDetailResponse? = null
class CharacterDetailFragment : Fragment(),AdapterView.OnItemSelectedListener {
    private val characterListViewModel: CharacterSearchViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterDetailResponse = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addObserver()
    }

    /**
     *  Observer of film data
     */
    private fun addObserver() {
        characterListViewModel.characterFilmDetail().observe(this, EventObserver {
            when (it) {
                is ResultState.Success<*> -> {
                    if(!it.message.isNullOrEmpty())
                    {
                        tvOpeningCrawl.text=it.message
                        pbOpeningCrawl.visibility=View.GONE
                        tvOpeningCrawl.movementMethod = ScrollingMovementMethod()
                    }
                }
                is ResultState.Failure -> {
                    Toast.makeText(requireContext(),it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

    }

    /**
     *  Set Data of people and spinner data
     */
    private fun setData() {
        tvName.text= characterDetailResponse?.name
        tvBirthYear.text= characterDetailResponse?.birth_year
        tvHeight.text= characterDetailResponse?.height. plus(getString(R.string.text_cm))
        var data:ArrayList<String>
        characterDetailResponse?.films?.let {
            data=java.util.ArrayList()
            it.forEachIndexed { index, _ ->
                val spinnerString=index.plus(1).toString().plus(getString(R.string.text_film))
                data.add(spinnerString)
            }
            if(data.isNotEmpty())
            {
                activity?.let {context->
                    val adapter=ArrayAdapter(context, android.R.layout.simple_spinner_item, data)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter=adapter
                }
            }
            spinner.onItemSelectedListener=this
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }



    /**
     *  Spinner Click listener Handle for getting Opening Crawl
     */
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        characterDetailResponse?.films?.let {
            if(it.size>0)
            {
                characterListViewModel.getFilmDataApi(it[position])
                tvOpeningCrawl.text=""
                pbOpeningCrawl.visibility=View.VISIBLE
            }

        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}