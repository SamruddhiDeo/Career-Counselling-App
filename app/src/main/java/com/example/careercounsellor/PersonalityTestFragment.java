package com.example.careercounsellor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PersonalityTestFragment extends Fragment {


    public PersonalityTestFragment() {
        // Required empty public constructor
    }
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private AppCompatButton previousButton, nextButton, submitButton;
    String[] userAnswers = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    ArrayList<String> userAnswersList = new ArrayList<>(Arrays.asList(userAnswers));
    private ArrayList<Integer> selectedOptions = new ArrayList<>();
    int countOptionA = 0, countOptionB = 0, countOptionC = 0, countOptionD = 0;
    private String[] questions = {
            "1. What do you prefer?",
            "2. When making decisions, what do you tend to?",
            "3. In social situations, what do you do?",
            "4. How do you approach planning?",
            "5. What do you trust?",
            "6. Where do you feel more comfortable?",
            "7. When facing a problem, what are you more likely to do?",
            "8. Describe your communication style.",
            "9. What do you enjoy?",
            "10. How do you envision your work environment?",
            "11. What motivates you the most?",
            "12. When meeting new people, what is your approach?",
            "13. How do you view rules and guidelines?",
            "14. What do you prefer?",
            "15. In group projects, how do you typically contribute?",
            "16. How do you cope with stress?",
            "17. What is your ideal weekend like?",
            "18. When faced with a challenge, what is your approach?",
            "19. How do you manage your time?",
            "20. How do you see yourself?"
    };

    private String[][] options = {
            {"A. Spend time with a few close friends", "B. Socialize with a large group", "C. Enjoy quiet activities alone", "D. Attend social events occasionally"},
            {"A. Rely on logic and facts", "B. Consider others' feelings", "C. Trust your instincts", "D. Explore all possibilities"},
            {"A. Feel energized by being around people", "B. Prefer one-on-one conversations", "C. Enjoy observing from the sidelines", "D. Find large gatherings overwhelming"},
            {"A. Detailed and structured", "B. Flexible and adaptable", "C. Spontaneous and open-ended", "D. Balanced and organized"},
            {"A. Objective information and evidence", "B. Gut feelings and intuition", "C. Both logic and feelings", "D. External guidance and advice"},
            {"A. In familiar and predictable settings", "B. In new and unfamiliar situations", "C. In quiet and peaceful environments", "D. Wherever the action is"},
            {"A. Analyze the problem logically and develop a plan", "B. Follow your intuition and go with the flow", "C. Seek advice and input from others", "D. Experiment with different approaches"},
            {"A. Direct and straightforward", "B. Emotionally expressive and empathetic", "C. Diplomatic and tactful", "D. Adaptive and versatile"},
            {"A. Solving complex problems", "B. Creative expression", "C. Helping others and making a difference", "D. Learning and exploring new things"},
            {"A. Structured and organized", "B. Dynamic and fast-paced", "C. Collaborative and team-oriented", "D. Flexible and adaptable"},
            {"A. Achieving goals and tangible results", "B. Making a difference and helping others", "C. Personal growth and learning", "D. Enjoying the process and journey"},
            {"A. Feel confident and at ease", "B. Feel excited and curious", "C. Feel cautious and observant", "D. Feel nervous and unsure"},
            {"A. Prefer to follow rules and guidelines", "B. Question rules and seek exceptions", "C. Create your own rules and standards", "D. Adapt to rules based on circumstances"},
            {"A. Clear instructions and specific guidelines", "B. Flexibility and openness to change", "C. Collaboration and teamwork", "D. Independence and autonomy"},
            {"A. Take on a leadership role", "B. Contribute your ideas and opinions", "C. Support others and maintain harmony", "D. Adapt your role based on group needs"},
            {"A. Seek solutions and take action", "B. Express your emotions and seek support", "C. Take time to reflect and regain perspective", "D. Stay calm and maintain balance"},
            {"A. Exploring new places and trying new activities", "B. Spending time with loved ones and friends", "C. Relaxing and enjoying leisure activities", "D. Pursuing hobbies and personal interests"},
            {"A. Confront the challenge head-on and develop a strategy", "B. Seek advice and guidance from others", "C. Approach the challenge with creativity and experimentation", "D. Assess the situation and adapt your approach accordingly"},
            {"A. Plan and organize your time meticulously", "B. Adapt your schedule based on priorities and opportunities", "C. Go with the flow and adjust plans as needed", "D. Stay flexible and open to changes in your schedule"},
            {"A. Confident and assertive", "B. Compassionate and empathetic", "C. Curious and open-minded", "D. Adaptable and versatile"}
    };


    int currentQuestionIndex = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_personality_test, container, false);

        questionTextView = view.findViewById(R.id.questionTextView);
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup);
        optionARadioButton = view.findViewById(R.id.optionARadioButton);
        optionBRadioButton = view.findViewById(R.id.optionBRadioButton);
        optionCRadioButton = view.findViewById(R.id.optionCRadioButton);
        optionDRadioButton = view.findViewById(R.id.optionDRadioButton);
        previousButton = view.findViewById(R.id.previousButton);
        nextButton = view.findViewById(R.id.nextButton);
        submitButton = view.findViewById(R.id.submitButton);

        displayQuestion(currentQuestionIndex);

