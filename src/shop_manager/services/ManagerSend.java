package shop_manager.services;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import shop_manager.ShopManager;
import util.StaticItems;

import java.util.ArrayList;
import java.util.List;

public class ManagerSend extends TickerBehaviour {

    private ShopManager myAgent;


    public ManagerSend(ShopManager a, long period)
    {
        super(a, period);
        myAgent = a;
    }

    @Override
    public void onTick()
    {
        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
        List<AID> buyers = new ArrayList<AID>();
        List<AID> sellers = new ArrayList<AID>();
        List<AID> warehousers = new ArrayList<AID>();
        List<AID> transporters = new ArrayList<AID>();

        for (AID agent : myAgent.participantList) {
            if (agent.getLocalName().contains(StaticItems.BUYER) && !buyers.contains(agent)) {
                buyers.add(agent);
            } else if (agent.getLocalName().contains(StaticItems.SELLER) && !sellers.contains(agent)) {
                sellers.add(agent);
            } else if (agent.getLocalName().contains(StaticItems.WAREHOUSE) && !warehousers.contains(agent)) {
                warehousers.add(agent);
            } else if (agent.getLocalName().contains(StaticItems.TRANSPORTER) && !transporters.contains(agent)) {
                transporters.add(agent);
            }
        }

        if (buyers.size() > 0 && sellers.size() > 0 && transporters.size() > 0 && warehousers.size() > 0 && myAgent.state == StaticItems.STATE_0){
            myAgent.state = StaticItems.STATE_1;
        }

        switch (myAgent.state){
            case StaticItems.STATE_1:
                toSend.setContent(StaticItems.SEND_OFFER_BUYER);
                sendMessageToAllAgents(buyers,toSend);
                break;
            case StaticItems.STATE_2:
                toSend.setContent(StaticItems.SEND_OFFER_SELLER);
                sendMessageToAllAgents(sellers,toSend);
                break;
            case StaticItems.STATE_3:
                toSend.setContent(StaticItems.SEND_OFFER_TRANSPORTER);
                sendMessageToAllAgents(transporters,toSend);
                break;
            case StaticItems.STATE_4:
                toSend.setContent(StaticItems.SEND_OFFER_WAREHOUSE);
                sendMessageToAllAgents(warehousers,toSend);
                break;
            case StaticItems.STATE_5:
                if(myAgent.buyerList!=null && myAgent.buyerList.size()>0
                    && myAgent.sellerList!=null && myAgent.sellerList.size()>0
                    && myAgent.transporterList!=null && myAgent.transporterList.size()>0
                    && myAgent.warehouseList!=null && myAgent.warehouseList.size()>0){

                    for(int j=0;j<myAgent.sellerList.size();j++){
                        for(int i=0;i<myAgent.buyerList.size();i++){
                            if(myAgent.buyerList.get(i).getItemName().equals(myAgent.sellerList.get(j).getItemName())
                                    && myAgent.buyerList.get(i).getPrice() <= myAgent.sellerList.get(j).getPrice()
                                    && myAgent.buyerList.get(i).getQuantity() <= myAgent.sellerList.get(j).getQuantity()) {
                                myAgent.isGoodSeller = true;
                                break;
                            }
                        }
                    }

                    if(myAgent.isGoodSeller){
                        for(int j=0;j<myAgent.transporterList.size();j++){
                            for(int i=0;i<myAgent.buyerList.size();i++){
                                if(myAgent.buyerList.get(i).getQuantity() <= myAgent.transporterList.get(j).getQuantity()
                                        && myAgent.buyerList.get(i).getPriceTransport() <= myAgent.transporterList.get(j).getPriceTransport()) {
                                    myAgent.isGoodTransporter = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(myAgent.isGoodTransporter){
                        for(int j=0;j<myAgent.warehouseList.size();j++){
                            for(int i=0;i<myAgent.buyerList.size();i++){
                                if(myAgent.buyerList.get(i).getQuantity() <= myAgent.warehouseList.get(j).getQuantity()
                                        && myAgent.buyerList.get(i).getPriceDepozit() <= myAgent.warehouseList.get(j).getPriceDepozit()) {
                                    myAgent.isGoodWarehouse = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(myAgent.isGoodWarehouse && myAgent.state == StaticItems.STATE_5){
                        toSend.setContent(StaticItems.ALL_GOOD);
                        sendMessageToAllAgents(buyers,toSend);
                        sendMessageToAllAgents(sellers,toSend);
                        sendMessageToAllAgents(transporters,toSend);
                        sendMessageToAllAgents(warehousers,toSend);
                        myAgent.state=StaticItems.STATE_0;

                    }
                }
                break;
        }
    }

    //send message to all agents from a specific list
    public void sendMessageToAllAgents(List<AID> agentList, ACLMessage toSend)
    {
        for (AID a : agentList) {
            toSend.addReceiver(a);
        }

        myAgent.send(toSend);

        myAgent.windowsForm.AddTextLine("\r\n" + toSend.getContent());
    }
}
