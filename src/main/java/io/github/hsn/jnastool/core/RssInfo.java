package io.github.hsn.jnastool.core;

import lombok.Data;

@Data
public class RssInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 种子地址
     */
    private String torrentUrl;
}
