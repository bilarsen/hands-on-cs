package blockchain;

import java.time.LocalDateTime;

public class Block {

    private final long index;

    private final String previousHash;

    private String hash;

    private final LocalDateTime time;

    private final String data;

    private final Integer nonce;

    public Block(long index, String previousHash, String data, Integer nonce) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = nonce;
        time = LocalDateTime.now();
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public Long getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

    public Integer getNonce() {
        return nonce;
    }
}
