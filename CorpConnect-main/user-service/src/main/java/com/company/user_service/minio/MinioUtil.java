package com.company.user_service.minio;



import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;

@Component
public class MinioUtil {

    private final MinioClient minioClient;
    private final String bucket;

    public MinioUtil(MinioClient minioClient, @Value("${minio.bucket}") String bucket) {
        this.minioClient = minioClient;
        this.bucket = bucket;
    }

    public String presignedPutObject(String objectKey, int expirySeconds) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(bucket)
                .object(objectKey)
                .expiry(expirySeconds, TimeUnit.SECONDS)
                .build();
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create presigned PUT url", e);
        }
    }

    public String presignedGetObject(String objectKey, int expirySeconds) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(objectKey)
                .expiry(expirySeconds, TimeUnit.SECONDS)
                .build();
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create presigned GET url", e);
        }
    }

    public boolean objectExists(String objectKey) {
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(objectKey).build());
            return true;
        } catch (ErrorResponseException e) {
            if ("NoSuchKey".equals(e.errorResponse().code())) return false;
            throw new RuntimeException(e);
        } catch (Exception e) {
            return false;
        }
    }
}

