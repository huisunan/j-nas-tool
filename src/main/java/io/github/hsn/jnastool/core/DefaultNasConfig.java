package io.github.hsn.jnastool.core;

import lombok.Data;

import java.util.List;

@Data
public class DefaultNasConfig implements NasConfig {
    /**
     * 下载器列表
     */
    protected List<Download> downloadList;
}
