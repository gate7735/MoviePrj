package com.example.movieproject.fragment_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieproject.R
import kotlinx.android.synthetic.main.fragment_page.view.*

class PageFragment : Fragment() {

    var imageId = 0
    var title: String? = null
    var number: String? = null
    var grade: String? = null

    var callback: FragmentCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FragmentCallback) {
            callback = context
        } else {
            Log.d(TAG, "Activity is not FragmentCallback.")
        }
    }

    override fun onDetach() {
        super.onDetach()

        if (callback != null) {
            callback = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle != null) {
            imageId = bundle.getInt("imageId")
            title = bundle.getString("title")
            number = bundle.getString("number")
            grade = bundle.getString("grade")
        }
    }

    companion object {
        private const val TAG = "PageFragment"

        fun newInstance(imageId: Int, title: String?, number: String?, grade: String?): PageFragment {
            val fragment = PageFragment()

            val bundle = Bundle()
            bundle.putInt("imageId", imageId)
            bundle.putString("title", title)
            bundle.putString("number", number)
            bundle.putString("grade", grade)
            fragment.arguments = bundle

            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_page, container, false)

        rootView.iv_poster.setImageResource(imageId)
        rootView.tv_title.setText(title)
        rootView.tv_number.setText(number)
        rootView.tv_grade.setText(grade)
        return rootView
    }
}