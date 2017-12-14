package shop_seller.service;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import shop_seller.ShopSeller;
import util.StaticItems;

public class SellerRegister extends OneShotBehaviour {

    private ShopSeller myAgent;

    public SellerRegister(ShopSeller a)
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

        myAgent.windowsForm.AddTextLine("Seller register");
    }
}
