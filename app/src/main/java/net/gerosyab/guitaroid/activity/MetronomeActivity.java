package net.gerosyab.guitaroid.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gerosyab.guitaroid.Constants;
import net.gerosyab.guitaroid.service.MetronomeService;
import net.gerosyab.guitaroid.service.MetronomeService.LocalBinder;
import net.gerosyab.guitaroid.R;
import net.gerosyab.guitaroid.util.BpmUtil;
import net.gerosyab.guitaroid.view.AutoRepeatImageView;
import net.gerosyab.guitaroid.view.CircleButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by donghe on 2017-04-03.
 */

public class MetronomeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MetronomeActivity";
    boolean isAlive = false;
    boolean mBound = false;
    private boolean isTapClicked = false;
    BroadcastReceiver broadcastReceiver;
    AutoRepeatImageView bpmMinusImg, bpmPlusImg, accentPrevImg, accentNextImg, soundPrevImg, soundNextImg;
    TextView bpmText, bpmBgText, accentText, soundText;
    ImageView button1BackgroundImage, button2BackgroudImage;
    CircleButton button1;
    CircleButton button2;
    private long tapStartTime;
    private long tapEndTime;
    long bpm = 120;
    int sound = 0;
    int accent = 0;
    Context context;
    MetronomeService mService;
    Timer timer;
    Vibrator vibes;
    BpmUtil bpmUtil;
    public static long BPM_CALC_RESET_DURATION = 5000;
    Animation tapAnim;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_metronome);

        bpmUtil = new BpmUtil();
        vibes = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/7segment.ttf");

        initViews();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(LOG_TAG, "In onReceive");
                String action = intent.getAction();
                Toast.makeText(getApplicationContext(), "broadcastReceiver.onReceive, action : " + action, Toast.LENGTH_SHORT).show();
                bpm = intent.getLongExtra(Constants.METRONOME.EXTRA_BPM_KEY, 120);
                if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
                else bpmText.setText(String.format("%d", bpm));
            }
        };


    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            bpm = mService.getBpm();
            if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
            else bpmText.setText(String.format("%d", bpm));
            sound = mService.getSound();
            soundText.setText(Constants.METRONOME.SOUND_RESOURCE_NAME_LIST[sound]);
            accent = mService.getAccent();
            accentText.setText(accent + "");

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mService = null;
            mBound = false;
        }
    };

    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(Constants.METRONOME.BROADCAST_MESSAGE);
        intentFilter.addCategory(Constants.ACTION.METRONOME_BROADCAST_BPM_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver), intentFilter);
        Intent intent = new Intent(MetronomeActivity.this, MetronomeService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        bpmUtil.reset();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initViews() {
        button1 = (CircleButton) findViewById(R.id.button1);
        button2 = (CircleButton) findViewById(R.id.button2);
        bpmMinusImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_bpm_minus_img);
        bpmPlusImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_bpm_plus_img);
        accentPrevImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_accent_prev_img);
        accentNextImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_accent_next_img);
        soundPrevImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_sound_prev_img);
        soundNextImg = (AutoRepeatImageView) findViewById(R.id.activity_metronome_sound_next_img);
        bpmText = (TextView) findViewById(R.id.activity_metronome_bpm_text);
        bpmText.setTypeface(typeface);
        bpmBgText = (TextView) findViewById(R.id.activity_metronome_bpm_bg_text);
        bpmBgText.setTypeface(typeface);
        accentText = (TextView) findViewById(R.id.activity_metronome_accent_text);
        soundText = (TextView) findViewById(R.id.activity_metronome_sound_text);
        button1BackgroundImage = (ImageView) findViewById(R.id.button1BackgroundImage);
        button2BackgroudImage = (ImageView) findViewById(R.id.button2BackgroundImage);

        if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
        else bpmText.setText(String.format("%d", bpm));
        soundText.setText(Constants.METRONOME.SOUND_RESOURCE_NAME_LIST[sound]);
        accentText.setText(accent + "");

        tapAnim = AnimationUtils.loadAnimation(context, R.anim.tap_anim);
        tapAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(LOG_TAG, "tapAnim.onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(LOG_TAG, "tapAnim.onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(LOG_TAG, "tapAnim.onAnimationRepeat");
            }


        });

        bpmMinusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bpm > Constants.METRONOME.MIN_BPM) {
                    bpm--;
                    if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
                    else bpmText.setText(String.format("%d", bpm));
                }
            }
        });

        bpmPlusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bpm < Constants.METRONOME.MAX_BPM) {
                    bpm++;
                    if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
                    else bpmText.setText(String.format("%d", bpm));
                }
            }
        });

        bpmMinusImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
