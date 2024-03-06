package io.github.hsn.jnastool.core;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;

public class NasManager {

    private NasConfig nasConfig;


    public void download(DownloadContext downloadContext) throws Exception {
        Download download = downloadContext.getDownload();
        if (download == null && nasConfig != null) {
            download = CollUtil.emptyIfNull(nasConfig.getDownloadList()).stream().filter(Download::isDefault).findFirst().orElse(null);
        }
        if (download == null) {
            throw new IllegalArgumentException("未配置默认下载器");
        }
        for (DownloadInfo downloadInfo : downloadContext.getDownloadInfos()) {
            download.download(downloadInfo);
        }
    }

}
