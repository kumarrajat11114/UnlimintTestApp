package com.rajatkumar.unliminttestapp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {

    private var _binding: T? = null
    val binding: T
        get() = _binding!!

    abstract fun inflateBinding(): T

    abstract fun created()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
        created()
    }

    fun showErrorView(error: Throwable) {
        if (error is JokeError) {
            when (error) {
                is JokeError.ApiError -> showDismissableSnackbar(message = "Error: ${error.message}")
                is JokeError.InternalServerError -> showSnackbar(message = "Error: ${error.message}")
                is JokeError.NoInternetError -> showDismissableSnackbar(message = "Error: ${error.message}")
                is JokeError.TimeoutError -> showSnackbar(message = "Error: ${error.message}")
                is JokeError.DataNotFoundError -> showSnackbar(message = "Error: ${error.message}")
            }
        } else {
            throw error
        }
    }

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    fun showSnackbar(view: View = binding.root, message: String, length: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, message, length).show()
    }

    fun showDismissableSnackbar(
        view: View = binding.root,
        message: String,
        actionText: String = "OK"
    ) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(actionText) {
            snackbar.dismiss()
        }
        snackbar.show()
    }

}