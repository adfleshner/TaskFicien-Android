package com.boardinglabs.taskficien.fragment;

import android.content.res.AssetManager;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.boardinglabs.taskficien.R;
import com.boardinglabs.taskficien.callback.FragmentCallback;
import com.ms.square.android.etsyblur.BlurDialogFragmentHelper;

/**
 * Created by rizkygustinaldi on 11/27/14.
 */
public class BlurredFragment extends DialogFragment {
    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    private LayoutInflater layoutInflater;
    private Context mContext;
    private View contentView;

    private AssetManager assets;


    private FrameLayout contentWrapperLayout;

    private static final String TAG = BlurredFragment.class.getSimpleName();

    private BlurDialogFragmentHelper mHelper;

    protected FragmentCallback callback;

    public static BlurredFragment newInstance() {
        BlurredFragment fragment = new BlurredFragment();
        return fragment;
    }

    public static BlurredFragment newInstance(FragmentCallback vcallback) {
        BlurredFragment fragment = new BlurredFragment();
        fragment.callback = vcallback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new BlurDialogFragmentHelper(this);
        mHelper.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_front_dialog_blur, container, false);
        this.contentWrapperLayout = (FrameLayout) v.findViewById(R.id.dialog_content);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHelper.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mHelper.onStart();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mHelper.onCancel(dialog);
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mHelper.onCancel(dialog);
        super.onDismiss(dialog);
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
        this.contentWrapperLayout.addView(contentView);
    }

    public AssetManager getAssets() {
        return assets;
    }

    public void setAssets(AssetManager assets) {
        this.assets = assets;
    }

}
