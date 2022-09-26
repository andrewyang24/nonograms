package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {

  private final int height;
  private final int width;
  private final int[][] rowClues;
  private final int[][] colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new NullPointerException();
    } else {
      this.height = rowClues.length;
      this.width = colClues.length;
      this.rowClues = rowClues;
      this.colClues = colClues;
    }
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int[] getRowClues(int index) {
    if (index > this.height || index < 0) {
      throw new IllegalArgumentException();
    } else {
      return this.rowClues[index];
    }
  }

  @Override
  public int[] getColClues(int index) {
    if (index > this.width || index < 0) {
      throw new IllegalArgumentException();
    } else {
      return this.colClues[index];
    }
  }

  @Override
  public int getRowCluesLength() {
    return this.rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return this.colClues[0].length;
  }
}
