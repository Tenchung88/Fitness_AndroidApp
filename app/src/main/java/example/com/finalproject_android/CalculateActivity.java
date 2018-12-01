package example.com.finalproject_android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculateActivity extends Activity implements View.OnClickListener {
    private DownloadImageTask diTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatebmi);
       Button CalcButton = (Button) findViewById(R.id.Calculate2_id);
       CalcButton.setOnClickListener(this);
        Button myimage = (Button) findViewById(R.id.getpiechart_id);
        myimage.setOnClickListener(this);


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Calculate2_id: {
                EditText myheight = (EditText) findViewById(R.id.height_id);
                EditText myweight = (EditText) findViewById(R.id.weight_id);
                int result = 0;
                result = ((Integer.parseInt(myheight.getText().toString())) * (Integer.parseInt(myweight.getText().toString())));
                Intent i = new Intent(this, FramebyFrameActivity.class);
                i.putExtra("StringtoPass", Integer.toString(result));
                startActivity(i);
                break;
            }
            case R.id.getpiechart_id: {
                if (diTask != null) {
                    AsyncTask.Status diStatus = diTask.getStatus();
                    Log.v("doClick", "diTask status is " + diStatus);
                    if (diStatus != AsyncTask.Status.FINISHED) {
                        Log.v("doClick", "... no need to start a new task");
                        return;
                    }
                    // Since diStatus must be FINISHED, we can try again.
                }

                diTask = new DownloadImageTask(this);
// diTask.execute("http://chart.apis.google.com/chart?&" +
//        "cht=p&" +
//         "chs=460x250&" +
//         "chd=t:15.3,20.5,59.7,4.5&" +
//         "chl=Android%201.5%7C" +
//         "Laptops%351.6%7C" +
//         "Smartphones%252.1%7C" +
//         "Tablets%402.2&" +
//         "chco=ff0000|3355aa");
                //diTask.execute("http://chart.apis.google.com/chart?&cht=p&chs=460x250&chd=t:15.3,20.5,59.7,4.5&chl=Android%201.5%7CAndroid%201.6%7CAndroid%202.1%7CAndroid%202.2&chco=ff0000,3355aa");
                diTask.execute("http://chart.apis.google.com/chart?&cht=p&chs=460x250&chd=t:15.3,20.5,59.7,4.5&chl=PeaceMind%401.5%7CNutrition%301.6%7CTraining%202.1%7CPatience%102.2&chco=ff0030,7325ba");
                break;

            }

        default: break;
        }
    }
//    public void imgClick(View v){
//        Intent i= new Intent(this,HttpActivity.class);
//        startActivity(i);
//    }

}
