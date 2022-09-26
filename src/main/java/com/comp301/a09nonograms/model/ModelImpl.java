package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final List<Puzzle> puzzles;
  private int puzzleIndex;
  private final List<Clues> clues;
  private final List<ModelObserver> observers;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new NullPointerException();
    }
    this.clues = clues;
    this.puzzles = new ArrayList<>();
    this.observers = new ArrayList<>();
    this.puzzleIndex = 0;
    for (Clues clue : clues) {
      puzzles.add(new Puzzle(clue));
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    Board board = puzzles.get(puzzleIndex).getBoard();
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    Board board = puzzles.get(puzzleIndex).getBoard();
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    Board board = puzzles.get(puzzleIndex).getBoard();
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    Board board = puzzles.get(puzzleIndex).getBoard();
    board.toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    Board board = puzzles.get(puzzleIndex).getBoard();
    board.toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    Board board = puzzles.get(puzzleIndex).getBoard();
    board.clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    Board board = puzzles.get(puzzleIndex).getBoard();
    return ((BoardImpl) board).getWidth();
  }

  @Override
  public int getHeight() {
    Board board = puzzles.get(puzzleIndex).getBoard();
    return ((BoardImpl) board).getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    Clues clue = this.clues.get(puzzleIndex);
    return clue.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    Clues clue = this.clues.get(puzzleIndex);
    return clue.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    Clues clue = this.clues.get(puzzleIndex);
    return clue.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    Clues clue = this.clues.get(puzzleIndex);
    return clue.getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return this.puzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return this.puzzleIndex;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index > getPuzzleCount() || index < 0) {
      throw new IllegalArgumentException();
    } else {
      this.puzzleIndex = index;
      notifyObservers();
    }
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    this.observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    boolean rowSumSolved = true;
    boolean rowSpacesSolved = true;
    boolean columnSumSolved = true;
    boolean columnSpacesSolved = true;
    Board board = puzzles.get(puzzleIndex).getBoard();
    Clues clue = puzzles.get(puzzleIndex).getClue();
    int rowSum = 0;
    int rowShadedSum = 0;
    int columnSum = 0;
    int columnShadedSum = 0;
    for (int i = 0; i < clue.getHeight(); i++) {
      int[] row = clue.getRowClues(i);
      int expectedSpaces = -1;
      for (int x : row) {
        rowSum += x;
        if (x != 0) {
          expectedSpaces += 1;
        }
      }
      if (expectedSpaces == -1) {
        expectedSpaces = 0;
      }
      boolean firstShaded = false;
      int spaces = 0;
      for (int j = 0; j < clue.getWidth(); j++) {
        if (firstShaded) {
          if (board.isShaded(i, j) && !board.isShaded(i, j - 1)) {
            spaces += 1;
          }
        }
        if (board.isShaded(i, j)) {
          rowShadedSum += 1;
          firstShaded = true;
        }
        if (j == clue.getWidth() - 1 && spaces != expectedSpaces) {
          rowSpacesSolved = false;
        }
      }
    }
    for (int i = 0; i < clue.getWidth(); i++) {
      int[] col = clue.getColClues(i);
      int expectedSpaces = -1;
      for (int x : col) {
        columnSum += x;
        if (x != 0) {
          expectedSpaces += 1;
        }
      }
      if (expectedSpaces == -1) {
        expectedSpaces = 0;
      }
      boolean firstShaded = false;
      int spaces = 0;
      for (int j = 0; j < clue.getHeight(); j++) {
        if (firstShaded) {
          if (board.isShaded(j, i) && !board.isShaded(j - 1, i)) {
            spaces += 1;
          }
        }
        if (board.isShaded(j, i)) {
          columnShadedSum += 1;
          firstShaded = true;
        }
        if (j == clue.getHeight() - 1 && spaces != expectedSpaces) {
          columnSpacesSolved = false;
        }
      }
    }
    if (rowSum != rowShadedSum) {
      rowSumSolved = false;
    }
    if (columnSum != columnShadedSum) {
      columnSumSolved = false;
    }
    return rowSumSolved && columnSumSolved && rowSpacesSolved && columnSpacesSolved;
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  public Puzzle getPuzzle() {
    return this.puzzles.get(puzzleIndex);
  }
}
