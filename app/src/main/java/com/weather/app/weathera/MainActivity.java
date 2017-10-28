package com.weather.app.weathera;

import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.weather.app.weathera.domain.Student;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @Inject
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        MessageEvent event = new MessageEvent("zhihaowu");

        EventBus.getDefault().post(event);

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getModel(MessageEvent event)
    {
        SystemClock.sleep(2000);
        Looper.prepare();
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public class MessageEvent {

        private String message;

        public MessageEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
