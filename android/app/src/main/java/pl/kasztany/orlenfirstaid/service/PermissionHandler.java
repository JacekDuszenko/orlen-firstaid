package pl.kasztany.orlenfirstaid.service;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import pl.kasztany.orlenfirstaid.activity.MainActivity;

import static android.content.Context.TELECOM_SERVICE;

public class PermissionHandler {
    private final MainActivity mainActivity;

    public PermissionHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handlePermissions() {
        if (!isDefaultPhoneCallApp(mainActivity)) {
            setThisAppAsDefault();
        }
        requestMultipleManifestPermissions();
    }

    private void setThisAppAsDefault() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
                    mainActivity.getPackageName());
            mainActivity.startActivity(intent);
        } else {
            Toast.makeText(mainActivity, "Android 6.0 required to use this app. Update it please", Toast.LENGTH_LONG).show();
        }
    }

    private void requestMultipleManifestPermissions() {
        Dexter.withActivity(mainActivity)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CALL_PHONE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Log.i("PERMISSIONS OK", "All permissions are granted by user!");
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(mainActivity.getApplicationContext(), "Permission Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void openSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", mainActivity.getPackageName(), null);
                intent.setData(uri);
                mainActivity.startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private boolean isDefaultPhoneCallApp(MainActivity mainActivity) {
        TelecomManager manger = (TelecomManager) mainActivity.getSystemService(TELECOM_SERVICE);
        if (manger != null && manger.getDefaultDialerPackage() != null) {
            return manger.getDefaultDialerPackage().equals(mainActivity.getPackageName());
        }
        return false;
    }
}
