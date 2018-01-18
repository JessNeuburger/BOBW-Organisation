package at.htl.maximilianwahl.databasehandler;

import at.htl.florianschwarcz.organisationalstructurelib.*;

public class Relation {

    private Position master;
    private Position slave;
    private String type;

    public Relation(Position master, Position slave, String type) {
        this.master = master;
        this.slave = slave;
        this.type = type;
    }

    public Position getMaster() {
        return master;
    }

    public void setMaster(Position master) {
        this.master = master;
    }

    public Position getSlave() {
        return slave;
    }

    public void setSlave(Position slave) {
        this.slave = slave;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
