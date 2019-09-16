package pl.kasztany.orlenfirstaid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import pl.kasztany.orlenfirstaid.R;

import static pl.kasztany.orlenfirstaid.util.Constants.ENCYCLO_URL;

public class EncyclopediaFragment extends Fragment {
    private String title;
    private int page;
    private WebView webView;

    public static EncyclopediaFragment newInstance(int page) {
        EncyclopediaFragment fragmentFirst = new EncyclopediaFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encyclopedia, container, false);
        initWebView(view);
        return view;
    }

    private void initWebView(View view) {
        webView = view.findViewById(R.id.encyclopediaWv);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(ENCYCLO_URL);
    }
}
