package com.github.snowflake.Other_Tools_Fx;

import com.github.SnowFlakes.File.FastaFile;
import com.github.SnowFlakes.IO.FastaReaderExtension;
import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.TreeFile;
import htsjdk.samtools.reference.ReferenceSequence;
import htsjdk.samtools.util.StringUtil;
import javafx.beans.Observable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.HashMap;

public class DataView {

    public TreeView<TreeFile> DataView_TreeView;
    public TextField DataView_TextField;
    public Button FileSelectButton;
    public Button Analyse_Button;
    public AnchorPane Data_Show_AnchorPane;
    public AnchorPane Figure_Show_AnchorPane;

    public void init() {
        TreeItem<TreeFile> root = new TreeItem<>(new TreeFile(Path.ProgramPath));
        root.setExpanded(true);
        for (File f : root.getValue().listFiles()) {
            TreeItem<TreeFile> file_view = new TreeItem<>(new TreeFile(f.getPath()));
            if (f.isDirectory()) {
                add_dir(file_view, f);
            }
            root.getChildren().add(file_view);
        }
        DataView_TreeView.setRoot(root);
    }

    private void add_dir(TreeItem<TreeFile> root, File f) {
        if (f.listFiles() != null) {
            for (File i : f.listFiles()) {
                TreeItem<TreeFile> tf = new TreeItem<>(new TreeFile(i.getPath()));
                if (i.isDirectory()) {
                    add_dir(tf, i);
                }
                root.getChildren().add(tf);
            }
        }
    }


    public void ShowSelectedItem(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            TreeItem<TreeFile> selected_item = DataView_TreeView.getSelectionModel().getSelectedItem();
            if (selected_item.isLeaf()) {
                DataView_TextField.setText(selected_item.getValue().getPath());
            }
        }
    }

    public void ChoseFile(MouseEvent mouseEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(Path.ProgramPath));
        File f = chooser.showOpenDialog(new Stage());
        if (f != null) {
            DataView_TextField.setText(f.getPath());
        }
    }

    public void analyse(MouseEvent event) {
        File select_file = new File(DataView_TextField.getText());
        if (!select_file.isFile()) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Warning");
            dialog.setHeaderText("\"" + select_file + "\" is not a file");
            dialog.showAndWait();
            return;
        }
        if (select_file.getName().matches(".+(fasta|fna|fa)$")) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("reading ......");
            dialog.show();
            FastaReaderExtension fasta_reader = new FastaFile(select_file).getReader();
            ReferenceSequence seq;
            HashMap<String,ReferenceSequence> seq_list=new HashMap<>();
            int CG_count=0,seq_len=0,AT_count=0;
            while ((seq = fasta_reader.ReadRecord()) != null) {
                seq_len+=seq.length();
                byte[] char_seq = seq.getBases();
                for(byte c: char_seq){
                    switch (c){
                        case 'c':
                        case 'C':
                        case 'g':
                        case 'G':
                            CG_count++;
                            break;
                        case 'A':
                        case 'a':
                        case 'T':
                        case 't':
                            AT_count++;
                            break;
                    }
                }
                seq_list.put(seq.getName(),seq);
            }
            dialog.close();
            javafx.collections.ObservableList<PieChart.Data> data = javafx.collections.FXCollections.observableArrayList(new PieChart.Data("CG",CG_count),new PieChart.Data("AT",AT_count));
            PieChart chart = new PieChart(data);
            chart.setTitle("CG contact");
            chart.setLegendSide(Side.RIGHT);
            Figure_Show_AnchorPane.getChildren().add(chart);
            TextArea area = new TextArea();
            area.appendText("sequence length: "+new DecimalFormat(",###").format(AT_count+CG_count)+"\n");
            area.appendText("sequence length (include N): "+new DecimalFormat(",###").format(seq_len)+"\n");
            Data_Show_AnchorPane.getChildren().add(area);
        }
    }
}
