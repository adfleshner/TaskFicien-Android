package com.boardinglabs.taskficien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.boardinglabs.taskficien.callback.FragmentCallback;
import com.boardinglabs.taskficien.fragment.BlurredFragment;
import com.boardinglabs.taskficien.fragment.LoginFragment;

public class FrontActivity extends FragmentActivity implements FragmentCallback {
    FrameLayout logoLayout;
    LinearLayout sletingLayout;
    LinearLayout contentLayout;
    LinearLayout buttonsLayout;
    ImageButton sletingTaskButton;
    boolean isTouchingTasButton;

    FrontActivity currActivity;
    LoginFragment loginFragment;

    private Animation animFadein;
    private Animation animFadeout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currActivity = this;
        setContentView(R.layout.activity_front);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentLayout = (LinearLayout) findViewById(R.id.front_content);

        logoLayout = (FrameLayout) inflator.inflate(R.layout.activity_front_item_logo, null);
        sletingLayout = (LinearLayout) inflator.inflate(R.layout.activity_front_item_sleting, null);
        buttonsLayout = (LinearLayout) inflator.inflate(R.layout.activity_front_item_buttons, null);

        sletingTaskButton = (ImageButton) sletingLayout.findViewById(R.id.taskButton);

        sletingTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTouchingTasButton = true;

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ImageView sletingImageView = (ImageView) sletingLayout.findViewById(R.id.sletingImageView);
                ImageButton imageButton = (ImageButton) sletingLayout.findViewById(R.id.taskButton);
                layoutParams.setMargins(0, sletingImageView.getHeight()-imageButton.getHeight(), 0, 0);
                sletingLayout.addView(buttonsLayout,layoutParams);

                LinearLayout.LayoutParams sletingLayoutParams = (LinearLayout.LayoutParams) sletingImageView.getLayoutParams();
                sletingLayoutParams.topMargin -= sletingImageView.getHeight();
                sletingImageView.setLayoutParams(sletingLayoutParams);
            }
        });

        Button loginButton = (Button) buttonsLayout.findViewById(R.id.front_login_up_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragment = LoginFragment.newInstance(currActivity);
                loginFragment.setAssets(getAssets());
                loginFragment.show(getSupportFragmentManager(), "login");
            }
        });



        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadein.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animation == animFadein){

                    BitmapDrawable bdSleting=(BitmapDrawable) getResources().getDrawable(R.drawable.tf_front_sleting);
                    BitmapDrawable bdSletingButton=(BitmapDrawable) getResources().getDrawable(R.drawable.tf_front_button);
                    int height=bdSletingButton.getBitmap().getHeight() + bdSleting.getBitmap().getHeight();

                    FrameLayout.LayoutParams contentLayoutParams = (FrameLayout.LayoutParams) contentLayout.getLayoutParams();
                    contentLayoutParams.height = contentLayout.getHeight() + 40 + height;
                    contentLayoutParams.width = contentLayout.getWidth();
                    contentLayout.setLayoutParams(contentLayoutParams);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    layoutParams.setMargins(0, 40, 0, 0);
                    contentLayout.addView(sletingLayout,layoutParams);

                    LinearLayout.LayoutParams sletingLayoutParams = (LinearLayout.LayoutParams) sletingLayout.getLayoutParams();
                    sletingLayoutParams.height = height;
                    sletingLayoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    sletingLayout.setLayoutParams(sletingLayoutParams);

                }

            }
        });
        contentLayout.addView(logoLayout);
        contentLayout.startAnimation(animFadein);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void FragmentDidAction(String actionMessage, Object param) {
        loginFragment.dismiss();

        contentLayout.removeView(sletingLayout);
        animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        animFadeout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == animFadeout) {

                    Intent intent = new Intent(FrontActivity.this, MainActivity.class);
                    startActivityForResult(intent, 1);
                    overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
                    finish();

                }

            }
        });
        contentLayout.startAnimation(animFadeout);
        contentLayout.removeView(logoLayout);

    }
}
