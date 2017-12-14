package shop_seller.service;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import shop_seller.ShopSeller;
import util.CommonUtils;
import util.ObjectItem;
import util.StaticItems;

import java.util.Arrays;
import java.util.List;

import static util.StaticItems.OFFER_TYPE_FROM_SELLER;

public class SellerReceive extends CyclicBehaviour {

    private ShopSeller myAgent;

    public SellerReceive(ShopSeller a) {
        super(a);
        myAgent = a;
    }

    @Override
    public void action() {
        ACLMessage m = myAgent.receive();
        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
        boolean isGoodOffer = false;

        if (m != null) {
            String received = m.getContent();

            if (received.contains("registered")) {
                //manager registered me for the auction
                myAgent.windowsForm.AddTextLine("Successfully registered for shop");
            }

            if(received.contains(StaticItems.SEND_OFFER_SELLER)){
                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.LIST_SELLER);
                myAgent.send(toSend);
            }

            if(received.contains(StaticItems.ALL_GOOD)){
                myAgent.windowsForm.AddTextLine("\nSell over.");
                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.RETREAT);
                myAgent.send(toSend);
            }
        } else {
            block();
        }
    }
}
