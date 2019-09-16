package pl.kasztany.orlenfirstaid.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.activity.AfterCallActivity;
import pl.kasztany.orlenfirstaid.activity.MainActivity;
import pl.kasztany.orlenfirstaid.model.CategoryContent;
import pl.kasztany.orlenfirstaid.model.TranscriptData;
import pl.kasztany.orlenfirstaid.ui.adapter.AfterCallListAdapter;

import static android.content.Context.NOTIFICATION_SERVICE;


public class FirebaseService {
    private boolean initialized = false;
    private boolean postCallInitialized = false;
    Integer notif = 0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    void insertTranscriptToNode(String nodeName, TranscriptData transcriptData) {
        if (transcriptData.getText() != null && !transcriptData.getText().equals("")) {
            db.collection(nodeName).document(transcriptData.getAccidentId()).set(transcriptData);
        }
    }

    void insertLocationToFirestore(Location location, String address) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("date", new Date());
        docData.put("place", new GeoPoint(location.getLatitude(), location.getLongitude()));
        docData.put("street", address);
        db.collection("accidents").add(docData);
    }

    public void setOnAccidentListener(MainActivity mainActivity) {
        CollectionReference docRef = db.collection("accidents");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                queryDocumentSnapshots.getDocumentChanges().forEach(change -> {
                    if (initialized) {
                        Map<String, Object> accident = change.getDocument().getData();
                        NotificationManager notificationManager = (NotificationManager)
                                mainActivity.getSystemService(NOTIFICATION_SERVICE);
                        Notification n = new Notification.Builder(mainActivity, "notif_channel")
                                .setStyle(new Notification.BigTextStyle().bigText("Car accident on " + accident.get("street") + ". Be careful!"))
                                .setContentTitle("Accident in 100 km radius")
                                .setContentText("Car accident on " + accident.get("street") + ". Be careful!")
                                .setSmallIcon(R.drawable.roundicon)
                                .setAutoCancel(false)
                                .build();

                        String channelId = "notif_channel";
                        NotificationChannel channel = new NotificationChannel(
                                channelId,
                                "accidentChannel",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManager.createNotificationChannel(channel);
                        notificationManager.notify(++notif, n);
                    }
                });
                initialized = true;
            }
        });
    }

    public void postCallTrigger(Context context) {
        db.collection("analysis_result").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (postCallInitialized) {
                    queryDocumentSnapshots.getDocumentChanges().forEach(change -> {
                        String accidentId = change.getDocument().getId();
                        Intent intent = new Intent(context, AfterCallActivity.class);
                        intent.putExtra("ACCIDENT_ID", accidentId);
                        context.startActivity(intent);
                    });
                }
                postCallInitialized = true;
            }
        });
    }

    public void loadContent(String accidentId, AfterCallListAdapter afterCallListAdapter, Context context, ListView listView) {
        Toast.makeText(context, accidentId, Toast.LENGTH_LONG).show();
        Task<DocumentSnapshot> tasker = db.collection("analysis_result").document(accidentId).get();
        tasker.addOnCompleteListener(snap -> {
            DocumentSnapshot ds = snap.getResult();
            List<String> categories = (List) ds.getData().get("matching_categories");
            for (String cate : categories) {
                db.collection("case").document(cate).get().addOnCompleteListener(s -> {
                    DocumentSnapshot dss = s.getResult();
                    List<Map> lsofMaps = (List) dss.get("content_pl");
                    if (lsofMaps.get(0).get("type").toString().equals("video")) {
                        String url = (String) lsofMaps.get(0).get("value");
                        CategoryContent categoryContent = new CategoryContent((String) dss.get("title_pl"),
                                "video",
                                "https:" +  url);
                        afterCallListAdapter.addItem(categoryContent);
                    }
                });
            }

            listView.setAdapter(afterCallListAdapter);
        });
    }
}
