package com.example.movieproject.fragment_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.movieproject.R
import kotlinx.android.synthetic.main.fragment_1.view.*

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_1, container, false)

//        rootView.pager.adapter = PagerAdapter(activity!!.supportFragmentManager, activity!!.lifecycle)
        rootView.pager.adapter = PagerAdapter(requireActivity().supportFragmentManager, requireActivity().lifecycle)
        rootView.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        rootView.pager.clipToPadding = false
        rootView.pager.setPadding(150, 0, 150, 0)
        rootView.pager.offscreenPageLimit = 4

        rootView.indicator.setViewPager(rootView.pager)
        rootView.indicator.createIndicators(4, 0)

        rootView.pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                println("${position+1} 페이지 선택됨")
                rootView.indicator.animatePageSelected(position)
            }
        })

        return rootView
    }

    // 뷰페이저를 위해 어댑터 클래스 필요
    // FragmentStateAdapter를 상속받아 프래그먼트들을 아이템으로 가질 수 있음
    // 프래그먼트가 올라갈 MainActivity의 속성을 참조하여 생성자에 전달
    inner class PagerAdapter : FragmentStateAdapter {
        constructor(fm: FragmentManager, lc: Lifecycle) : super(fm, lc)

        override fun getItemCount(): Int = 4 // 뷰페이저 안에 넣을 프래그먼트 3개로 지정

        // 3개의 프래그먼트 객체를 반환
        // 전달된 파라미터는 프래그먼트의 인덱스 값
        override fun createFragment(position: Int): Fragment {
            var imageId: Int = 0
            var title: String = ""
            var details1: String = ""
            var details2: String = ""
            var fragment: PageFragment? = null

            when(position) {
                0 -> {
                    imageId = R.drawable.poster1 // 영화 포스터
                    title = "${position+1}. 결백" // 영화 제목
                    details1 = "관객 수 312.745" // 관객 수
                    details2 = "15세 이상 관람가" // 관람 등급
                    fragment = PageFragment.newInstance(imageId, title, details1, details2)
                }
                2 -> {
                    imageId = R.drawable.poster2
                    title = "${position+1}. 침입자"
                    details1 = "관객 수 166,604"
                    details2 = "15세 이상 관람가"
                    fragment = PageFragment.newInstance(imageId, title, details1, details2)
                }
                3 -> {
                    imageId = R.drawable.poster3
                    title = "${position+1}. 에어로너츠"
                    details1 = "관객 수 51,608"
                    details2 = "12세 이상 관람가"
                    fragment = PageFragment.newInstance(imageId, title, details1, details2)
                }
                else -> {
                    fragment = PageFragment.newInstance(0, "", "", "")
                }
            }
            return fragment
        }
    }
}