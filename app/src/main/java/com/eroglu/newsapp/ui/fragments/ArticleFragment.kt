package com.eroglu.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eroglu.newsapp.R
import com.eroglu.newsapp.databinding.FragmentArticleBinding
import com.eroglu.newsapp.ui.NewsActivity
import com.eroglu.newsapp.ui.NewsViewModel
import com.eroglu.newsapp.models.Article
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            //loadUrl(article.url)
            article.url?.let { loadUrl(it) } //  article nullable
            //loadUrl(args.article.url)
        }

        binding.fab.setOnClickListener { // FloatingActionButton(fab)
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}