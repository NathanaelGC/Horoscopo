package com.nathanaelgc.horoscopo.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.nathanaelgc.horoscopo.R
import com.nathanaelgc.horoscopo.databinding.FragmentLuckBinding
import com.nathanaelgc.horoscopo.ui.core.listeners.OnSwipeTouchListener
import com.nathanaelgc.horoscopo.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let { luck ->
            val currentPrediction = getString(luck.text)
            val currentPredictionimg = luck.image
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvLucky.text = currentPrediction
            binding.tvShare.setOnClickListener { shareResult(currentPrediction,currentPredictionimg) }
        }
    }
    private fun shareResult(predictionText: String, drawableId: Int) {
        try {
            val imageUri = getUriForDrawable(drawableId)
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND

                putExtra(Intent.EXTRA_TEXT, predictionText)
                putExtra(Intent.EXTRA_STREAM, imageUri)

                type = "image/jpeg"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            startActivity(Intent.createChooser(sendIntent, "Compartir predicción..."))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getUriForDrawable(drawableId: Int): Uri {
        val bitmap =
            (requireContext().getDrawable(drawableId) as BitmapDrawable).bitmap

        val cachePath = File(requireContext().cacheDir, "images")
        cachePath.mkdirs()

        val file = File(cachePath, "shared_image.jpg")
        val fileOutputStream = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.close()

        val authority = "${requireContext().packageName}.provider"
        return FileProvider.getUriForFile(requireContext(), authority, file)
    }


    private fun initListeners() {
        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                spinRoulette()
            }

            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {

        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun LuckFragment.slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            private fun growCard() {
                val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
                growAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        binding.ivReverse.isVisible = false
                        showPremonitionView()
                    }

                    private fun showPremonitionView() {
                        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
                        disappearAnimation.duration = 200

                        val apperAnimation = AlphaAnimation(0.0f, 1.0f)
                        apperAnimation.duration = 1000

                        disappearAnimation.setAnimationListener(object :
                            Animation.AnimationListener {
                            override fun onAnimationEnd(p0: Animation?) {
                                binding.preview.isVisible = false
                                binding.prediction.isVisible = true
                            }

                            override fun onAnimationRepeat(p0: Animation?) {}

                            override fun onAnimationStart(p0: Animation?) {}

                        })
                        binding.preview.startAnimation(disappearAnimation)
                        binding.prediction.startAnimation(apperAnimation)
                    }

                    override fun onAnimationRepeat(p0: Animation?) {}

                    override fun onAnimationStart(p0: Animation?) {}

                })
                binding.ivReverse.startAnimation(growAnimation)
            }

            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.isVisible = true

            }

        })
        binding.ivReverse.startAnimation(slideUpAnimation)
    }
}