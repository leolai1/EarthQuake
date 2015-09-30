package com.leolai.earthquake;

import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 15-9-29.
 */
public class EarthQuakeListFragement extends ListFragment {
    private static final String TAG = "EarthQuakeListFragement";
    private Handler mHandler;

    ArrayAdapter<Quake> aa;
    ArrayList<Quake> earthQuakes;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        aa = new ArrayAdapter<Quake>(getActivity(), android.R.layout.simple_list_item_1,
                earthQuakes);
        setListAdapter(aa);
    }

    public void refleshEarthQuakes() {
        URL mUrl;
        try {
            String mQuakeFeed = getString(R.string.quake_feed);
            mUrl = new URL(mQuakeFeed);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            int reposeCode = connection.getResponseCode();

            if (reposeCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();

                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document dom = documentBuilder.parse(in);
                Element docEle =  dom.getDocumentElement();

                earthQuakes.clear();

                NodeList nl = docEle.getElementsByTagName("entry");
                if (nl == null || nl.getLength() <= 0)
                    return;
                for (int i = 0; i < nl.getLength(); i ++) {
                    Element entry = (Element)nl.item(i);
                    Element title = (Element)entry.getElementsByTagName("title").item(0);
                    Element g = (Element)entry.getElementsByTagName("georss:point").item(0);
                    Element when = (Element)entry.getElementsByTagName("updated").item(0);
                    Element link = (Element)entry.getElementsByTagName("link").item(0);




                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
