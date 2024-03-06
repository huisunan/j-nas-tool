package io.github.hsn.jnastool.core;

import io.github.hsn.jnastool.TestUtil;
import io.github.hsn.jnastool.download.QBittorrentDownload;
import io.github.hsn.jnastool.torrent.torrent.Torrent;
import io.github.hsn.jnastool.torrent.torrent.TorrentParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Slf4j
@SpringBootTest
class NasManagerTest {


    public static String password = null;

    @BeforeAll
    public static void before() {
        password = TestUtil.crateQBittorrentContainer();

    }

    @Test
    void download() throws Exception {
        NasManager nasManger = NasManager.getNasManger();
        DownloadContext downloadContext = new DownloadContext();
        downloadContext.setDownload(TestUtil.createQBittorrentDownload(password));
        DownloadInfo downloadInfo = new DownloadInfo(
                "https://mikanani.me/Download/20240302/28fe34731d359240b75d2f0dd257a6185522090e.torrent"
        );
        downloadInfo.setTags(List.of("BT"));
        downloadInfo.setSavePath("/volume1/media/download/av");
        downloadContext.setDownloadInfos(Collections.singletonList(downloadInfo));
        nasManger.download(downloadContext);
    }

    @Test
    void test() throws IOException {

    }
}