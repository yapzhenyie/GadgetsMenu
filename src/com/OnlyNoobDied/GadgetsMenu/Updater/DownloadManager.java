package com.OnlyNoobDied.GadgetsMenu.Updater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadManager {

	public static File download(String url, File file) {
		try {
			URLConnection localURLConnection = new URL(url).openConnection();
			localURLConnection.connect();
			InputStream in = localURLConnection.getInputStream();
			FileOutputStream fout = new FileOutputStream(file);
			int i = 0;
			while (i != -1) {
				i = in.read();
				if (i != -1) {
					fout.write(i);
				}
			}
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
			return file;
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return null;
	}
}
