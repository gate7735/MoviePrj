package com.example.movieproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.movieproject.fragment_list.Fragment1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class FragmentItem {
        ITEM1, ITEM2, ITEM3
    }

    override fun onCreate(savedInstanceState: Bundle?) {gi
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
        R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.item1 -> {
                    Toast.makeText(this, "첫 번째 선택됨.", Toast.LENGTH_SHORT).show()
                    onFragmentSelected(FragmentItem.ITEM1, null)
                }
                R.id.item1 -> {
                    Toast.makeText(this, "두 번째 선택됨.", Toast.LENGTH_SHORT).show()
                    onFragmentSelected(FragmentItem.ITEM2, null)
                }
                R.id.item1 -> {
                    Toast.makeText(this, "세 번째 선택됨.", Toast.LENGTH_SHORT).show()
                    onFragmentSelected(FragmentItem.ITEM3, null)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        supportFragmentManager.beginTransaction().add(R.id.container, Fragment1()).commit()
    }

    fun onFragmentSelected(item: FragmentItem, bundle: Bundle?) {
        var fragment: Fragment
        when(item) {
            FragmentItem.ITEM1 -> {
                toolbar.title = "첫 번째 화면"
                fragment = Fragment1()
            }
            FragmentItem.ITEM2 -> {
                toolbar.title = "두 번째 화면"
                fragment = Fragment1()
            }
            FragmentItem.ITEM3-> {
                toolbar.title = " 번째 화면"
                fragment = Fragment1()
            }
        }
    }
}