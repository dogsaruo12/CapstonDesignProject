package kr.ac.yjc.wdj.capstondesignproject;


import android.content.Context;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;


public class Util {

/*    public static boolean isFingerprintAuthAvailable(Context context) {

        FingerprintManagerCompat mFingerprintManager = FingerprintManagerCompat.from(context);


        if (mFingerprintManager.hasEnrolledFingerprints() && mFingerprintManager.isHardwareDetected()) {
            return true;
        } else {
            return false;
        }
    }*/


    public static FingerprintManagerCompat getFingerprintManagerCompat(Context context){
        FingerprintManagerCompat mFingerprintManager;
        mFingerprintManager = FingerprintManagerCompat.from(context);
        return mFingerprintManager;
    }

}