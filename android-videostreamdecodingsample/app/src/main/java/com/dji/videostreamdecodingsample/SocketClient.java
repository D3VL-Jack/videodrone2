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
import java.nio.charset.StandardCharsets;

public class SocketClient extends AsyncTask<byte[], Void, Void> {
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    private static final int SERVERPORT = 8888;

    @Override
    protected Void doInBackground(byte[]... voids) {

        byte[] array = voids[0];
        byte[] ip = voids[1];
        String ip_address = new String(ip, StandardCharsets.UTF_8); // for UTF-8 encoding

        try {
            s = new Socket(ip_address, 8888);
            //s = new Socket("192.168.1.91", 8888);

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
