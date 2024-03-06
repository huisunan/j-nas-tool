package io.github.hsn.jnastool;

import io.github.hsn.jnastool.download.QBittorrentDownload;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.shaded.org.apache.commons.lang3.mutable.MutableObject;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class TestUtil {
    public final static Pattern passwordPattern = Pattern.compile("session: (.*?)\\n");

    public String crateQBittorrentContainer() {
        MutableObject<String> passwordRef = new MutableObject<>(null);
        GenericContainer<?> qbContainer = new GenericContainer<>(DockerImageName.parse("lscr.io/linuxserver/qbittorrent:latest"));
        qbContainer.withEnv("PUID", "1000");
        qbContainer.withEnv("PGID", "1000");
        qbContainer.withEnv("TZ", "Asia/Shanghai");
        qbContainer.withEnv("WEBUI_PORT", "8085");
        qbContainer.withEnv("TORRENTING_PORT", "6881");
        qbContainer.setPortBindings(List.of("8085:8085"));
        qbContainer.addExposedPort(8085);
        qbContainer.waitingFor(Wait.forHttp("/").forStatusCode(200));
        qbContainer.withLogConsumer(outputFrame -> {
            Matcher matcher = passwordPattern.matcher(outputFrame.getUtf8String());
            if (matcher.find()) {
                passwordRef.setValue(matcher.group(1));
                log.info("password:{}", passwordRef.getValue());
            }
        });
        qbContainer.start();
        return passwordRef.getValue();
    }

    public QBittorrentDownload createQBittorrentDownload(String password) {
        return new QBittorrentDownload(
                "http://127.0.0.1:8085",
                "admin",
                password,
                createOkHttpBuilder()
        );
    }

    public OkHttpClient.Builder createOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder;
    }
}
