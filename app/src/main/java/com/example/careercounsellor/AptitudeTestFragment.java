package com.example.careercounsellor;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AptitudeTestFragment extends Fragment {


    public AptitudeTestFragment() {
        // Required empty public constructor
    }

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private AppCompatButton previousButton, nextButton, submitButton;
    String[] userAnswers = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    ArrayList<String> userAnswersList = new ArrayList<>(Arrays.asList(userAnswers));
    int countOptionA = 0, countOptionB = 0, countOptionC = 0, countOptionD = 0;
    private String[] questions = {
            // Technology Questions
            "Which programming language is commonly used for building dynamic web applications?",
            "What is the primary function of an operating system?",
            "What does the acronym 'HTTP' stand for in web technology?",
            "Which database management system is known for its relational model?",
            "What does 'IoT' stand for in technology?",
            // Business and Management Questions
            "What is the purpose of a balance sheet in accounting?",
            "What does SWOT analysis assess in business strategy?",
            "What is the main function of a marketing plan?",
            "What is the role of a project manager in a business project?",
            "What is the purpose of a cash flow statement in financial analysis?",
            // Healthcare and Medicine Questions
            "Which organ is responsible for filtering waste from the bloodstream?",
            "What is the function of white blood cells in the immune system?",
            "What is the primary function of the respiratory system?",
            "Which medical professional is trained to diagnose and treat mental health disorders?",
            "What is the purpose of an electrocardiogram (ECG or EKG)?",
            // Creative Arts and Design Questions
            "Which design software is commonly used for vector graphics?",
            "Who is responsible for creating visual effects in film production?",
            "What does 'CMYK' stand for in printing technology?",
            "What is the primary function of a storyboard in animation production?",
            "Which instrument is commonly used in digital music production?"
    };

    private String[][] options = {
            // Technology Options
            {"Java", "HTML", "JavaScript", "CSS"},
            {"Managing hardware resources", "Providing internet connectivity", "Running application software", "Organizing files on a computer"},
            {"HyperText Transfer Protocol", "Hyperlink Transfer Procedure", "Hypertext Transfer Port", "Hypertext Transmission Protocol"},
            {"MySQL", "MongoDB", "Redis", "SQLite"},
            {"Internet of Things", "Input/output Terminal", "Interactive Online Testing", "Integrated Operating Technique"},
            // Business and Management Options
            {"Tracking daily expenses", "Reporting financial position", "Managing inventory levels", "Analyzing market trends"},
            {"Strengths, weaknesses, opportunities, and threats", "Sales, wages, operations, and taxes", "Stock prices, dividends, assets, and liabilities", "Staffing, workflow, objectives, and timelines"},
            {"Establishing long-term goals", "Identifying target customers", "Managing employee performance", "Securing venture capital funding"},
            {"Creating marketing campaigns", "Analyzing financial statements", "Planning and executing tasks", "Conducting market research"},
            {"Evaluating customer satisfaction", "Tracking money inflows and outflows", "Measuring employee productivity", "Assessing product profitability"},
            // Healthcare and Medicine Options
            {"Liver", "Kidney", "Heart", "Lung"},
            {"Carrying oxygen", "Fighting infections", "Clotting blood", "Storing energy"},
            {"Pumping blood throughout the body", "Digesting food and absorbing nutrients", "Exchanging oxygen and carbon dioxide", "Filtering waste from the bloodstream"},
            {"Psychologist", "Cardiologist", "Dermatologist", "Orthopedist"},
            {"Monitoring brain activity", "Measuring lung capacity", "Recording heart electrical activity", "Assessing liver function"},
            // Creative Arts and Design Options
            {"Adobe Illustrator", "Adobe Premiere Pro", "Adobe After Effects", "Adobe InDesign"},
            {"Set designer", "Sound engineer", "VFX artist", "Script supervisor"},
            {"Cyan, Magenta, Yellow, Key (black)", "Computer, Monitor, Keyboard, Mouse", "Creative, Marketing, Yield, Key", "Curve, Mix, Yield, Key"},
            {"Recording dialogue lines", "Planning visual sequences", "Designing character costumes", "Creating sound effects"},
            {"Electric guitar", "MIDI keyboard", "Drum set", "Saxophone"}
    };

    private String[] answers = {
            // Technology Answers
            "2", "0", "0", "0", "0",
            // Business and Management Answers
            "1", "0", "1", "2", "1",
            // Healthcare and Medicine Answers
            "1", "1", "2", "1", "2",
            // Creative Arts and Design Answers
            "0", "2", "0", "1", "1"
    };

//    private String[] answers = {
//            // Technology Answers
//            "C", "A", "A", "A", "A",
//            // Business and Management Answers
//            "B", "A", "B", "C", "B",
//            // Healthcare and Medicine Answers
//            "B", "B", "C", "A", "C",
//            // Creative Arts and Design Answers
//            "A", "C", "A", "B", "B"
//    };

    int currentQuestionIndex = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_aptitude_test, container, false);

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
//
//                RadioButton selectedRadioButton = group.findViewById(checkedId);
//                int index = group.indexOfChild(selectedRadioButton);
//                Log.d("checkbug index", String.valueOf(index));
//                // Update the selected answer in the array
//                if(index != -1){
////                userAnswers[currentQuestionIndex] = options[currentQuestionIndex][index];
////                userAnswers[currentQuestionIndex] = String.valueOf(index);
//                }
//
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
                            }).setNegativeButton("Leave test", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout); // Replace R.id.fragment_container with your fragment container ID
                                    if (currentFragment != null) {
                                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                                        transaction.remove(currentFragment).commit();
                                    }
                                    loadFragment(new TestsFragment());
                                    dialog.dismiss();
                                }
                            });
                    completeTest.show();
                } else{
                    for (int i = 0; i < 5; i++) {
                        if(userAnswers[i] == answers[i]){
                            countOptionA++;
                        }
                    }
                    for (int i = 5; i < 10; i++) {
                        if(userAnswers[i] == answers[i]){
                            countOptionB++;
                        }
                    }
                    for (int i = 10; i < 15; i++) {
                        if(userAnswers[i] == answers[i]){
                            countOptionC++;
                        }
                    }
                    for (int i = 15; i < 20; i++) {
                        if(userAnswers[i] == answers[i]){
                            countOptionD++;
                        }
                    }

                    double percentOptionA = (double) countOptionA / 5 * 100;
                    double percentOptionB = (double) countOptionB / 5 * 100;
                    double percentOptionC = (double) countOptionC / 5 * 100;
                    double percentOptionD = (double) countOptionD / 5 * 100;

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
                    args.putString("Recommendation A", "Technology");
                    args.putString("Recommendation B", "Business and Management");
                    args.putString("Recommendation C", "Healthcare and Medicine");
                    args.putString("Recommendation D", "Creative Arts and Design");
                    if(max == "percentOptionA"){
                    args.putString("Recommendation message", "With your strong understanding of technology, you're well-suited for careers in computer science, software development, IT support, cybersecurity, and related fields. Consider exploring opportunities in these areas to further develop your skills and expertise.");
                    } else if (max == "percentOptionB") {
                    args.putString("Recommendation message", "Your proficiency in business and management concepts positions you for success in finance, marketing, human resources, operations management, and other business-related roles. Explore career paths that align with your interests and leverage your skills in these areas.");
                    } else if (max == "percentOptionC") {
                    args.putString("Recommendation message", "Your excellent performance in healthcare and medicine suggests that you have a knack for roles in healthcare professions such as doctors, nurses, pharmacists, therapists, medical researchers, and more. Consider pursuing a career in the medical field where you can make a positive impact on people's lives.");
                    } else if (max == "percentOptionD") {
                    args.putString("Recommendation message", "With your creativity and talent in creative arts and design, you're well-suited for careers in graphic design, advertising, filmmaking, writing, music, and other creative fields. Explore opportunities to showcase your creativity and pursue a fulfilling career in the arts.");

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