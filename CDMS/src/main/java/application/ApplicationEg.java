package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ApplicationEg extends Application {
    @Override
    public void start(Stage stage) {
        // create some sample data.
        TreeMap<String, List<String>> data = new TreeMap<>();

        data.put("AUF_1060589919844_59496", Arrays.asList("AUF_1086686287581_9999", "AUF_1086686329972_10049", "AUF_1079023138936_6682"));
        data.put("AUF_1087981634453_7022", Arrays.asList("AUF_1421268533080_1741", "AUF_1421268568003_1743"));
        data.put("AUF_1421268533080_1741", Arrays.asList("AUF_1421268719761_1776"));
        data.put("AUF_1421272434570_1781", Arrays.asList("AUF_1087981634453_7022"));
        data.put("AUF_1422451819445_14260", Arrays.asList("AUF_1421268568003_1743"));

        String[] rootKeys = {
                "AUF_1060589919844_59496",
                "AUF_1421272434570_1781",
                "AUF_1422451819445_14260"
        };

        // create the tree from the data.
        TreeView<String> tree = createTreeView(
                data,
                rootKeys
        );

        // display the tree.
        Scene scene = new Scene(tree);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Create a TreeView of a set of data
     * given the data and identified roots within the data.
     */
    private TreeView<String> createTreeView(TreeMap<String, List<String>> data,String[] rootKeys) {
        TreeItem<String> root = new TreeItem<>();
        Arrays.stream(rootKeys).sorted().forEach(rootKey ->
             root.getChildren().add(
                 createTreeItem(data, rootKey)
              ));

        TreeView<String> tree = new TreeView<>();
        tree.setRoot(root);
        tree.setShowRoot(false);

        return tree;
    }

    /**
     * Create a TreeItem for a TreeView from a set of data
     * given the data and an identified root within the data.
     */
    private TreeItem<String> createTreeItem(
            TreeMap<String, List<String>> data,
            String rootKey
    ) {
        TreeItem<String> item = new TreeItem<>();
        item.setValue(rootKey);
        item.setExpanded(true);

        List<String> childData = data.get(rootKey);
        if (childData != null) {
            childData.stream()
                .sorted()
                .map(
                        child -> createTreeItem(data, child)
                )
                .collect(
                        Collectors.toCollection(item::getChildren)
                );
        }

        return item;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}