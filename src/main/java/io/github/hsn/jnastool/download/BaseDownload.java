package io.github.hsn.jnastool.download;

import cn.hutool.core.util.StrUtil;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.InputStream;

public abstract class BaseDownload {
    protected final String url;
    protected final String username;

    protected final String password;

    protected final OkHttpClient okHttpClient;

    public BaseDownload(String url, String username, String password, OkHttpClient.Builder builder) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.okHttpClient = builder.build();
    }




}
