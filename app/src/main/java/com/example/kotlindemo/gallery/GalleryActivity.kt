package com.example.kotlindemo.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.kotlindemo.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_gallery.*

/**
 *
 * @Author:         cbx
 * @CreateDate:     2020/7/16 2:03 PM
 * @Description:
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class GalleryActivity : AppCompatActivity() {


    val list = listOf("tab1", "tab2", "tab3","tab4","tab5","tab6","tab7","tab8")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val compositePageTransformer = CompositePageTransformer();
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ScaleInTransformer())
        viewPager.setPageTransformer(compositePageTransformer)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount() = list.size

            override fun createFragment(position: Int) = BlankFragment.newInstance()
        }

        TabLayoutMediator(
            tabLayout,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = list[position]
            }).attach()

    }
}