package shop_buyer;

import UI.AgentFrame;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAException;
import shop_buyer.service.BuyerReceive;
import shop_buyer.service.BuyerRegister;
import shop_manager.ShopManager;
import util.Services;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopBuyer extends Agent {
    public AgentFrame windowsForm;
    public AID providerAID = null;
    int auctionCallInterval = 5000;


    @Override
    public void setup() {
        Object[] args = this.getArguments();
        if (args != null) {
            windowsForm = (AgentFrame) args[0];
        }

        windowsForm.setTitle(this.getName());
        windowsForm.setVisible(true);

        try {
            Services.RegisterService("ShopService", this);
        } catch (FIPAException ex) {
            Logger.getLogger(ShopManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowsForm.AddTextLine("Registered ShopService");

        addBehaviour(new BuyerRegister(this));
        addBehaviour(new BuyerReceive(this));
    }
}
