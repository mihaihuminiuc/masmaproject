package shop_manager;

import UI.AgentFrame;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAException;
import shop_manager.services.ManagerReceive;
import shop_manager.services.ManagerSend;
import util.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopManager extends Agent {

    public AgentFrame windowsForm;
    public List<AID> participantList;
    public String offer;
    int auctionCallInterval = 5000;
    public int state;

    public List<ObjectItem> sellerList;
    public List<ObjectItem> transporterList;
    public List<ObjectItem> warehouseList;
    public List<ObjectItem> buyerList;

    public boolean isGoodSeller=false;
    public boolean isGoodTransporter=false;
    public boolean isGoodWarehouse=false;


    public static ShopManager shopManager;

    @Override
    public void setup() {
        Object[] args = this.getArguments();
        if (args != null) {
            windowsForm = (AgentFrame) args[0];
        }

        participantList = new LinkedList<>();

        state = StaticItems.STATE_0;

        windowsForm.setTitle(this.getName());
        windowsForm.setVisible(true);

        try {
            Services.RegisterService("ShopService", this);
        } catch (FIPAException ex) {
            Logger.getLogger(ShopManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        windowsForm.AddTextLine("Registered ShopService");

        shopManager=this;
        addBehaviour(new ManagerReceive(this));
        addBehaviour(new ManagerSend(this, auctionCallInterval));
    }
}
