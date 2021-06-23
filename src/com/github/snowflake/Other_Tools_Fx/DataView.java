package com.github.snowflake.Other_Tools_Fx;

import com.github.SnowFlakes.File.FastaFile;
import com.github.SnowFlakes.IO.FastaReaderExtension;
import com.github.SnowFlakes.unit.StatUnit;
import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.TreeFile;
import htsjdk.samtools.reference.ReferenceSequence;
import htsjdk.samtools.util.StringUtil;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;

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
            long AT_count = 0, CG_count = 0, N_count = 0;
            //---------------------------------stat---------------------------------
            StatUnit fasta_stat = new StatUnit();
            fasta_stat.analys(new FastaFile(select_file));
            //----------------------------------------------------------------------
            dialog.close();
            TextArea area = new TextArea();

//            ObservableList<XYChart.Data>
            StackedBarChart<String, Number> bc = new StackedBarChart<>(new CategoryAxis(), new NumberAxis());
            bc.getYAxis().setLabel("Count");
            bc.setTitle("CG Contact");
            HashMap<String, int[]> fasta_map = fasta_stat.base_count_sum;
            XYChart.Series series_A = new XYChart.Series();
            XYChart.Series series_T = new XYChart.Series();
            XYChart.Series series_C = new XYChart.Series();
            XYChart.Series series_G = new XYChart.Series();
            XYChart.Series series_N = new XYChart.Series();
            series_A.setName("A");
            series_T.setName("T");
            series_C.setName("C");
            series_G.setName("G");
            series_N.setName("N");
            List<String> keys = Arrays.asList(fasta_map.keySet().toArray(new String[0]));
            Collections.sort(keys);
            for (String s : keys) {
                int[] count = fasta_map.get(s);
                AT_count += count['a'] + count['A'] + count['t'] + count['T'];
                CG_count += count['c'] + count['C'] + count['g'] + count['G'];
                N_count += count['n'] + count['N'];
                long sum = count['a'] + count['A'] + count['t'] + count['T'] + count['c'] + count['C'] + count['g'] + count['G'] + count['n'] + count['N'];
                series_A.getData().add(new XYChart.Data<>(s, (float) (count['a'] + count['A']) / sum));
                series_T.getData().add(new XYChart.Data<>(s, (float) (count['t'] + count['T']) / sum));
                series_C.getData().add(new XYChart.Data<>(s, (float) (count['c'] + count['C']) / sum));
                series_G.getData().add(new XYChart.Data<>(s, (float) (count['g'] + count['G']) / sum));
                series_N.getData().add(new XYChart.Data<>(s, (float) (count['n'] + count['N']) / sum));
                area.appendText(s + ":" + new DecimalFormat(",###").format(sum)+"\n");
            }
            bc.getData().addAll(series_A, series_T, series_C, series_G, series_N);
            javafx.collections.ObservableList<PieChart.Data> data = javafx.collections.FXCollections.observableArrayList(new PieChart.Data("CG", CG_count), new PieChart.Data("AT", AT_count));
            PieChart chart = new PieChart(data);
            chart.setTitle("CG contact");
            chart.setLegendSide(Side.RIGHT);
            GridPane pane = new GridPane();
            pane.add(bc, 0, 0);
            pane.add(chart, 1, 0);
            Figure_Show_AnchorPane.getChildren().addAll(pane);

            area.appendText("sequence length: " + new DecimalFormat(",###").format(AT_count + CG_count) + "\n");
            area.appendText("sequence length (include N): " + new DecimalFormat(",###").format(AT_count + CG_count + N_count) + "\n");
            area.setEditable(false);
            Data_Show_AnchorPane.getChildren().add(area);
        }
    }
}
