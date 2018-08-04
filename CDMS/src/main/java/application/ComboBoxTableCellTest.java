package application;

public class ComboBoxTableCellTest // extends Application 
{
/*
    public class Product {
        private String name;
        public Product(String name) {
            this.name = name;
        }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public class Command {
        private Integer quantite;
        private Product product;
        public Command(Product product, Integer quantite) {
            this.product = product;
            this.quantite = quantite;
        }
        public Integer getQuantite() { return quantite; }
        public void setQuantite(Integer quantite) { this.quantite = quantite; }
        public Product getProduct() { return product; }
        public void setProduct(Product product) { this.product = product; }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Product p1 = new Product("Product 1");
        Product p2 = new Product("Product 2");
        final ObservableList<Product> productslst  = FXCollections.observableArrayList(p1, p2);
        ObservableList<Command> commands  = FXCollections.observableArrayList(new Command(p1, 20));
 

        TableView<Command> tv = new TableView<Command>();
        tv.setItems(commands);

        TableColumn<Command, Product> tc = new TableColumn<Command, Product>("Product");
        tc.setMinWidth(140);
        tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Command, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Command, String> p) {
                return new SimpleObjectProperty(p.getValue().getProduct().getName());
            }
        });
        final StringConverter<Product> converter = new StringConverter<ComboBoxTableCellTest.Product>() {

            @Override
            public String toString(Product p) {
                return p.getName();
            }

            @Override
        public Product fromString(String s) {
                // TODO Auto-generated method stub
                return null;
            }
        };

        tc.setCellFactory(new Callback<TableColumn<Command, String>, TableCell<Command, String>>() {
            @Override
            public TableCell<Command, String> call(TableColumn<Command, String> p) {
                *//**
                 * Este Map guardará os objetos Product indexando pelo "name"
                 *//*
                

                ComboBoxTableCell cell = new ComboBoxTableCell(){
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                          
                        	tv.getItems().get(getIndex()).setProduct(products.get(item.toString()));
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        tv.getColumns().add(tc);
        tv.setEditable(true);

        Scene scene = new Scene(tv, 140, 200);
        stage.setScene(scene);
        stage.show();
    }*/
}