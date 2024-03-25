package it.unibo.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import it.unibo.api.BrickWall;

public class BrickWallImpl implements BrickWall {

    private Set<Brick> wall;
    private byte[] seed;
    private int width;
    private int height;

    public BrickWallImpl(int width, int height, String seed) {
        this.width = width;
        this.height = height;
        this.seed = seed.getBytes();
    }

    public BrickWallImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.seed = createSeed();
    }

    @Override
    public void generateLayout() {
        this.resetLayout();
    }

    private byte[] createSeed() {

        String timestamp = String.valueOf(System.currentTimeMillis());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");

            return digest.digest(timestamp.getBytes());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can't find algorithm SHA-1", e);
        }
    }

    @Override
    public void resetLayout() {
        this.wall = new HashSet<Brick>();
    }

    @Override
    public void shiftLayout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shiftLayout'");
    }

    @Override
    public void removeDeathBricks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeDeathBricks'");
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setSeed(String seed) {
        this.seed = seed.getBytes();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public String getSeed() {
        StringBuilder hexString = new StringBuilder();
        for (byte b : seed) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public Set<Brick> getWall() {
        return wall;
    }
}
