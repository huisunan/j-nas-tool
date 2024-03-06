package io.github.hsn.jnastool.core;

import lombok.Data;

import java.util.List;

@Data
public class NasContext {
    /**
     * 种子下载列表
     */
    private List<DownloadInfo> downloadInfos;

    /**
     * 下载器
     */
    private Download download;



}
