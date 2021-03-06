package com.example.sudo.lv4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static android.R.attr.category;

/**
 * Created by Sudo on 27.4.2017..
 */

public class CitajRss extends AsyncTask<Void, Void, Void> {

    Context context;
    private final String BUG_URL = "http://www.bug.hr/rss/vijesti/";
    ProgressDialog progressDialog;

    ArrayList<ItemNews> itemNews;

    RecyclerView recyclerView;
    URL url;

    String category;
    public CitajRss(Context context, RecyclerView recyclerView, String category) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.category= category;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Učitavanje");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        AdapterRss adapter = new AdapterRss(context, itemNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(GetData());
        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            itemNews = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(0);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node curentChild = items.item(i);
                if (curentChild.getNodeName().equalsIgnoreCase("item")) {
                    ItemNews item = new ItemNews();
                    NodeList itemchild = curentChild.getChildNodes();
                    for (int j = 0; j < itemchild.getLength(); j++) {
                        Node curent = itemchild.item(j);
                        if (curent.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(curent.getTextContent());
                        } else if (curent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(curent.getTextContent());
                        } else if (curent.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(curent.getTextContent());
                        } else if (curent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(curent.getTextContent());
                        } else if (curent.getNodeName().equalsIgnoreCase("category")) {
                            item.setCategory(curent.getTextContent());
                        } else if (curent.getNodeName().equalsIgnoreCase("enclosure")) {
                            String url = curent.getAttributes().item(1).getTextContent();
                            item.setImage(url);
                        }
                    }
                    if(category.equals(item.getCategory())) {
                        itemNews.add(item);
                    }else if(category.isEmpty()){
                        itemNews.add(item);
                    }


                }
            }
        }
    }


    public Document GetData() {
        try {
            url = new URL(BUG_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
