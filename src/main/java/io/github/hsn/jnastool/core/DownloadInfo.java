package io.github.hsn.jnastool.core;

import lombok.Data;

import java.util.List;

@Data
public class DownloadInfo {
    /**
     * 种子url
     */
    private final String torrentUrl;

    /**
     * 文件标签
     */
    private List<String> tags;

    /**
     * 存储路径
     */
    private String savePath;
}
