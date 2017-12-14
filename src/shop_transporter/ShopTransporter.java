package shop_transporter;

import UI.AgentFrame;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAException;
import shop_manager.ShopManager;
import shop_transporter.service.TransporterReceive;
import shop_transporter.service.TransporterRegister;
import util.Services;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopTransporter extends Agent {
    public AgentFrame windowsForm;
    public List<AID> participantList;
    public int currentPrice = 0;
    public AID currentWinner = null;
    int maxStartingPrice = 100;
    int auctionCallInterval = 5000;

    @Override
    public void setup() {
        Object[] args = this.getArguments();
        if (args != null) {
            windowsForm = (AgentFrame) args[0];
        }

        participantList = new LinkedList<>();
        currentPrice = (new Random()).nextInt(maxStartingPrice);

        windowsForm.setTitle(this.getName());
        windowsForm.setVisible(true);
        try {
            Services.RegisterService("ShopService", this);
        } catch (FIPAException ex) {
            Logger.getLogger(ShopManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowsForm.AddTextLine("Registered ShopService");

        addBehaviour(new TransporterRegister(this));
        addBehaviour(new TransporterReceive(this));
    }
}
