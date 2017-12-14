package shop_transporter.service;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import shop_buyer.ShopBuyer;
import shop_transporter.ShopTransporter;
import util.CommonUtils;
import util.ObjectItem;
import util.StaticItems;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static util.StaticItems.OFFER_TYPE_FROM_TRANSPORTER;

public class TransporterReceive extends CyclicBehaviour {

    private ShopTransporter myAgent;
    private boolean isGoodOffer = false;

    public TransporterReceive(ShopTransporter a) {
        super(a);
        myAgent = a;
    }

    @Override
    public void action() {
        ACLMessage m = myAgent.receive();
        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);

        if (m != null) {
            String received = m.getContent();

            if (received.contains("registered")) {
                //manager registered me for the auction
                myAgent.windowsForm.AddTextLine("Successfully registered for shop");
            }

            if (received.contains(StaticItems.SEND_OFFER_TRANSPORTER)) {
                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.LIST_TRANSPORTER);
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
