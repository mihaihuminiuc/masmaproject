package shop_warehouse.service;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import shop_warehouse.ShopWarehouseKeeper;
import util.CommonUtils;
import util.ObjectItem;
import util.StaticItems;

import java.util.Arrays;
import java.util.List;

import static util.StaticItems.OFFER_TYPE_FROM_TRANSPORTER;
import static util.StaticItems.OFFER_TYPE_FROM_WAREHOUSE;


public class WarehouseReceive extends CyclicBehaviour {

    private ShopWarehouseKeeper myAgent;
    private boolean isGoodOffer = false;

    public WarehouseReceive(ShopWarehouseKeeper a) {
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

            if(received.contains(StaticItems.SEND_OFFER_WAREHOUSE)){
                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.LIST_WAREHOUSE);
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
