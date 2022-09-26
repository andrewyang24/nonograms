package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {

  private final int height;
  private final int width;
  private int[][] grid;

  public BoardImpl(int height, int width) {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException();
    } else {
      this.height = height;
      this.width = width;
      int[][] temp = new int[height][width];
      this.grid = temp;
    }
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row > height || row < 0 || col > width || col < 0) {
      throw new IllegalArgumentException();
    } else {
      return this.grid[row][col] == 1;
    }
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row > height || row < 0 || col > width || col < 0) {
      throw new IllegalArgumentException();
    } else {
      return this.grid[row][col] == 2;
    }
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row > height || row < 0 || col > width || col < 0) {
      throw new IllegalArgumentException();
    } else {
      return this.grid[row][col] == 0;
    }
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row > height || row < 0 || col > width || col < 0) {
      throw new IllegalArgumentException();
    } else {
      if (this.grid[row][col] == 1) {
        this.grid[row][col] = 0;
      } else {
        this.grid[row][col] = 1;
      }
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row > height || row < 0 || col > width || col < 0) {
      throw new IllegalArgumentException();
    } else {
      if (this.grid[row][col] == 2) {
        this.grid[row][col] = 0;
      } else {
        this.grid[row][col] = 2;
      }
    }
  }

  @Override
  public void clear() {
    int[][] temp = new int[height][width];
    this.grid = temp;
  }
}
