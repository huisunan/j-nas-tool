package io.github.hsn.jnastool.core;

public class NasManager {
    private static final NasManager INSTANCE = new NasManager();

    private NasManager() {
    }

    public static NasManager getNasManger() {
        return INSTANCE;
    }

    public void download(DownloadContext downloadContext) throws Exception {
        Download download = downloadContext.getDownload();
        for (DownloadInfo downloadInfo : downloadContext.getDownloadInfos()) {
            download.download(downloadInfo);
        }
    }
}
