package com.company.tickert_service.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Objects;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;

@Component
public class MinioUtil {

    private final MinioClient minioClient;
    private final String bucket = "company-files";

    public MinioUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Create bucket automatically if it doesn't exist
     */
    @PostConstruct
    public void initBucket() {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucket).build()
            );

            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucket).build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MinIO bucket: " + bucket, e);
        }
    }

    /**
     * Generate presigned PUT url to upload file
     */
    public String presignPut(String objectKey, int expirySeconds) {
        expirySeconds = validateExpiry(expirySeconds);

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucket)
                            .object(objectKey)
                            .expiry(expirySeconds)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate presigned PUT URL for object: " + objectKey, e);
        }
    }

    /**
     * Generate presigned GET url to download file
     */
    public String presignGet(String objectKey, int expirySeconds) {
        expirySeconds = validateExpiry(expirySeconds);

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectKey)
                            .expiry(expirySeconds)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate presigned GET URL for object: " + objectKey, e);
        }
    }

    /**
     * Check object existence in bucket
     */
    public boolean objectExists(String objectKey) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectKey)
                            .build()
            );
            return true;
        } catch (ErrorResponseException ex) {
            return false; // object not found
        } catch (Exception e) {
            throw new RuntimeException("Failed to check object existence: " + objectKey, e);
        }
    }

    private int validateExpiry(int expirySeconds) {
        int max = 604800; // 7 days

        if (expirySeconds < 1) {
            return 60; // default
        }

        return Math.min(expirySeconds, max);
    }
}
