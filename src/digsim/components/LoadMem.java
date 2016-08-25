package digsim.components;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DigSimFrame;
import digsim.ElectronicComponent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            Ram, Rom, Ram2, Mem,
//            DigSimFrame, DigSim, ElectronicComponent

public class LoadMem extends Stage //Dialog
{

    public LoadMem(DigSimFrame digsimframe, ElectronicComponent electroniccomponent, int i)
    {
//        super(digsimframe, "Programmation de la mémoire", false);
        MaRom = null;
        MaRam = null;
        MaRam2 = null;
        MyTextField = null;
        MyCheckbox = null;
        frame = digsimframe;
        DialogID = i;
        String s = new String();
        DigSimFrame _tmp = frame;
        if(i == 10)
        {
            MaRam = (Ram)electroniccomponent;
            for(int j = 0; j < 32; j++)
                s = s + Mem.toHexa(MaRam.Tab[j]) + "";

        } else
        {
            DigSimFrame _tmp1 = frame;
            if(i == 9)
            {
                MaRom = (Rom)electroniccomponent;
                for(int k = 0; k < 32; k++)
                    s = s + Mem.toHexa(MaRom.Tab[k]) + "";

            } else
            {
                MaRam2 = (Ram2)electroniccomponent;
                for(int l = 0; l < 32; l++)
                    s = s + Mem.toHexa(MaRam2.Tab[l]) + "";

            }
        }
        TextDialogFont = Font.font("Monospaced", 12);
        TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);

        Group root = new Group();
        Scene scene = new Scene(root, 845, 200);
        setScene(scene);


        BorderPane bp = new BorderPane();
        root.getChildren().add(bp);

        GridPane panel = new GridPane();

        panel.getColumnConstraints().setAll(
                //new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE)
                );
        panel.getColumnConstraints().get(0).setHgrow(Priority.ALWAYS);
        //panel.getColumnConstraints().get(1).setHgrow(Priority.ALWAYS);

        panel.getRowConstraints().setAll(
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        panel.getRowConstraints().get(0).setVgrow(Priority.NEVER);
        panel.getRowConstraints().get(1).setVgrow(Priority.NEVER);
        panel.getRowConstraints().get(2).setVgrow(Priority.ALWAYS);

        //panel.setLayout(new GridLayout(3, 1));
        Label label = new Label("Vous pouvez modifier une partie ou la totalité de ces 32 données en veillant à les laisser séparées par un espace.");
        label.setFont(TextDialogFont);
        GridPane.setConstraints(label, 0, 0);
        panel.getChildren().add(label);
        MyCheckbox = new CheckBox("Cochez cette case pour entrer et afficher les données en hexadécimal {00-FF}; sinon, elles seront en base 10 {0-255}.");
        MyCheckbox.setSelected(true);
        MyCheckbox.setFont(TextDialogFont);
        GridPane.setConstraints(MyCheckbox, 1, 0);
        panel.getChildren().add(MyCheckbox);
        bp.setTop(panel);
        FlowPane panel1 = new FlowPane();
        MyTextField = new TextField(s);//, 98);
        MyTextField.setFont(TextDialogFont);
        panel1.getChildren().add(MyTextField);
        bp.setCenter(panel1);
        FlowPane panel2 = new FlowPane();
        label = new Label("Adresse: 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31");
        GridPane.setConstraints(label, 2, 0);
        panel.getChildren().add(label);
        bp.setBottom(panel2);
        panel2.getChildren().add(new Button("Valider"));
        panel2.getChildren().add(new Button("Annuler"));
       // setBounds(40, 40, 845, 200);
        show();
        frame.applet.StatusMessage("Entrez les 32 données mémoire en les séparant par un espace.");
        MyTextField.requestFocus();
    }

    public LoadMem(DigSimFrame digsimframe, String s, int i)
    {
//        super(digsimframe, s, false);
        MaRom = null;
        MaRam = null;
        MaRam2 = null;
        MyTextField = null;
        MyCheckbox = null;
        frame = digsimframe;
        DialogID = i;
        TextDialogFont = Font.font("Monospaced", 12);
        TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);
        Group root = new Group();
        Scene scene = new Scene(root, 845, 200);
        setScene(scene);

//        setFont(TextDialogFont);

        BorderPane bp = new BorderPane();
        root.getChildren().add(bp);

        GridPane panel = new GridPane();

        panel.getColumnConstraints().setAll(
                //new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE)
                );
        panel.getColumnConstraints().get(0).setHgrow(Priority.ALWAYS);
        //panel.getColumnConstraints().get(1).setHgrow(Priority.ALWAYS);

