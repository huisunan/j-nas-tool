package io.github.hsn.jnastool.core;

public class NasManager {
    private static final NasManager INSTANCE = new NasManager();

    private NasManager() {
    }

    public static NasManager getNasManger() {
        return INSTANCE;
    }

    public void download(NasContext nasContext) throws Exception {
        Download download = nasContext.getDownload();
        for (DownloadInfo downloadInfo : nasContext.getDownloadInfos()) {
            download.download(downloadInfo);
        }
    }
}
