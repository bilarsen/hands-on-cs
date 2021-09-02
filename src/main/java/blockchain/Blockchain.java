package blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Blockchain {

    private final List<Block> blocks;

    private final Integer hashZerosCount;

    private final Random random;

    private int previousBlockIndex;

    public Blockchain(Integer hashZerosCount, String data) {
        this.hashZerosCount = hashZerosCount;
        blocks = new ArrayList<>();
        random = new Random();
        Block initial = new Block(0, "00000", data, hashZerosCount != null ? random.nextInt() : null);
        initial.setHash(hashBlock(initial));
        blocks.add(initial);
        previousBlockIndex = 0;
    }

    private String hashBlock(Block block) {
        try {
            return hashBlock(block.getIndex(), block.getPreviousHash(), block.getTime(), block.getData(), block.getNonce());
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private String hashBlock(long index, String previousHash, LocalDateTime time, String data, Integer nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Long.toBinaryString(index).getBytes());
        md.update(previousHash.getBytes());
        md.update(time.toString().getBytes());
        md.update(data.getBytes());
        if (nonce != null) {
            do {
                md.update(nonce.toString().getBytes());
                nonce = random.nextInt();
            } while (!checkZerosCount(Arrays.toString(md.digest())));
        }
        return Arrays.toString(md.digest());
    }

    private boolean checkZerosCount(String hash) {
        return Pattern.matches(String.format("[0]{%d}", hashZerosCount), hash.substring(hash.length() - hashZerosCount));
    }

    public Block addBlock(String data) {
        Block previousBlock = blocks.get(previousBlockIndex);
        Block block = new Block(previousBlock.getIndex() + 1,
                previousBlock.getHash(), data,
                hashZerosCount != null ? random.nextInt() : null);
        block.setHash(hashBlock(block));
        blocks.add(block);
        previousBlockIndex++;
        return block;
    }

    public Block findBlock(String hash) {
        return blocks.stream()
                .filter(block -> block.getHash().equals(hash))
                .findAny()
                .orElse(null);
    }

    public boolean isChainBroken() {
        return blocks.stream()
                .anyMatch(block -> !block.getHash().equals(hashBlock(block)));
    }

}