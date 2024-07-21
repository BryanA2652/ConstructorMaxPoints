/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constructormaxpoints.modelos;

/**
 *
 * @author ROCIO
 */
public class ConstructoresMax {
     private String name;
    private double totalPoints;

    public ConstructoresMax(String name, double totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public double getTotalPoints() {
        return totalPoints;
    }
    
    
}
