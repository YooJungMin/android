package android.radiant.com.test;

    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.webkit.JavascriptInterface;

    import java.io.BufferedReader;
    import java.io.DataOutputStream;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;

    /**
     * Created by jungmin on 2015-12-16.
     */
    public class MainActivity extends AppCompatActivity {
        String xmlFilePath = "/system/etc/mixer_paths_rt5633.xml";
        String km = "/storage/usb0";
        FileInputStream inputStream = null;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
/*
        File apkFile = new File("/system/etc");
        File decFile = new File("/storage/usb0/mixer_paths_rt5633.xml");
        String A = decFile.toString();
        String B = apkFile.toString();*/

            try
            {


                String line;
                OutputStream stdin = null;
                InputStream stderr = null;
                InputStream stdout = null;

            /*process = Runtime.getRuntime ().exec("su\n");
            process.waitFor();*/
/*            String old_file = "/storage/usb0/sample.txt";
            String new_file = "/system/etc/sample.txt"; //FlappyBird.xml
            process = Runtime.getRuntime().exec ("su -c" + "cp " + old_file + " " +  new_file);*/
                Process proces = Runtime.getRuntime().exec("su");
                Process process = Runtime.getRuntime().exec("rm /system/etc/sample.txt");
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                StringBuilder log=new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    Log.i("entered loop", log.toString());
                    log.append(line + "\n");
                }
                Log.i("app", log.toString());








            }
            catch (Exception ex) {
                Log.w("Error ejecutando el comando Root", ex);
            }

        }
    }




