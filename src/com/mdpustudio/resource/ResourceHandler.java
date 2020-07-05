package com.mdpustudio.resource;
import com.mdpustudio.img.ResReference;
import javafx.scene.image.Image;

public class ResourceHandler {
    private String baseurl = "res/";
    private Image paso1;
    private Image paso2;
    private Image paso3;
    private Image paso4;
    private Image[] asm;
    private Image[] paso5;
    private Image[] paso6;
    private Image modelo, neumann, dirichlet, tconectividades, malla;
    private  Image[] definicionK;
    private Image[] definicionC;
    private Image[] definicionL;
    private Image[] definicionD;
    private Image[] definicionfe;
    private Image[] modelos;
    private Image cond;

    public void loadResources(){


        try{
            System.out.println("CARGANDO RECURSOS...");
            paso1 = new Image(ResReference.class.getResource(baseurl + "paso1.PNG").toExternalForm());

            paso2 = new Image(ResReference.class.getResourceAsStream(baseurl+ "paso2.PNG"));
            paso3 = new Image(ResReference.class.getResourceAsStream(baseurl+ "paso3.PNG"));
            paso4 = new Image(ResReference.class.getResourceAsStream(baseurl+ "paso4.PNG"));
            paso5 = new Image[2];
            for(int i = 0; i < paso5.length; i++){
                paso5[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "paso5" + (i+1) + ".PNG"));
            }
            paso6 = new Image[4];
            for(int i = 0; i < paso6.length; i++){
                paso6[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "paso6" + (i+1) + ".PNG"));

            }
            definicionK = new Image[16];
            for(int i = 0; i < definicionK.length; i++){
                definicionK[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "defk" + (i+1) + ".PNG"));
            }
            definicionC = new Image[6];
            for(int i = 0; i < definicionC.length; i++){
                definicionC[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "defc" + (i+1) + ".PNG"));
            }
            definicionL = new Image[3];
            for(int i = 0; i < definicionL.length; i++){
                definicionL[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "defl" + (i+1) + ".PNG"));
            }
            definicionD = new Image[2];
            for(int i = 0; i < definicionD.length; i++){
                definicionD[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "defd" + (i+1) + ".PNG"));
            }
            definicionfe = new Image[4];
            for(int i = 0; i < definicionfe.length; i++){
                definicionfe[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "deffe" + (i+1) + ".PNG"));
            }

            modelos = new Image[5];
            modelos[0] = new Image(ResReference.class.getResourceAsStream(baseurl+ "modelo.PNG" ));
            modelos[1] = new Image(ResReference.class.getResourceAsStream(baseurl+ "dirichlet.PNG" ));
            modelos[2] = new Image(ResReference.class.getResourceAsStream(baseurl+ "neumann.PNG" ));
            modelos[3] = new Image(ResReference.class.getResourceAsStream(baseurl+ "malla.PNG" ));
            modelos[4] = new Image(ResReference.class.getResourceAsStream(baseurl+ "tconectividades.PNG"));

            asm = new Image[17];
            for(int i = 0; i < asm.length; i++){
                asm[i] = new Image(ResReference.class.getResourceAsStream(baseurl+ "asm" + (i+1) + ".PNG"));
            }

            cond = new Image(ResReference.class.getResourceAsStream(baseurl + "cond.PNG"));
            System.out.println("CORRECTO");


        }catch (NullPointerException e){
            System.out.println("ERROR CARGANDO RECURSO");
            System.out.println(e.getMessage());
        }



    }

    public Image getPaso1() {
        return paso1;
    }

    public Image getPaso2() {
        return paso2;
    }

    public Image getPaso3() {
        return paso3;
    }

    public Image getPaso4() {
        return paso4;
    }

    public Image[] getPaso5() {
        return paso5;
    }

    public Image[] getPaso6() {
        return paso6;
    }

    public Image getModelo() {
        return modelo;
    }

    public Image getNeumann() {
        return neumann;
    }

    public Image getDirichlet() {
        return dirichlet;
    }

    public Image getTconectividades() {
        return tconectividades;
    }

    public Image getMalla() {
        return malla;
    }

    public Image[] getDefinicionK() {
        return definicionK;
    }

    public Image[] getDefinicionC() {
        return definicionC;
    }

    public Image[] getDefinicionL() {
        return definicionL;
    }

    public Image[] getDefinicionD() {
        return definicionD;
    }

    public Image[] getDefinicionfe() {
        return definicionfe;
    }

    public Image[] getModelos() {
        return modelos;
    }

    public Image[] getAsm() {
        return asm;
    }


    public Image getCond() {
        return cond;
    }
}
