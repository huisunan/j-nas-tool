package io.github.hsn.jnastool.torrent;

import lombok.Getter;

import java.util.List;

@Getter
public record TorrentFile(Long fileLength, List<String> fileDirs) {

}
