package com.mdpustudio.resource;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneHandler {

    private Scene paso1, paso2,paso3,paso4,paso5,paso6, slideshowScene;
    private ResourceHandler resourceHandler;
    public int currentpaso, imagecounter = 0;
    public Parent lastAdded;
    public Image imageArray[];
    public Label tit;

    public void initScene(){
        resourceHandler = new ResourceHandler();
        resourceHandler.loadResources();


    }

    public Scene getSingleSlideScene(int number){
        Image imagen;
        switch (number){
            case 1:
                imagen = resourceHandler.getPaso1();
                break;
            case 2:
                imagen = resourceHandler.getPaso2();
                break;
            case 3:
                imagen = resourceHandler.getPaso3();
                break;
            case 4:
                imagen = resourceHandler.getPaso4();
                break;
            case 5:
                imagen = resourceHandler.getCond();
                break;
            default:
                return (new Scene(new VBox()));
        }

        VBox root = new VBox();
        root.setSpacing(0.5);

        VBox titulol = new VBox();
        Label titulo;
        if(number != 5)  titulo = new Label("Paso "+ number);
        else  titulo = new Label("Condiciones de Contorno");

        titulo.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        titulo.setWrapText(true);
        titulol.setAlignment(Pos.BASELINE_CENTER);
        titulol.getChildren().addAll(titulo);





        ImageView imagenv = new ImageView(imagen);
        imagenv.setVisible(true);


        imagenv.setFitWidth(720);
        imagenv.setFitHeight(400);
        imagenv.setStyle("-fx-padding: 16");

        root.setAlignment(Pos.BASELINE_CENTER);
        root.setSpacing(16);

        root.getChildren().addAll(titulol, imagenv);
        return new Scene(root);

    }

    public Scene getPaso1() {
        return paso1;
    }

    public void setPaso1(Scene paso1) {
        this.paso1 = paso1;
    }

    public Scene getPaso2() {
        return paso2;
    }

    public void setPaso2(Scene paso2) {
        this.paso2 = paso2;
    }

    public Scene getPaso3() {
        return paso3;
    }

    public void setPaso3(Scene paso3) {
        this.paso3 = paso3;
    }

    public Scene getPaso4() {
        return paso4;
    }

    public void setPaso4(Scene paso4) {
        this.paso4 = paso4;
    }

    public Scene getPaso5() {
        return paso5;
    }

    public void setPaso5(Scene paso5) {
        this.paso5 = paso5;
    }

    public Scene getPaso6() {
        return paso6;
    }

    public void setPaso6(Scene paso6) {
        this.paso6 = paso6;
    }

    public Scene getSlideshowScene() {
        return slideshowScene;
    }

    public void setSlideshowScene(Scene slideshowScene) {
        this.slideshowScene = slideshowScene;
    }

    public Scene getSlideshow(SceneHandler sceneHandler, int cpaso){
        imageArray = new Image[]{};
        imagecounter = 0;
        currentpaso = cpaso;

        switch (currentpaso){
            case 5:
                imageArray = resourceHandler.getPaso5();
                //paso 5
                break;
            case 6:
                imageArray = resourceHandler.getPaso6();
                //paso 6
                break;
            case 7:
                //Definicion K
                imageArray = resourceHandler.getDefinicionK();
                break;
            case 8:
                //Definicion C
                imageArray = resourceHandler.getDefinicionC();

                break;
            case 9:
                //Definicion L
                imageArray = resourceHandler.getDefinicionL();
                break;
            case 10:
                //Definicion D
                imageArray = resourceHandler.getDefinicionD();
                break;
            case 11:
                //definicion fe
                imageArray = resourceHandler.getDefinicionfe();
                break;
            case 12:
                //dominio
                imageArray = resourceHandler.getModelos();
                break;
            case 13:
                imageArray = resourceHandler.getAsm();
                break;
        }

        VBox root = new VBox();


        VBox titulol = new VBox();
        Label titulo = null;
        if(currentpaso <=6){
            titulo = new Label("Paso "+ currentpaso);
        }else if(currentpaso == 12){
            titulo = new Label("Definición del Dominio");

        }else if(currentpaso > 6 && currentpaso < 12){
            titulo = new Label("Definición de Matrices");
        }else if(currentpaso == 13){
            titulo = new Label("Ensamblaje");

        }
        titulo.setStyle("-fx-padding: 16; -fx-font-size: 24");
        titulo.setWrapText(true);
        tit = titulo;
        titulol.getChildren().addAll(titulo);

        titulol.setAlignment(Pos.BASELINE_CENTER);

        HBox botones = new HBox();
        botones.setSpacing(8);
        Button next  = new Button(">");
        next.getStyleClass().add("buttonSlide");
        Button back = new Button("<");
        back.getStyleClass().add("buttonSlide");
        back.setDisable(true);
        botones.getChildren().addAll(back,next);

        botones.setAlignment(Pos.BASELINE_CENTER);


        ImageView imagenv = new ImageView(imageArray[imagecounter]);

        imagenv.setFitWidth(720);
        imagenv.setFitHeight(400);
        imagenv.setStyle("-fx-padding: 16");



        root.setAlignment(Pos.BASELINE_CENTER);
        root.setSpacing(16);
        root.getChildren().addAll(titulol, imagenv, botones);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(currentpaso == 12){
                    root.getChildren().remove(imagenv);
                    root.getChildren().remove(titulol);
                    imagecounter++;
                    switch (imagecounter){
                        case 0:
                            tit.setText("Definición del Dominio");
                            break;
                        case 1:
                            tit.setText("Condiciones de Dirichlet");
                            break;
                        case 2:
                            tit.setText("Condiciones de Neumann");
                            break;
                        case 3:
                            tit.setText("Definición de Malla");
                            break;
                        case 4:
                            tit.setText("Tabla de Conectividades");
                            break;

                    }
                    imagenv.setImage(imageArray[imagecounter]);
                    root.getChildren().remove(botones);
                    root.getChildren().addAll(titulol, imagenv, botones);

                }else {

                    root.getChildren().remove(imagenv);
                    imagecounter++;
                    imagenv.setImage(imageArray[imagecounter]);
                    root.getChildren().remove(botones);
                    root.getChildren().addAll(imagenv, botones);
                }
                if(imagecounter == imageArray.length-1) next.setDisable(true);
                if(imagecounter >= 0) back.setDisable(false);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(currentpaso == 12){
                    root.getChildren().remove(imagenv);
                    root.getChildren().remove(titulol);
                    imagecounter--;
                    switch (imagecounter){
                        case 0:
                            tit.setText("Definición del Dominio");
                            break;
                        case 1:
                            tit.setText("Condiciones de Dirichlet");
                            break;
                        case 2:
                            tit.setText("Condiciones de Neumann");
                            break;
                        case 3:
                            tit.setText("Definición de Malla");
                            break;
                        case 4:
                            tit.setText("Tabla de Conectividades");
                            break;

                    }
                    imagenv.setImage(imageArray[imagecounter]);
                    root.getChildren().remove(botones);
                    root.getChildren().addAll(titulol, imagenv, botones);

                }else {

                    root.getChildren().remove(imagenv);
                    imagecounter--;
                    imagenv.setImage(imageArray[imagecounter]);
                    root.getChildren().remove(botones);
                    root.getChildren().addAll(imagenv, botones);
                }
                if(imagecounter == 0) back.setDisable(true);
                if(imagecounter <= imageArray.length-1) next.setDisable(false);

            }
        });

        return new Scene(root);






    }
}
