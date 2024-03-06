package io.github.hsn.jnastool.torrent.torrent;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Created by christophe on 16.01.15.
 */
@Setter
@Getter
public class Torrent
{
    private String announce;
    private String name;
    private Long pieceLength;
    private byte[] piecesBlob;
    private List<String> pieces;
    private boolean singleFileTorrent;
    private Long totalSize;
    private List<TorrentFile> fileList;
    private String comment;
    private String createdBy;
    private Date creationDate;
    private List<String> announceList;
    private String infoHash;
}