//                Toast.makeText(context, "bpmMinusImg touch action end", Toast.LENGTH_SHORT).show();
                if(mService != null && mService.isNotificationExist()){
                    mService.setBpm(bpm);
                }
            }
        });

        bpmPlusImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
//                Toast.makeText(context, "bpmPlusImg touch action end", Toast.LENGTH_SHORT).show();
                if(mService != null && mService.isNotificationExist()){
                    mService.setBpm(bpm);
                }
            }
        });

        soundPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound--;
                if (sound < 0) {
                    sound = Constants.METRONOME.SOUND_RESOURCE_NAME_LIST.length - 1;
                }
                soundText.setText(Constants.METRONOME.SOUND_RESOURCE_NAME_LIST[sound]);
            }
        });

        soundPrevImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
                if(mService != null && mService.isNotificationExist()){
                    mService.setSound(sound);
                }
            }
        });

        soundNextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound++;
                if (sound >= Constants.METRONOME.SOUND_RESOURCE_NAME_LIST.length) {
                    sound = 0;
                }
                soundText.setText(Constants.METRONOME.SOUND_RESOURCE_NAME_LIST[sound]);
            }
        });

        soundNextImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
                if(mService != null && mService.isNotificationExist()){
                    mService.setSound(sound);
                }
            }
        });

        accentPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accent--;
                if (accent < 0) {
                    accent = Constants.METRONOME.MAX_ACCENT;
                }
                accentText.setText(accent + "");
            }
        });

        accentPrevImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
                if(mService != null && mService.isNotificationExist()){
                    mService.setAccent(accent);
                }
            }
        });

        accentNextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accent++;
                if (accent > Constants.METRONOME.MAX_ACCENT) {
                    accent = 0;
                }
                accentText.setText(accent + "");
            }
        });

        accentNextImg.setAutoRepeatImageViewListener(new AutoRepeatImageView.AutoRepeatImageViewListener() {
            @Override
            public void onTouchActionEnd() {
                if(mService != null && mService.isNotificationExist()){
                    mService.setAccent(accent);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                isAlive = isServiceRunningInForeground(getApplicationContext(), MetronomeService.class);
                Intent intent = new Intent(MetronomeActivity.this, MetronomeService.class);
//                if (!isAlive) {
//                    intent.setAction(Constants.ACTION.METRONOME_STARTFOREGROUND_ACTION);
//                    startService(intent);
//                    Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
//                } else {
                    if(!mBound){
                        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                    }
                    if(mService.isNotificationExist()){
                        Toast.makeText(getApplicationContext(), "Metronome Service Notification Exists", Toast.LENGTH_SHORT).show();
                    }else{
                        intent.setAction(Constants.ACTION.METRONOME_STARTFOREGROUND_ACTION);
                        intent.putExtra(Constants.METRONOME.EXTRA_BPM_KEY, bpm);
                        intent.putExtra(Constants.METRONOME.EXTRA_SOUND_KEY, sound);
                        intent.putExtra(Constants.METRONOME.EXTRA_ACCENT_KEY, accent);
                        startService(intent);
                        Toast.makeText(getApplicationContext(), bpm + "Constants.ACTION.METRONOME_STARTFOREGROUND_ACTION", Toast.LENGTH_SHORT).show();
                    }

                    if(!mService.isPlaying()){
                        mService.play();
                    }
//                    stopService(intent);
//                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    Log.i(LOG_TAG, "timer.cancel()");
                    timer.cancel();
                }

                vibes.vibrate(50);
                bpmUtil.tap();
                long tapAnimDuration = 200;
                if(bpm != 0) tapAnimDuration = (1000 * 60 / bpm);
                tapAnim.setDuration(tapAnimDuration);
                int repeatCount = (int) (BPM_CALC_RESET_DURATION / tapAnimDuration) + 10;
//                tapAnim.setRepeatCount(Animation.INFINITE);
                tapAnim.setRepeatCount(repeatCount);
                tapAnim.setRepeatMode(Animation.RESTART);
                timer = new Timer("BPM UTIL TIMER", true);

                button2BackgroudImage.clearAnimation();
                button2BackgroudImage.startAnimation(tapAnim);

                if(bpmUtil.taps.size() >= 2){
                    bpm = bpmUtil.getBpm();
                    if(bpm < 100) bpmText.setText("!" + String.format("%d", bpm));
                    else bpmText.setText(String.format("%d", bpm));
                    if(mService != null && mService.isNotificationExist()){
                        mService.setBpm(bpm);
                    }
                }

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.i(LOG_TAG, "bpmUtil.reset()");
//                        bpmUtil.reset();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(LOG_TAG, "clearAnimation()");
                                bpmUtil.reset();
                                button2BackgroudImage.clearAnimation();
                            }
                        });
                    }
                }, BPM_CALC_RESET_DURATION);
            }
        });

    }
}
