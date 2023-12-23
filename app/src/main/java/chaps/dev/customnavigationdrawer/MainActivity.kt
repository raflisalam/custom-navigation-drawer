package chaps.dev.customnavigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chaps.dev.customnavigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuNavigationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
            R.string.nav_drawer_open, R.string.nav_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        recyclerView = binding.navView.getHeaderView(0).findViewById(R.id.rvMenu)
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter = MenuNavigationAdapter(MenuDataProvider.menuList, this)
        recyclerView.adapter = adapter
    }

    override fun onItemMenuClick(slug: String) {
        Toast.makeText(this, "Ini Toast Menu : $slug", Toast.LENGTH_SHORT).show()
        binding.drawerLayout.closeDrawers()
    }
}