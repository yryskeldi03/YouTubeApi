package kg.geek.youtubeapi.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)


        setUI()
        setupObservers()
        initClickListener()

    }

    abstract fun setupObservers() // внутри этой функции обрабатываем observe

    abstract fun setUI() // инициализация UI

    abstract fun initClickListener() // внутри этого метода обрабатываем все клики

}