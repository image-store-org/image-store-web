package com.vartdalen.imagestoreweb.service;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.common.StorageSharedKeyCredential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlobService {
    private final BlobContainerClient blobContainerClient;

    @Autowired
    public BlobService( @Value("https://${azure.blob-storage.address}") String BASE_URL,
                        @Value("${azure.blob-storage.account.name}") String ACCOUNT_NAME,
                        @Value("${azure.blob-storage.account.key}") String ACCOUNT_KEY,
                        @Value("${azure.blob-storage.container.name}") String CONTAINER_NAME
                        ) {
        this.blobContainerClient = new BlobServiceClientBuilder()
            .endpoint(BASE_URL)
            .credential(new StorageSharedKeyCredential(ACCOUNT_NAME, ACCOUNT_KEY))
            .buildClient()
            .getBlobContainerClient(CONTAINER_NAME);
    }

    public List<BlobItem> get() {
        return blobContainerClient
            .listBlobs()
            .stream()
            .collect(Collectors.toList());
    }

    public void post(long id, InputStream data, long length) {
        blobContainerClient
            .getBlobClient(id + ".jpg")
            .upload(data, length);
    }

    public void delete(long id) {
        blobContainerClient
            .getBlobClient(id + ".jpg")
            .delete();
    }
}
