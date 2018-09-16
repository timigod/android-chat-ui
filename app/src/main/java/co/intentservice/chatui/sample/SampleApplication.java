package co.intentservice.chatui.sample;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        long diskCacheLowSizeBytes = 1024 * 1024 * 20; // 100 MB
        long diskCacheSizeBytes = 1024 * 1024 * 50; // 100 MB

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setMaxCacheSizeOnLowDiskSpace(diskCacheLowSizeBytes)
                .setMaxCacheSize(diskCacheSizeBytes)
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setDiskCacheEnabled(true)
                .setHttpConnectionTimeout(30000)
                .build();

        Fresco.initialize(this, config);
    }
}

