package com.example.petdoc.ui.chat;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.example.petdoc.MainActivity;
import com.example.petdoc.databinding.FragmentVideoCallBinding;


public class VideoCallFragment extends Fragment {
    private FragmentVideoCallBinding binding;
    public VideoCallFragment(){}
    int targetUserID = MainActivity.USERID;
    public VideoCallFragment(int userID){
        targetUserID = userID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentVideoCallBinding.inflate(inflater, container, false);
        WebView webView = binding.webView;
        setMyWebviewSettings(webView);

        webView.setWebChromeClient(new WebChromeClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                return true;
            }
        });

        webView.loadUrl("https://meet.jit.si/PetDoc"+targetUserID+"#config.disableDeepLinking=true");
        return binding.getRoot();
    }

    private void setMyWebviewSettings(WebView webView) {
        WebSettings MyWebviewSettings = webView.getSettings();
        MyWebviewSettings.setAllowFileAccessFromFileURLs(true);
        MyWebviewSettings.setAllowUniversalAccessFromFileURLs(true);
        MyWebviewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        MyWebviewSettings.setJavaScriptEnabled(true);
        MyWebviewSettings.setDomStorageEnabled(true);
        MyWebviewSettings.setBuiltInZoomControls(true);
        MyWebviewSettings.setAllowFileAccess(true);
        MyWebviewSettings.setSupportZoom(true);
        MyWebviewSettings.setSupportMultipleWindows(true);
        MyWebviewSettings.setMediaPlaybackRequiresUserGesture(false);
        //String desktopBrowser= "Mozilla/5.0";
        //MyWebviewSettings.setUserAgentString(desktopBrowser);
    }
}