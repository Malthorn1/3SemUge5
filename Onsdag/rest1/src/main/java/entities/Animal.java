/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mikke
 */
public class Animal {
    private String Type; 
    private int birthYear; 
    private String Sound; 

    public Animal() {
    }

    public Animal(String Type, int birthYear, String Sound) {
        this.Type = Type;
        this.birthYear = birthYear;
        this.Sound = Sound;
    }

    
    
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String Sound) {
        this.Sound = Sound;
    }
    
    
    
}
