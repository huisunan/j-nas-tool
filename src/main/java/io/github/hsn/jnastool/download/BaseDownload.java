package io.github.hsn.jnastool.download;

import cn.hutool.core.util.StrUtil;
import io.github.hsn.jnastool.core.Download;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.InputStream;

public abstract class BaseDownload implements Download {
    protected final String url;
    protected final String username;

    protected final String password;

    protected final OkHttpClient okHttpClient;

    protected boolean defaultDownload;

    public BaseDownload(String url, String username, String password, OkHttpClient.Builder builder) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.okHttpClient = builder.build();
    }

    @Override
    public boolean isDefault() {
        return defaultDownload;
    }

    @Override
    public void setDefault(boolean b) {
        defaultDownload = true;
    }
}
