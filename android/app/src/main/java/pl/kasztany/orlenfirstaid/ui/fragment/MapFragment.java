package pl.kasztany.orlenfirstaid.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.util.Constants;

public class MapFragment extends Fragment {
    private WebView webView;

    public static MapFragment newInstance(int page) {
        MapFragment map = new MapFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        map.setArguments(args);
        return map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initializeMapWebView(view);
        return view;
    }

    private void initializeMapWebView(View view) {
        webView = view.findViewById(R.id.mapWebview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl(Constants.MAP_URL);
        webView.setWebViewClient(new WebViewClient());
    }
}
