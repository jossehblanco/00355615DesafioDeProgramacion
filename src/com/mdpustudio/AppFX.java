package com.mdpustudio;

import com.mdpustudio.img.ResReference;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.mdpustudio.resource.SceneHandler;

public class AppFX extends Application {

    public int currentpaso;
    public Parent lastAdded;
    private SceneHandler sceneHandler;

    @Override
    public void init() throws Exception {
        sceneHandler = new SceneHandler();

    }

    @Override
    public void start(Stage stage) throws Exception {
        sceneHandler.initScene();
        currentpaso = 1;
        stage.setTitle("TSC01/2020 - Desafío de Programación");
        stage.setWidth(910);
        stage.setHeight(720);
        VBox root = new VBox();
        root.getChildren().addAll(createMenu(root));

        Scene menu = new Scene(root);
        menu.getStylesheets().add(ResReference.class.getResource("styles/appstyle.css").toExternalForm());
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(menu);



        stage.show();


    }
    public VBox createPasos(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label("Aplicacion del MEF");
        label1.getStyleClass().add("labelHeader");
        currentpaso = 1;

        label1.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        label1.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);
        titulol.getChildren().add(label1);



        lastAdded = sceneHandler.getSingleSlideScene(1).getRoot();
        lastAdded.setStyle("-fx-padding: 8");

        HBox botones = new HBox();
        Button next  = new Button("Siguiente Paso");
        Button back = new Button("Paso Anterior");
        back.setDisable(true);
        botones.setSpacing(16);
        botones.setAlignment(Pos.CENTER);

        botones.setStyle("-fx-padding: 8");


        Button regresar = new Button("Regresar");

        regresar.setOnAction(backButtonAction(rootnode, root));