//        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton selectedRadioButton = group.findViewById(checkedId);
//                int index = group.indexOfChild(selectedRadioButton);
//                Log.d("checkbug", String.valueOf(index));
//                // Update the selected answer in the array
//                if(index != -1){
//                    selectedAnswers.set(currentQuestionIndex, aptitudeOptions[currentQuestionIndex][index]);
//                    selectedOptions.set(currentQuestionIndex, index);
//                }
//            }
//        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion(currentQuestionIndex);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserAnswer(currentQuestionIndex);
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    displayQuestion(currentQuestionIndex);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswersList = new ArrayList<>(Arrays.asList(userAnswers));
                if(userAnswersList.contains(null)){
                    AlertDialog.Builder completeTest = new AlertDialog.Builder(getContext());
                    completeTest.setTitle("Answer all questions")
                            .setMessage("Solve complete test to help us recommmend the right career for you")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    completeTest.show();
                } else{
                    for (int i = 0; i < userAnswers.length; i++) {
                        if (userAnswers[i] == "0") {
                            countOptionA++;
                        } else if(userAnswers[i] == "1"){
                            countOptionB++;
                        } else if (userAnswers[i] == "2") {
                            countOptionC++;
                        } else if(userAnswers[i] == "3") {
                            countOptionD++;
                        }
                    }

                    double percentOptionA = (double) countOptionA / questions.length * 100;
                    double percentOptionB = (double) countOptionB / questions.length * 100;
                    double percentOptionC = (double) countOptionC / questions.length * 100;
                    double percentOptionD = (double) countOptionD / questions.length * 100;

                    String max;

                    if (percentOptionA >= percentOptionB && percentOptionA >= percentOptionC && percentOptionA >= percentOptionD) {
                        max = "percentOptionA";
                    } else if (percentOptionB >= percentOptionA && percentOptionB >= percentOptionC && percentOptionB >= percentOptionD) {
                        max = "percentOptionB";
                    } else if (percentOptionC >= percentOptionA && percentOptionC >= percentOptionB && percentOptionC >= percentOptionD) {
                        max = "percentOptionC";
                    } else {
                        max = "percentOptionD";
                    }


                    ResultFragment fragment = new ResultFragment();
                    Bundle args = new Bundle();
                    args.putDouble("Percent A", percentOptionA);
                    args.putDouble("Percent B", percentOptionB);
                    args.putDouble("Percent C", percentOptionC);
                    args.putDouble("Percent D", percentOptionD);
                    args.putString("Recommendation A", "Analytical Thinker");
                    args.putString("Recommendation B", "Creative Collaborator");
                    args.putString("Recommendation C", "Social Organizer");
                    args.putString("Recommendation D", "Versatile Explorer");
                    if(max == "percentOptionA"){
                        args.putString("Recommendation message", "As an Analytical Thinker, you excel in roles that require logical reasoning, data analysis, and systematic problem-solving. Careers in fields such as computer science, engineering, finance, research, and data analysis may be particularly suitable for you. Your ability to approach challenges with a structured and methodical mindset makes you well-suited for roles that involve complex problem-solving and strategic decision-making.");
                    } else if (max == "percentOptionB") {
                        args.putString("Recommendation message", "As a Creative Collaborator, you thrive in roles that allow you to express your creativity, work collaboratively with others, and innovate new solutions. Careers in areas such as marketing, advertising, design, media, and the arts may align well with your strengths. Your empathy, adaptability, and ability to think outside the box make you valuable in environments that require creative problem-solving, communication, and teamwork.");
                    } else if (max == "percentOptionC") {
                        args.putString("Recommendation message", "As a Social Organizer, you find fulfillment in roles that involve building relationships, supporting others, and creating positive change in your community. Careers in fields such as counseling, education, social work, human resources, and nonprofit organizations may resonate with your strengths. Your ability to communicate effectively, mediate conflicts, and foster collaboration make you well-suited for roles that involve working with people and making a difference in their lives.");
                    } else if (max == "percentOptionD") {
                        args.putString("Recommendation message", "As a Versatile Explorer, you enjoy exploring new opportunities, adapting to change, and embracing a variety of experiences. Careers that offer flexibility, growth potential, and the chance to learn and develop new skills may be ideal for you. Whether in entrepreneurship, consulting, project management, or freelance work, your adaptability, curiosity, and willingness to take on new challenges make you well-suited for dynamic and ever-evolving environments.");

                    }

                    fragment.setArguments(args);
                    loadFragment(fragment);

                }
            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder leaveTest = new AlertDialog.Builder(getContext());
                leaveTest.setTitle("Are you sure you want to leave?")
                        .setMessage("Solve complete test to help us recommmend the right career for you")
                        .setIcon(R.drawable.baseline_question_mark_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove the current fragment
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout); // Replace R.id.fragment_container with your fragment container ID
                                if (currentFragment != null) {
                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.remove(currentFragment).commit();
                                }
                                loadFragment(new TestsFragment());
                                dialog.dismiss();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                leaveTest.show();
            }
        });
    }

    private void displayQuestion(int questionIndex) {

        for (int i = 0; i < userAnswers.length; i++) {
            Log.d("checkbug arr", userAnswers[i]+" ,");
        }

        questionTextView.setText(questions[questionIndex]);
        optionARadioButton.setText(options[questionIndex][0]);
        optionBRadioButton.setText(options[questionIndex][1]);
        optionCRadioButton.setText(options[questionIndex][2]);
        optionDRadioButton.setText(options[questionIndex][3]);

        // Restore user's answer for the current question
        String userAnswer = userAnswers[questionIndex];
        Log.d("checkbug","userans : "+userAnswer);
        if (userAnswer != null) {
            switch (userAnswer) {
                case "0":
                    optionARadioButton.setChecked(true);
                    break;
                case "1":
                    optionBRadioButton.setChecked(true);
                    break;
                case "2":
                    optionCRadioButton.setChecked(true);
                    break;
                case "3":
                    optionDRadioButton.setChecked(true);
                    break;
            }
        } else {
            Log.d("checkbug","else");
            // Clear radio button selection if user hasn't answered this question before
            optionsRadioGroup.clearCheck();
//            optionARadioButton.setChecked(false);
//            optionBRadioButton.setChecked(false);
//            optionCRadioButton.setChecked(false);
//            optionDRadioButton.setChecked(false);
        }
    }

    // Method to save user's answer
    private void saveUserAnswer(int questionIndex) {
        if (optionARadioButton.isChecked()) {
            userAnswers[questionIndex] = "0";
        } else if (optionBRadioButton.isChecked()) {
            userAnswers[questionIndex] = "1";
        } else if (optionCRadioButton.isChecked()) {
            userAnswers[questionIndex] = "2";
        } else if (optionDRadioButton.isChecked()) {
            userAnswers[questionIndex] = "3";
        } else {
            // If none of the options are selected, clear the user's answer
            userAnswers[questionIndex] = null;
        }
    }


    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }

}