package io.github.hsn.jnastool.download;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.github.hsn.jnastool.core.Download;
import io.github.hsn.jnastool.core.DownloadInfo;
import io.github.hsn.jnastool.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.IOException;


@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public class QBittorrentDownload extends BaseDownload implements Download {

    public static final String SUCCESS = "Ok.";

    public QBittorrentDownload(String url, String username, String password, OkHttpClient.Builder builder) {
        super(url, username, password, builder);
    }

    @Override
    public void download(DownloadInfo downloadInfo) throws Exception {
        String cookie = getCookie();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MediaType.get(org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE));
        bodyBuilder.addFormDataPart("urls", downloadInfo.getTorrentUrl());
        bodyBuilder.addFormDataPart("savepath", downloadInfo.getSavePath());
        if (CollUtil.isNotEmpty(downloadInfo.getTags()))
            bodyBuilder.addFormDataPart("tags", String.join(",", downloadInfo.getTags()));
        Request request = new Request.Builder()
                .url(url + "/api/v2/torrents/add")
                .header(HttpHeaders.REFERER, url)
                .header(HttpHeaders.COOKIE, cookie)
                .post(bodyBuilder.build())
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            assertSuccess(response);
        }


    }


    @Override
    public boolean test() {
        try {
            String cookie = getCookie();
            return StrUtil.isNotBlank(cookie);
        } catch (Exception e) {
            log.error("连接失败", e);
            return false;
        }
    }

    protected String getCookie() throws Exception {
        Request request = new Request.Builder()
                .url(url + "/api/v2/auth/login")
                .header(HttpHeaders.REFERER, url)
                .post(
                        new FormBody.Builder()
                                .add("username", username)
                                .add("password", password)
                                .build()
                )
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() != HttpStatus.OK.value()) {
                throw new ApiException("状态码异常");
            }
            assertSuccess(response);
            return StrUtil.subBefore(response.header("set-cookie"), ";", false);
        }

    }

    protected void assertSuccess(Response response) throws IOException {
        Assert.notNull(response.body());
        Assert.equals(SUCCESS, response.body().string());

    }

}