        panel.getRowConstraints().setAll(
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        panel.getRowConstraints().get(0).setVgrow(Priority.NEVER);
        panel.getRowConstraints().get(1).setVgrow(Priority.NEVER);
        panel.getRowConstraints().get(2).setVgrow(Priority.ALWAYS);

//        panel.setLayout(new GridLayout(3, 1));
        Label label = new Label("Entrer n données séparées par un espace (maxi=32); si n<32, le contenu sera automatiquement complété avec 00.");
        label.setFont(TextDialogFont);
        GridPane.setConstraints(label, 0, 0);
        panel.getChildren().add(label);
        MyCheckbox = new CheckBox("Cocher cette case pour entrer et afficher les données en hexadécimal {00-FF}; sinon, elles seront en base 10 {0-255}.");
        MyCheckbox.setSelected(true);
        MyCheckbox.setFont(TextDialogFont);
        GridPane.setConstraints(MyCheckbox, 1, 0);
        panel.getChildren().add(MyCheckbox);
        bp.setTop(panel);
        FlowPane panel1 = new FlowPane();

        String s1 = new String();
        for(int j = 0; j < 32; j++)
            s1 = s1 + "00 ";

        MyTextField = new TextField(s1);//, 98);
        MyTextField.setFont(TextDialogFont);
        panel1.getChildren().add(MyTextField);
        bp.setCenter(panel1);
        FlowPane panel2 = new FlowPane();
        label = new Label("Adresse: 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31");
        GridPane.setConstraints(label, 2, 0);
        panel.getChildren().add(label);
        bp.setBottom(panel2);

        panel2.getChildren().add(new Button("Valider"));
        panel2.getChildren().add(new Button("Annuler"));
//        setBounds(40, 40, 845, 200);
        show();
        frame.applet.StatusMessage("Entrez les 32 données mémoire en les séparant par un espace.");
        MyTextField.requestFocus();
    }

    public void paint(GraphicsContext g)
    {
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
            close();
            return frame.action(event, "LOADMEM_Cancel_" + DialogID);
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    public boolean action(Event event, Object obj)
    {
//        if(event.target instanceof TextField)
//        {
            close();
            if(MaRom != null)
            {
                if(MyCheckbox.isSelected())
                    MaRom.UpdateMemory("H " + MyTextField.getText());
                else
                    MaRom.UpdateMemory(MyTextField.getText());
                return frame.action(event, "LOADMEM_Cancel_" + DialogID);
            }
            if(MaRam != null)
            {
                if(MyCheckbox.isSelected())
                    MaRam.UpdateMemory("H " + MyTextField.getText());
                else
                    MaRam.UpdateMemory(MyTextField.getText());
                return frame.action(event, "LOADMEM_Cancel_" + DialogID);
            }
            if(MaRam2 != null)
            {
                if(MyCheckbox.isSelected())
                    MaRam2.UpdateMemory("H " + MyTextField.getText());
                else
                    MaRam2.UpdateMemory(MyTextField.getText());
                return frame.action(event, "LOADMEM_Cancel_" + DialogID);
            }
            if(MyCheckbox.isSelected())
                return frame.action(event, "LOADMEM_OK_H " + MyTextField.getText() + "_" + DialogID);
            else
                return frame.action(event, "LOADMEM_OK_" + MyTextField.getText() + "_" + DialogID);
//        }
//        if(event.target instanceof Button)
//        {
//            String s = (String)obj;
//            if(s.equals("Valider"))
//            {
//                close();
//                if(MaRom != null)
//                {
//                    if(MyCheckbox.isSelected())
//                        MaRom.UpdateMemory("H " + MyTextField.getText());
//                    else
//                        MaRom.UpdateMemory(MyTextField.getText());
//                    return frame.action(event, "LOADMEM_Cancel_" + DialogID);
//                }
//                if(MaRam != null)
//                {
//                    if(MyCheckbox.isSelected())
//                        MaRam.UpdateMemory("H " + MyTextField.getText());
//                    else
//                        MaRam.UpdateMemory(MyTextField.getText());
//                    return frame.action(event, "LOADMEM_Cancel_" + DialogID);
//                }
//                if(MaRam2 != null)
//                {
//                    if(MyCheckbox.isSelected())
//                        MaRam2.UpdateMemory("H " + MyTextField.getText());
//                    else
//                        MaRam2.UpdateMemory(MyTextField.getText());
//                    return frame.action(event, "LOADMEM_Cancel_" + DialogID);
//                }
//                if(MyCheckbox.isSelected())
//                    return frame.action(event, "LOADMEM_OK_H " + MyTextField.getText() + "_" + DialogID);
//                else
//                    return frame.action(event, "LOADMEM_OK_" + MyTextField.getText() + "_" + DialogID);
//            }
//            if(s.equals("Annuler"))
//            {
//                close();
//                return frame.action(event, "LOADMEM_Cancel_" + DialogID);
//            }
//        } else
//        if(event.target instanceof CheckBox)
//            if(MyCheckbox.isSelected())
//                MyTextField.setText(Mem.TranslateToHexa(MyTextField.getText()));
//            else
//            if(!MyCheckbox.isSelected())
//                MyTextField.setText(Mem.TranslateToInt(MyTextField.getText()));
//        return false;
    }

    DigSimFrame frame;
    String text;
    Rom MaRom;
    Ram MaRam;
    Ram2 MaRam2;
    protected Font TextDialogFont;
    protected FontMetrics TextDialogFontMetrics;
    TextField MyTextField;
    CheckBox MyCheckbox;
    int DialogID;
}
