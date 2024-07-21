/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package constructormaxpoints;

import constructormaxpoints.modelos.ConstructoresMax;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author ROCIO
 */
public class ConstructorMaxPoints extends Application {
    
    private String driver = "com.mysql.jdbc.Driver";
    private String cadenaconeccion = "jdbc:mysql://localhost:3306/formula01";
    private String usuario = "root";
    private String contraseña = "";
    public Connection con;
    Statement st;
    ResultSet rs;
    String sql = "SELECT d.name, SUM(r.points) AS total_points " +
                           "FROM results AS r " +
                           "JOIN constructorss AS d ON r.constructorId = d.constructorId " +
                           "GROUP BY d.name " +
                           "ORDER BY total_points DESC " +
                           "LIMIT 10";
    
    private TableView<ConstructoresMax> tableView;
    
       public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
         tableView = new TableView<>();
        TableColumn<ConstructoresMax, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ConstructoresMax, Double> pointsColumn = new TableColumn<>("Total Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        tableView.getColumns().addAll(nameColumn, pointsColumn);

        VBox vbox = new VBox(tableView);
        
        Scene scene = new Scene(vbox, 400, 350);
        
        primaryStage.setTitle("Constructores con más puntos - Ayala");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ConstructorMaxPoints();
    }
    
    private void ConstructorMaxPoints() {
        ObservableList<ConstructoresMax> data = FXCollections.observableArrayList();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadenaconeccion, usuario, contraseña);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            

            while (rs.next()) {
                String name = rs.getString("name");
                double totalPoints = rs.getDouble("total_points");
                data.add(new ConstructoresMax(name, totalPoints));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableView.setItems(data);
    }
}

   
 
          

