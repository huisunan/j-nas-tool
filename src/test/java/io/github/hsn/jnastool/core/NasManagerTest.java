package io.github.hsn.jnastool.core;

import io.github.hsn.jnastool.download.QBittorrentDownload;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NasManagerTest {

    @Test
    void download() throws Exception {
        NasManager nasManger = NasManager.getNasManger();
        NasContext nasContext = new NasContext();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        QBittorrentDownload qBittorrentDownload = new QBittorrentDownload(
                "http://127.0.0.1:8085",
                "admin",
                "password",
                builder
        );
        nasContext.setDownload(qBittorrentDownload);
        DownloadInfo downloadInfo = new DownloadInfo(
                "https://mikanani.me/Download/20240302/28fe34731d359240b75d2f0dd257a6185522090e.torrent"
        );
        downloadInfo.setTags(List.of("BT"));
        downloadInfo.setSavePath("/volume1/media/download/av");
        nasContext.setDownloadInfos(Collections.singletonList(downloadInfo));


        nasManger.download(nasContext);
    }
}