package com.example.hday_13_02_windowmanager;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static WindowManager manager;
    private static ImageView iv;
    private static boolean flag = true;
    private static float px,py;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        manager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);


    }

    public void click(View view) {
        switch(view.getId()){
            case R.id.but_add:

                if(flag){
                    flag = false;
                    iv = new ImageView(this);
                    iv.setImageResource(R.drawable.pic0);

                    final WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    params.alpha = 100;
                    params.format = PixelFormat.RGBA_8888;
                    params.width  = WindowManager.LayoutParams.WRAP_CONTENT;
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT;

                    params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

                    manager.addView(iv,params);

                   iv.setOnTouchListener(new View.OnTouchListener() {
                       @Override
                       public boolean onTouch(View v, MotionEvent event) {
                           switch(event.getAction()){
                               case MotionEvent.ACTION_DOWN:
                                   px = event.getRawX()-params.x;
                                   py = event.getRawY()-params.y;

                               break;
                               case MotionEvent.ACTION_MOVE:

                                   params.x = (int) (event.getRawX()-px);
                                   params.y = (int) (event.getRawY()-py);

                                   manager.updateViewLayout(iv,params);
                               break;

                           }

                           return true;
                       }
                   });


                }


            break;
            case R.id.but_remove:

                if (!flag){
                    flag = true;
                    manager.removeView(iv);
                }


            break;

        }
    }
}
