package com.letich.LetichApp;

import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.nganga.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

   private Form current;
   private Resources theme;

   public void init(Object context) {
      // use two network threads instead of one
      updateNetworkThreadCount(2);

      theme = UIManager.initFirstTheme("/theme");

      // Enable Toolbar on all Forms by default
      Toolbar.setGlobalToolbar(true);

      // Pro only feature
      Log.bindCrashProtection(true);

      addNetworkErrorListener(err -> {
         // prevent the event from propagating
         err.consume();
         if (err.getError() != null) {
            Log.e(err.getError());
         }
         Log.sendLogAsync();
         Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
      });
   }

   public void start() {
      if (current != null) {
         current.show();
         return;
      }

      TextField tf = new TextField();
      TextField tf2 = new TextField();
      Form hi = new Form("Hi World", BoxLayout.y());
      hi.add(new Label("Hi World"));

      EventBus b = new EventBus("http://localhost:9999/eventbus") {
         @Override
         protected void onConnected() {
            ping();

            registerHandler("webout", result -> {
               if (result.succeeded()) {
                  Map<String, String> map = result.result();
                  tf2.setText(map.get("message"));
               }
            });
         }

         @Override

         protected void onClose(int statusCode, String reason) {

         }

         @Override
         public void onFailure(Exception ex) {
            ex.printStackTrace();
         }

      };
      b.connect();

      Button b1 = new Button("send");

      b1.addActionListener(e -> {

         Map p = new HashMap();
         p.put("message", tf.getText());
         String jo = JSONParser.mapToJson(p);
         b.send("mobilein", jo, r
               -> {
            if (r.succeeded()) {
               System.out.println(r.result());
            }
         });
         //print();
      });

      hi.add(tf);
      hi.add(tf2);
      hi.add(b1);

      hi.show();
   }

   public void stop() {

   }

   public void destroy() {
   }

   

  

}