package com.nwhhades.common.glide;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MyRoundedCorners extends BitmapTransformation {

    private static final String ID = "com.nwhhades.common.glide.MyRoundedCorners";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int roundingRadius;

    public MyRoundedCorners(int roundingRadius) {
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
        this.roundingRadius = roundingRadius;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        toTransform = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return TransformationUtils.roundedCorners(pool, toTransform, roundingRadius);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MyRoundedCorners) {
            MyRoundedCorners other = (MyRoundedCorners) o;
            return roundingRadius == other.roundingRadius;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(), Util.hashCode(roundingRadius));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

        byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
        messageDigest.update(radiusData);
    }

}
