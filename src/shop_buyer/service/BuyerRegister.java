package shop_buyer.service;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import shop_buyer.ShopBuyer;
import util.StaticItems;

public class BuyerRegister extends OneShotBehaviour {

    private ShopBuyer myAgent;

    public BuyerRegister(ShopBuyer a)
    {
        super(a);
        myAgent = a;
    }

    @Override
    public void action()
    {
        //inform managerAgent that I want to register for the auction

        ACLMessage m = new ACLMessage(ACLMessage.INFORM);

        AID receiverAID = new AID("ShopManager", AID.ISLOCALNAME);
        m.addReceiver(receiverAID);
        m.setContent(StaticItems.REGISTERING);
        myAgent.send(m);

        myAgent.windowsForm.AddTextLine("Buyer register");
    }
}