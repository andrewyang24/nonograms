package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MessageView implements FXComponent {

  private final Controller controller;

  public MessageView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox pane = new HBox();

    Text text = new Text();
    if (controller.isSolved()) {
      text.setText("Congratulations! You Solved the Puzzle");
    } else {
      text.setText("Use the clues to solve the puzzle!");
    }
    pane.getChildren().add(text);
    return pane;
  }
}
