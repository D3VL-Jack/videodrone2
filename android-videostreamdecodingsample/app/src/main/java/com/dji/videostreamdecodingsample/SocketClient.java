package com.dji.videostreamdecodingsample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient extends AsyncTask<byte[], Void, Void> {
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    private static final int SERVERPORT = 8888;
    private static final String SERVER_IP = "192.168.1.91";

    @Override
    protected Void doInBackground(byte[]... voids) {

        byte[] array = voids[0];

        try {
            s = new Socket("192.168.1.91", 8888);

            OutputStream out = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeInt(array.length);
            dos.write(array, 0, array.length);
            dos.flush();
            dos.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
