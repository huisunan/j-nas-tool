package io.github.hsn.jnastool.core;

import io.github.hsn.jnastool.core.DownloadInfo;

public interface Download {

    void download(DownloadInfo downloadInfo) throws Exception;

    boolean test();

    boolean isDefault();

    void setDefault(boolean b);
}
