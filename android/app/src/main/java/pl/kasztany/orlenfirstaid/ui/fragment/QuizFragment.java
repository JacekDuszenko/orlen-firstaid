package pl.kasztany.orlenfirstaid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pl.kasztany.orlenfirstaid.R;

public class QuizFragment extends Fragment {
    private String title;
    private int page;

    public static QuizFragment newInstance(int page) {
        QuizFragment quiz = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        quiz.setArguments(args);
        return quiz;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        return view;
    }
}