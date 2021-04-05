package com.vartdalen.imagestoreweb.service;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.ParallelTransferOptions;
import com.azure.storage.common.StorageSharedKeyCredential;

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

    public void post(long id, byte[] bytes, long length) {
        ParallelTransferOptions parallelTransferOptions = new ParallelTransferOptions()
            .setBlockSizeLong(length)
            .setMaxConcurrency(5);
        Flux<ByteBuffer> flux = Flux.just(ByteBuffer.wrap(bytes));
        blobContainerAsyncClient
            .getBlobAsyncClient(id + ".jpg")
            .upload(flux, parallelTransferOptions)
            .subscribe();
    }

    public void delete(long id) {
        blobContainerAsyncClient
            .getBlobAsyncClient(id + ".jpg")
            .delete()
            .subscribe();
    }
}
