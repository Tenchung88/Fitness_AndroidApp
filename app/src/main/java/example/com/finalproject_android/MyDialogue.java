package example.com.finalproject_android;

import android.app.Dialog;
import android.app.IntentService;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogue extends DialogFragment implements View.OnClickListener {
    public static MyDialogue newInstant (int textforfragment){

        MyDialogue m = new MyDialogue();
        Bundle bundle = new Bundle();
        bundle.putInt("about_text",textforfragment);
        m.setArguments(bundle);

        return m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aboutdialog_fragment,container,false);
        TextView textInDialgo =  (TextView)view.findViewById(R.id.aboutext_id);
        textInDialgo.setText(getArguments().getInt("about_text"));

        Button button = (Button)view.findViewById(R.id.close_but_id);
        button.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}



