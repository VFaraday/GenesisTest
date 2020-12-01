package com.bobryshev.genesistest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.bobryshev.genesistest.R
import com.bobryshev.genesistest.databinding.MainActivityBinding
import com.bobryshev.genesistest.ui.main.FavoriteFragment
import com.bobryshev.genesistest.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MainActivityBinding.inflate(layoutInflater).root)
    }
}