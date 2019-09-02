package ar.com.favio.wefoxpokedex.screens


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ar.com.favio.wefoxpokedex.R
import ar.com.favio.wefoxpokedex.databinding.ActivityHomeBinding
import ar.com.favio.wefoxpokedex.screens.backpack.BackpackFragment
import ar.com.favio.wefoxpokedex.screens.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_backpack -> {
                binding.toolbar.title = "Pokemon Backpack"
                supportFragmentManager.beginTransaction()
                    .replace(frame_container.id, BackpackFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                binding.toolbar.title = "Catch a new pokemon!"
                supportFragmentManager.beginTransaction()
                    .replace(frame_container.id, SearchFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        binding.navigation.selectedItemId = R.id.navigation_backpack
    }
}