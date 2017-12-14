package shop_buyer.service;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import shop_buyer.ShopBuyer;
import util.CommonUtils;
import util.ObjectItem;
import util.StaticItems;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BuyerReceive extends CyclicBehaviour {

    private ShopBuyer myAgent;
    boolean isGoodOffer=false;

    public BuyerReceive(ShopBuyer a) {
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

            if(received.contains(StaticItems.SEND_OFFER_BUYER)){
                String buyerListItems[] = StaticItems.LIST_BUYER.split("//");

                buyerListItems = Arrays.copyOfRange(buyerListItems, 1, buyerListItems.length);
                List<ObjectItem> objectItemList = CommonUtils.setList5Items(buyerListItems);

                int randomNum = new Random().nextInt(objectItemList.size())- 1;

                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.OFFER_TYPE_FROM_BUYER+"//"+objectItemList.get(1).getItemName()
                        +"//"+objectItemList.get(1).getPrice()+"//"+objectItemList.get(1).getQuantity()
                +"//"+objectItemList.get(1).getPriceTransport()+"//"+objectItemList.get(1).getPriceDepozit());
                myAgent.send(toSend);
            }

            if(received.contains(StaticItems.ALL_GOOD)){
                myAgent.windowsForm.AddTextLine("\nOffer for buying is accepted.");
                toSend.addReceiver(m.getSender());
                toSend.setContent(StaticItems.RETREAT);
                myAgent.send(toSend);
            }

        } else {
            block();
        }
    }
}
