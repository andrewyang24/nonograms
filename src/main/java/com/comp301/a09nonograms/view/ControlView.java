package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlView implements FXComponent {

  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox pane1 = new VBox();
    HBox pane2 = new HBox();

    Button nextBtn = new Button();
    nextBtn.setText("Next");

    Button prevBtn = new Button();
    prevBtn.setText("Previous");

    nextBtn.setOnAction(
        (MouseEvent) -> {
          controller.nextPuzzle();
        });

    prevBtn.setOnAction(
        (MouseEvent) -> {
          controller.prevPuzzle();
        });

    pane2.getChildren().add(prevBtn);
    pane2.getChildren().add(nextBtn);
    pane1.getChildren().add(pane2);

    Text text = new Text();
    int curr = controller.getPuzzleIndex() + 1;
    int total = controller.getPuzzleCount();
    text.setText("You are on puzzle " + curr + " of " + total);
    pane1.getChildren().add(text);
    return pane1;
  }
}
