package com.vartdalen.imagestoreweb.service;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.ParallelTransferOptions;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.vartdalen.imagestoreweb.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;

@Service
public class BlobService {
    private final BlobContainerAsyncClient blobContainerAsyncClient;

    @Autowired
    public BlobService( @Value("https://${azure.blob-storage.address}") String BASE_URL,
                        @Value("${azure.blob-storage.account.name}") String ACCOUNT_NAME,
                        @Value("${azure.blob-storage.account.key}") String ACCOUNT_KEY,
                        @Value("${azure.blob-storage.container.name}") String CONTAINER_NAME
                        ) {
        this.blobContainerAsyncClient = new BlobServiceClientBuilder()
            .endpoint(BASE_URL)
            .credential(new StorageSharedKeyCredential(ACCOUNT_NAME, ACCOUNT_KEY))
            .buildAsyncClient()
            .getBlobContainerAsyncClient(CONTAINER_NAME);
    }

    public void post(Image image) {
        blobContainerAsyncClient
            .getBlobAsyncClient(createBlobName(image))
            .upload(createFlux(image.getBytes()), createTransferOptions((long)image.getBytes().length))
            .subscribe();
    }

    public void delete(Image image) {
        blobContainerAsyncClient
            .getBlobAsyncClient(createBlobName(image))
            .delete()
            .subscribe();
    }
    
    private String createBlobName(Image image) {
        return image.getId() + "." + image.getExtension().toString().toLowerCase();
    }

    private Flux<ByteBuffer> createFlux(byte[] bytes) {
        return Flux.just(ByteBuffer.wrap(bytes));
    }

    private ParallelTransferOptions createTransferOptions(Long blockSize) {
        return new ParallelTransferOptions()
            .setBlockSizeLong(blockSize)
            .setMaxConcurrency(5);
    }
}
