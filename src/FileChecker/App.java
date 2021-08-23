package FileChecker;

import java.io.FileInputStream;
import java.security.MessageDigest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {
    String path = "";
    String algorithm = "SHA-256";

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        VBox vb = new VBox();
        HBox hb = new HBox();
        HBox algorithmHB = new HBox();
        root.getChildren().add(vb);
        TextArea infoLabel = new TextArea("Info Text");
        infoLabel.setWrapText(true);
        infoLabel.setEditable(false);
        infoLabel.setPrefWidth(600);
        infoLabel.setPrefHeight(100);

        vb.getChildren().addAll(hb, algorithmHB, infoLabel);
        vb.setPadding(new Insets(10.0));
        vb.setSpacing(30);

        Label algorithmLabel = new Label("Algorithm : " + algorithm);
        algorithmLabel.setPrefWidth(120);
        algorithmLabel.setTooltip(new Tooltip("Default : SHA-256"));
        Button sha1Btn = new Button("SHA-1");
        Button sha256Btn = new Button("SHA-256");
        Button sha512Btn = new Button("SHA-512");
        Button md5Btn = new Button("MD5");
        algorithmHB.getChildren().addAll(algorithmLabel, sha1Btn, sha256Btn, sha512Btn, md5Btn);
        algorithmHB.setSpacing(20.0);
        sha1Btn.setOnAction(e -> {
            Button b = (Button) e.getSource();
            this.setAlgorithm(b.getText());
            algorithmLabel.setText("Algorithm : " + algorithm);
        });
        sha256Btn.setOnAction(e -> {
            Button b = (Button) e.getSource();
            this.setAlgorithm(b.getText());
            algorithmLabel.setText("Algorithm : " + algorithm);
        });
        sha512Btn.setOnAction(e -> {
            Button b = (Button) e.getSource();
            this.setAlgorithm(b.getText());
            algorithmLabel.setText("Algorithm : " + algorithm);
        });
        md5Btn.setOnAction(e -> {
            Button b = (Button) e.getSource();
            this.setAlgorithm(b.getText());
            algorithmLabel.setText("Algorithm : " + algorithm);
        });

        Button fileSelectBtn = new Button("Load");
        fileSelectBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            try {
                path = fileChooser.showOpenDialog(stage).getAbsolutePath();
            } catch (Exception exception) {
            }
        });

        TextField code = new TextField();
        code.setTooltip(new Tooltip("Input Hash Code"));
        code.setPromptText("Hash Code");
        code.setPrefWidth(300);

        Button checkStartBtn = new Button("Check");
        checkStartBtn.setOnAction(e -> {
            try {
                String hashCode = checkSum(path, algorithm);
                if (hashCode.equalsIgnoreCase(code.getText())) {
                    infoLabel.setText("Checked Successfully\n\n" + algorithm);
                } else {
                    infoLabel.setText("Checked Failed\n\n" + algorithm + " : " + hashCode);
                }
            } catch (Exception exception) {
                infoLabel.setText("Invalid File Path");
            }
        });

        hb.getChildren().addAll(fileSelectBtn, code, checkStartBtn);
        hb.setSpacing(20.0);

        stage.setScene(new Scene(root));
        stage.setTitle("FileChecker");
        stage.getIcons().add(new Image("/FileChecker/icon.jpg"));
        stage.setResizable(false);
        stage.show();
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public static String checkSum(String filePath, String algorithm) throws Exception {
        StringBuilder ans = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance(algorithm);
        FileInputStream fis = new FileInputStream(filePath);
        md.update(fis.readAllBytes());
        for (Byte b : md.digest()) {
            ans.append(String.format("%02x", b));
        }
        fis.close();
        return ans.toString();
    }
}
