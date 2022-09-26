package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;

import java.util.concurrent.ThreadLocalRandom;

public class ControllerImpl implements Controller {

  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new NullPointerException();
    } else {
      this.model = model;
    }
  }

  @Override
  public Clues getClues() {
    return ((ModelImpl) model).getPuzzle().getClue();
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int curr = model.getPuzzleIndex();
    if (curr == model.getPuzzleCount() - 1) {
      this.model.setPuzzleIndex(0);
    } else {
      this.model.setPuzzleIndex(curr + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    int curr = model.getPuzzleIndex();
    if (curr == 0) {
      this.model.setPuzzleIndex(model.getPuzzleCount() - 1);
    } else {
      this.model.setPuzzleIndex(curr - 1);
    }
  }

  @Override
  public void randPuzzle() {
    int curr = model.getPuzzleIndex();
    int rand = ThreadLocalRandom.current().nextInt(0, model.getPuzzleCount());
    while (curr == rand) {
      rand = ThreadLocalRandom.current().nextInt(0, model.getPuzzleCount());
    }
    this.model.setPuzzleIndex(rand);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
