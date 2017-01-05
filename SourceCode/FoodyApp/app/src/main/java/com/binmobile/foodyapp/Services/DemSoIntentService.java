package com.binmobile.foodyapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DemSoIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_SO_LE = "com.binmobile.foodyapp.Services.action.SoLe";
    public static final String ACTION_SO_CHAN = "com.binmobile.foodyapp.Services.action.SoChan";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.binmobile.foodyapp.Services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.binmobile.foodyapp.Services.extra.PARAM2";

    public DemSoIntentService() {
        super("DemSoIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSoLe(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DemSoIntentService.class);
        intent.setAction(ACTION_SO_LE);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSoChan(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DemSoIntentService.class);
        intent.setAction(ACTION_SO_CHAN);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SO_LE.equals(action)) {
                handleSoLe();
            } else if (ACTION_SO_CHAN.equals(action)) {
                handleSoChan();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    int i=0,j=0;
    private void handleSoLe() {
        // TODO: Handle action Foo
        final Intent intent=new Intent(ACTION_SO_LE);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    i++;
                    intent.putExtra("data",i);
                    sendBroadcast(intent);
                    Log.d("dem","dem so le:"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleSoChan() {
        final Intent intent=new Intent(ACTION_SO_CHAN);
        intent.setAction(ACTION_SO_CHAN);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    j+=2;
                    intent.putExtra("data",i);
                    sendBroadcast(intent);
                    Log.d("dem","dem so chan:"+j);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
