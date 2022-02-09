package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes:RecyclerView
    private var list:ArrayList<Hero> = arrayListOf()
    private var title:String = "List Mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle(title)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when(selectedMode){
            R.id.action_list -> {
                title = "List Mode"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Grid Mode"
                showRecyclerGrid()
            }
            R.id.action_cardview ->{
                title = "Card Mode"
                showRecyclerCard()
            }
        }
        setActionBarTitle(title)
    }

    private fun showRecyclerList(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero){
                showSelectedHero(data)
            }
        })
    }
    private fun showRecyclerGrid(){
        rvHeroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHeroes.adapter = gridHeroAdapter
        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
    private fun showRecyclerCard(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardHeroAdapter = CardHeroAdapter(list)
        rvHeroes.adapter = cardHeroAdapter
    }
    private fun setActionBarTitle(title:String){
        supportActionBar?.title = title
    }
    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this,"Kamu memilih "+hero.name,Toast.LENGTH_SHORT).show()
    }

}