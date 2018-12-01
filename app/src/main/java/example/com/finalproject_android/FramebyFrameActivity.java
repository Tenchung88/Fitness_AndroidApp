package example.com.finalproject_android;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class FramebyFrameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framebyframe);
        ImageView imgView = (ImageView) findViewById(R.id.picture);
        imgView.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) imgView.getBackground();
        frameAnimation.start();
        Boolean condition = frameAnimation.isRunning();
       frameAnimation.setEnterFadeDuration(10);
      // frameAnimation.setExitFadeDuration(30);

        String passingString;
        Intent i = getIntent();
        passingString = i.getStringExtra("StringtoPass");

        if(condition == true)
        {
            AlertDialog show = new AlertDialog.Builder(this)
                    .setTitle("THE RESULT IS:").setMessage(passingString)
                    .setNeutralButton("OK",null).show();
        }

        //createDialgo(Integer.parseInt(passingString));
    }


//    public void createDialgo(String textId) {
//        MyDialogue myDialog = MyDialogue.newInstant(textId);
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        myDialog.show(transaction,"fragments");
//
//    }

}
