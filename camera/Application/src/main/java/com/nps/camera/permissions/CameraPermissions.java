package com.nps.camera.permissions;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;

public class CameraPermissions {
    /**
     * Request code for camera permissions.
     */
    public  static final int REQUEST_CAMERA_PERMISSIONS = 1;

    private Activity activity;
    private Fragment fragment;

    /**
     * Permissions required to take a picture.
     */
    public static final String[] CAMERA_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    public CameraPermissions(Activity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

    /**
     * Tells whether all the necessary permissions are granted to this app.
     *
     * @return True if all the required permissions are granted.
     */
    public boolean hasAllPermissionsGranted() {
        for (String permission : CAMERA_PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets whether you should show UI with rationale for requesting the permissions.
     *
     * @return True if the UI should be shown.
     */
    private boolean shouldShowRationale(Fragment fragment) {
        for (String permission : CAMERA_PERMISSIONS) {
            if (FragmentCompat.shouldShowRequestPermissionRationale(fragment, permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Requests permissions necessary to use camera and save pictures.
     */
    public void requestCameraPermissions() {
        if (shouldShowRationale()) {
            PermissionConfirmationDialog.newInstance().show(fragment.getChildFragmentManager(), "dialog");
        } else {
            FragmentCompat.requestPermissions(fragment, CAMERA_PERMISSIONS, REQUEST_CAMERA_PERMISSIONS);
        }
    }


    /**
     * Gets whether you should show UI with rationale for requesting the permissions.
     *
     * @return True if the UI should be shown.
     */
    private boolean shouldShowRationale() {
        for (String permission : CAMERA_PERMISSIONS) {
            if (FragmentCompat.shouldShowRequestPermissionRationale(fragment, permission)) {
                return true;
            }
        }
        return false;
    }


}