        botones.getChildren().addAll(back, next, regresar);
        root.getChildren().addAll(titulol, lastAdded, botones);

        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(lastAdded);
                currentpaso++;
                if(currentpaso <= 4 ){
                    lastAdded = sceneHandler.getSingleSlideScene(currentpaso).getRoot();

                }else if(currentpaso >= 5){
                    lastAdded = sceneHandler.getSlideshow(sceneHandler, currentpaso).getRoot();
                }
                lastAdded.setStyle("-fx-padding: 8");
                root.getChildren().remove(botones);
                root.getChildren().addAll(lastAdded, botones);
                if(currentpaso == 6) next.setDisable(true);
                if(currentpaso >= 1) back.setDisable(false);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(lastAdded);
                currentpaso--;
                if(currentpaso <= 4 ){
                    lastAdded = sceneHandler.getSingleSlideScene(currentpaso).getRoot();
                }else if(currentpaso >= 5){
                    lastAdded = sceneHandler.getSlideshow(sceneHandler, currentpaso).getRoot();
                }
                lastAdded.setStyle("-fx-padding: 8");
                root.getChildren().remove(botones);
                root.getChildren().addAll(lastAdded, botones);
                if(currentpaso == 1) back.setDisable(true);
                if(currentpaso <= 6) next.setDisable(false);

            }
        });

        return root;
    }

    public VBox createMenu(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label(" ");
        label1.getStyleClass().add("labelHeader");
        Label titulo = new Label("Técnicas de Simulación en Computadora");
        Label titulo2 = new Label("Desafío de Programación");
        Label info = new Label("Nombre Estudiante: Josseh Mario Blanco Amaya\n" +
                "Carnet: 00355615\n" +
                "Catedrático: Enmanuel Amaya\n");

        titulo.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        titulo2.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 20");
        info.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 16");


        titulo.setWrapText(true);
        titulo2.setWrapText(true);
        info.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);

        titulol.getChildren().addAll(label1, titulo, titulo2, info);

        VBox botones = new VBox();
        Button modelo  = new Button("Dominio");

        modelo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createModelos(rootnode));
            }
        });
        Button pasos  = new Button("Pasos del MEF");


        pasos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createPasos(rootnode));
            }
        });

        Button matrices = new Button("Definicion de Matrices");

        matrices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createMatrices(rootnode));
            }
        });
        Button ensamblaje = new Button("Ensamblaje");
        ensamblaje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createAsm(rootnode));
            }
        });

        Button contorno = new Button("Condiciones");

        contorno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createCond(rootnode));
            }
        });
        Button exit = new Button("Salir");

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        exit.getStyleClass().add("buttonExit");
        botones.setAlignment(Pos.CENTER);
        botones.setSpacing(8);
        botones.getChildren().addAll(modelo, pasos, matrices, ensamblaje, contorno, exit);
        modelo.getStyleClass().add("buttonMenu");
        pasos.getStyleClass().add("buttonMenu");
        matrices.getStyleClass().add("buttonMenu");
        ensamblaje.getStyleClass().add("buttonMenu");
        contorno.getStyleClass().add("buttonMenu");
        exit.getStyleClass().add("buttonMenu");



        root.getChildren().addAll(titulol, botones);


        return root;
    }

    public VBox createModelos(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label("Definición del Dominio");
        label1.getStyleClass().add("labelHeader");

        label1.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        label1.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);
        titulol.getChildren().add(label1);



        //lastAdded = sceneHandler.getSingleSlideScene(1).getRoot();
        lastAdded = sceneHandler.getSlideshow(sceneHandler, 12).getRoot();
        lastAdded.setStyle("-fx-padding: 8");

        HBox botones = new HBox();
        Button back = new Button("Regresar");
        back.setOnAction(backButtonAction(rootnode, root));
        botones.setSpacing(16);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(back);
        botones.setStyle("-fx-padding: 8");
        root.getChildren().addAll(titulol, lastAdded, botones);


        return root;

    }

    public VBox createMatrices(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label("Definición de Matrices");
        label1.getStyleClass().add("labelHeader");

        label1.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        label1.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);
        titulol.getChildren().add(label1);

        currentpaso = 7;
        lastAdded = sceneHandler.getSlideshow(sceneHandler, 7).getRoot();
        lastAdded.setStyle("-fx-padding: 8");

        HBox botones = new HBox();
        Button next  = new Button("Matriz Siguiente");
        Button back = new Button("Matriz Anterior");
        Button regresar = new Button("Regresar");

        back.setDisable(true);
        botones.setSpacing(16);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(back, next, regresar);
        botones.setStyle("-fx-padding: 8");
        root.getChildren().addAll(titulol, lastAdded, botones);
        regresar.setOnAction(backButtonAction(rootnode, root));
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(lastAdded);
                currentpaso++;
                lastAdded = sceneHandler.getSlideshow(sceneHandler, currentpaso).getRoot();
                lastAdded.setStyle("-fx-padding: 8");
                root.getChildren().remove(botones);
                root.getChildren().addAll(lastAdded, botones);
                if(currentpaso == 11) next.setDisable(true);
                if(currentpaso >= 7) back.setDisable(false);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(lastAdded);
                currentpaso--;
                if(currentpaso <= 4 ){
                    lastAdded = sceneHandler.getSingleSlideScene(currentpaso).getRoot();
                }else if(currentpaso >= 5){
                    lastAdded = sceneHandler.getSlideshow(sceneHandler, currentpaso).getRoot();
                }
                lastAdded.setStyle("-fx-padding: 8");
                root.getChildren().remove(botones);
                root.getChildren().addAll(lastAdded, botones);
                if(currentpaso == 7) back.setDisable(true);
                if(currentpaso <= 11) next.setDisable(false);

            }
        });

        return root;


    }



    public VBox createAsm(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label("Ensamblaje");
        label1.getStyleClass().add("labelHeader");

        label1.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        label1.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);
        titulol.getChildren().add(label1);



        //lastAdded = sceneHandler.getSingleSlideScene(1).getRoot();
        lastAdded = sceneHandler.getSlideshow(sceneHandler, 13).getRoot();
        lastAdded.setStyle("-fx-padding: 8");

        HBox botones = new HBox();
        Button back = new Button("Regresar");
        back.setOnAction(backButtonAction(rootnode, root));
        botones.setSpacing(16);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(back);
        botones.setStyle("-fx-padding: 8");
        root.getChildren().addAll(titulol, lastAdded, botones);


        return root;

    }


    public VBox createCond(VBox rootnode){

        VBox root = new VBox();

        VBox titulol = new VBox();
        Label label1 = new Label("Ensamblaje");
        label1.getStyleClass().add("labelHeader");

        label1.setStyle("-fx-padding: 16; -fx-text-alignment: center; -fx-font-size: 24");
        label1.setWrapText(true);
        titulol.setAlignment(Pos.CENTER);
        titulol.getChildren().add(label1);



        //lastAdded = sceneHandler.getSingleSlideScene(1).getRoot();
        lastAdded = sceneHandler.getSingleSlideScene(5).getRoot();
        lastAdded.setStyle("-fx-padding: 8");

        HBox botones = new HBox();
        Button back = new Button("Regresar");
        back.setOnAction(backButtonAction(rootnode, root));
        botones.setSpacing(16);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(back);
        botones.setStyle("-fx-padding: 8");
        root.getChildren().addAll(titulol, lastAdded, botones);

        return root;

    }

    EventHandler backButtonAction(VBox rootnode, VBox root){

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootnode.getChildren().removeAll(root);
                rootnode.getChildren().add(createMenu(rootnode));
            }
        };
    }

}
