package com.glucode.about_you.about

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.ProfileCardView
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.mockdata.MockData

class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding
   // private lateinit var profileCardBinding: ViewProfileCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfileCard()
        setUpQuestions()
    }
    private fun setProfileCard(){
        val engineerName = arguments?.getString("name")
        val engineerRole = arguments?.getString("role")
        val engineer = MockData.engineers.first { it.name==engineerName }

        val  profileView = ProfileCardView(requireContext())
        profileView.name = engineerName
        profileView.role = engineerRole


        profileView.years = engineer.quickStats.years.toString()
        profileView.coffees = engineer.quickStats.coffees.toString()
        profileView.bugs = engineer.quickStats.bugs.toString()

        profileView.profileCardClick = View.OnClickListener { selectProfilePicture() }
        profileView.profileImageUri = engineer.defaultImageName
        binding.container.addView(profileView)

    }

    fun selectProfilePicture(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }
    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }

        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 100  && resultCode == -1) {
                binding.container.removeAllViews()

                    MockData.engineers.find { it.name == arguments?.getString("name") }?.defaultImageName = data?.data
                   setProfileCard()
                }
            }
    }
