/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganisationalStructureLibrary;

/**
 *
 * @author flori
 */
public class Hierarchy {
    private Position head;

    public Hierarchy() {
    }
    
    public Position getHead() {
        return head;
    }

    public void setHead(Position boss) {
        this.head = boss;
    }
}
