package util;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import javax.swing.*;

public class Services {

    // Returns the AID of the first found service provider

    public static AID FindService(String serviceName, Agent myAgent, int timeOut) throws FIPAException {
        AID providerAID = null;
        boolean found = false;

        double t1 = System.currentTimeMillis();
        while (!found) {
            if (System.currentTimeMillis() - t1 > timeOut) {
                break;
            }

            // search for a provider
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(serviceName);
            template.addServices(sd);

            DFAgentDescription[] result = DFService.search(myAgent, template);
            if (result != null && result.length > 0) {
                providerAID = result[0].getName();
                found = true;
            }
        }

        return providerAID;
    }

    // Registers a service on behalf of an agent
    public static void RegisterService(String serviceName, Agent myAgent) throws FIPAException {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(myAgent.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceName);
        sd.setName(serviceName);
        dfd.addServices(sd);
        DFService.register(myAgent, dfd);
    }

    // Deregisters a service on behalf of an agent
    public static void DeregisterService(Agent myAgent) throws FIPAException {
        DFService.deregister(myAgent);
    }

    // Handles the messages associated with the take down of an agent, and deregisters the service on its behalf
    public static void DeregisterServiceOnTakeDown(Agent myAgent) throws FIPAException {
        DFService.deregister(myAgent);
        MessageBox(
                "Agent " + myAgent.getLocalName()
                        + " was taken down.\r\nAn unhandled exception probably occured.");
    }

    public static void MessageBox(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
