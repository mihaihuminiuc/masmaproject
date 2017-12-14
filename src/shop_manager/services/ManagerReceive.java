package shop_manager.services;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import shop_manager.ShopManager;
import util.CommonUtils;
import util.StaticItems;
import java.util.Arrays;


public class ManagerReceive extends CyclicBehaviour {

    private ShopManager myAgent;

    public ManagerReceive(ShopManager a) {
        super(a);
        myAgent = a;
    }

    @Override
    public void action() {
        ACLMessage m = myAgent.receive();

        if (m != null) {
            String received = m.getContent();
            AID sender = m.getSender();

            if (received != null && received.contains(StaticItems.REGISTERING)) {
                //sender wants to register for auction
                myAgent.participantList.add(sender);

                //reply to sender that registration was successful
                ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
                reply.setContent("registered");
                reply.addReceiver(sender);
                myAgent.send(reply);

                myAgent.windowsForm.AddTextLine("New participant: " + sender.getLocalName());
            }

            if(received != null) {
                if(received.contains(StaticItems.OFFER_TYPE_FROM_BUYER) && myAgent.state==StaticItems.STATE_1){
                    myAgent.windowsForm.AddTextLine("Received from " + sender.getLocalName() + " : " + received);

                    received = received.replace(StaticItems.OFFER_TYPE_FROM_BUYER, "");
                    myAgent.offer = received;

                    String segmentsReceived[] = received.split("//");
                    segmentsReceived = Arrays.copyOfRange(segmentsReceived, 1, segmentsReceived.length);
                    myAgent.buyerList = CommonUtils.setList5Items(segmentsReceived);

                    myAgent.state=StaticItems.STATE_2;
                }else if(received.contains(StaticItems.OFFER_TYPE_FROM_SELLER) && myAgent.state==StaticItems.STATE_2){
                    myAgent.windowsForm.AddTextLine("Received from " + sender.getLocalName() + " : " + received);

                    received = received.replace(StaticItems.OFFER_TYPE_FROM_SELLER, "");
                    myAgent.offer = received;

                    String segmentsReceived[] = received.split("//");
                    segmentsReceived = Arrays.copyOfRange(segmentsReceived, 1, segmentsReceived.length);
                    myAgent.sellerList = CommonUtils.setList3Items(segmentsReceived);

                    myAgent.state=StaticItems.STATE_3;
                }else if(received.contains(StaticItems.OFFER_TYPE_FROM_TRANSPORTER) && myAgent.state==StaticItems.STATE_3){
                    myAgent.windowsForm.AddTextLine("Received from " + sender.getLocalName() + " : " + received);

                    received = received.replace(StaticItems.OFFER_TYPE_FROM_TRANSPORTER, "");
                    myAgent.offer = received;

                    String segmentsReceived[] = received.split("//");
                    segmentsReceived = Arrays.copyOfRange(segmentsReceived, 1, segmentsReceived.length);
                    myAgent.transporterList = CommonUtils.setList2ItemsTransporter(segmentsReceived);

                    myAgent.state=StaticItems.STATE_4;
                }else if(received.contains(StaticItems.OFFER_TYPE_FROM_WAREHOUSE) && myAgent.state==StaticItems.STATE_4){
                    myAgent.windowsForm.AddTextLine("Received from " + sender.getLocalName() + " : " + received);

                    received = received.replace(StaticItems.OFFER_TYPE_FROM_WAREHOUSE, "");
                    myAgent.offer = received;

                    String segmentsReceived[] = received.split("//");
                    segmentsReceived = Arrays.copyOfRange(segmentsReceived, 1, segmentsReceived.length);
                    myAgent.warehouseList = CommonUtils.setList2ItemsWarehouse(segmentsReceived);

                    myAgent.state=StaticItems.STATE_5;
                }
            }

            if (received != null && received.contains("retreat")) {
                //agent retreated
                myAgent.participantList.remove(sender);
                myAgent.windowsForm.AddTextLine(sender.getLocalName() + " retreated.");
            }

        } else {
            block();
        }
    }
}
