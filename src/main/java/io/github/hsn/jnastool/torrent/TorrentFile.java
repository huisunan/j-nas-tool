package io.github.hsn.jnastool.torrent;

import lombok.Getter;

import java.util.List;

public record TorrentFile(Long fileLength, List<String> fileDirs) {

}
