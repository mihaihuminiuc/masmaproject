import UI.AgentFrame;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class MASInit {

    public static void DoInitialization() throws ControllerException, InterruptedException
    {
        //create main container
        jade.wrapper.AgentContainer mainContainer = CreateContainer("Container1M", true, "localhost", "", "1090");
        mainContainer.start();

        AgentFrame managerAgentForm = new AgentFrame();
        managerAgentForm.setLocation(50, 50);

        AgentFrame agent1Form = new AgentFrame();
        agent1Form.setLocation(500, 50);

        AgentFrame agent2Form = new AgentFrame();
        agent2Form.setLocation(50, 400);

        AgentFrame agent3Form = new AgentFrame();
        agent3Form.setLocation(500, 400);

        AgentFrame agent4Form = new AgentFrame();
        agent4Form.setLocation(1000, 400);

        //create and start auction manager and participants
        jade.wrapper.AgentController managerAg =
                CreateAgent(mainContainer, "ShopManager", "shop_manager.ShopManager", new Object[] { managerAgentForm });

        jade.wrapper.AgentController ag1 =
                CreateAgent(mainContainer, "ShopBuyer", "shop_buyer.ShopBuyer", new Object[] { agent1Form });

        jade.wrapper.AgentController ag2 =
                CreateAgent(mainContainer, "ShopSeller", "shop_seller.ShopSeller", new Object[] { agent2Form });

        jade.wrapper.AgentController ag3 =
                CreateAgent(mainContainer, "ShopTransporter", "shop_transporter.ShopTransporter", new Object[] { agent3Form });

        jade.wrapper.AgentController ag4 =
                CreateAgent(mainContainer, "ShopWarehouseKeeper", "shop_warehouse.ShopWarehouseKeeper", new Object[] { agent4Form });

        managerAg.start();
        Thread.sleep(10);
        ag1.start();
        ag2.start();
        ag3.start();
        ag4.start();
    }


    /*
    Create a container:

    hostAddress = the IP address of the host
    hostPort = the port through which the host communicates
    localPort = the local port through which agents communicate
     */
    private static jade.wrapper.AgentContainer CreateContainer(String containerName, boolean isMainContainer, String hostAddress, String hostPort, String localPort)
    {
        ProfileImpl p = new ProfileImpl();

        if (containerName.isEmpty() == false) {
            p.setParameter(Profile.CONTAINER_NAME, containerName);
        }

        p.setParameter(Profile.MAIN, String.valueOf(isMainContainer));

        if (localPort != null) {
            p.setParameter(Profile.LOCAL_PORT, localPort);
        }

        if (hostAddress.isEmpty() == false) {
            p.setParameter(Profile.MAIN_HOST, hostAddress);
        }

        if (hostPort.isEmpty() == false) {
            p.setParameter(Profile.MAIN_PORT, hostPort);
        }

        if (isMainContainer == true)
        {
            return Runtime.instance().createMainContainer(p);
        }
        else
        {
            return Runtime.instance().createAgentContainer(p);
        }
    }

    private static jade.wrapper.AgentController CreateAgent(jade.wrapper.AgentContainer container, String agentName, String agentClass, Object[] args) throws StaleProxyException
    {
        return container.createNewAgent(agentName, agentClass, args);
    }
}
