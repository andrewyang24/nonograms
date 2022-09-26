package com.comp301.a09nonograms.model;

public class Puzzle {

  private final Clues clue;
  private final Board board;

  public Puzzle(Clues clue) {
    if (clue == null) {
      throw new NullPointerException();
    }
    this.clue = clue;
    this.board = new BoardImpl(clue.getHeight(), clue.getWidth());
  }

  public Clues getClue() {
    return this.clue;
  }

  public Board getBoard() {
    return this.board;
  }
}
