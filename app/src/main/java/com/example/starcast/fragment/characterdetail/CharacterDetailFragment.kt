package com.example.starcast.fragment.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starcast.R
import com.example.starcast.fragment.homescreen.CharacterDetailResponse
import kotlinx.android.synthetic.main.fragment_detail_list.*
private const val ARG_PARAM1 = "data"
private var characterDetailResponse: CharacterDetailResponse? = null
class CharacterDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterDetailResponse = it.getParcelable(ARG_PARAM1)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        tvName.text= characterDetailResponse?.name
        tvBirthYear.text= characterDetailResponse?.birth_year
        tvHeight.text= characterDetailResponse?.height
        tvFilms.text= characterDetailResponse?.height
        tvOpeningCrawl.text= characterDetailResponse?.height
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }
}