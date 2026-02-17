package com.company.hiring_service.config;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.http.Method;

@Component
public class MinioUtil {
    private final MinioClient minioClient;
    private final String bucket;

    public MinioUtil(MinioClient minioClient, org.springframework.core.env.Environment env) {
        this.minioClient = minioClient;
        this.bucket = env.getProperty("minio.bucket", "company-files");
    }

    public String presignPut(String objectKey, int expirySeconds) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucket)
                        .object(objectKey)
                        .method(Method.PUT)
                        .expiry(expirySeconds, TimeUnit.SECONDS)
                        .build()
        );
    }

    public String presignGet(String objectKey, int expirySeconds) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucket)
                        .object(objectKey)
                        .method(Method.GET)
                        .expiry(expirySeconds, TimeUnit.SECONDS)
                        .build()
        );
    }

    public boolean objectExists(String objectKey) {
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(objectKey).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}