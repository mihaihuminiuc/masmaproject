package util;

public class StaticItems {
    public final static String BUYER = "ShopBuyer";
    public final static String SELLER = "ShopSeller";
    public final static String WAREHOUSE = "ShopWarehouseKeeper";
    public final static String TRANSPORTER = "ShopTransporter";

    public final static String SEND_OFFER_BUYER="offer_buyer";
    public final static String SEND_OFFER_SELLER="offer_seller";
    public final static String SEND_OFFER_TRANSPORTER="offer_transporter";
    public final static String SEND_OFFER_WAREHOUSE="offer_warehouse";

    public final static String OFFER_TYPE_FROM_BUYER ="offer_from_buyer";
    public final static String OFFER_TYPE_FROM_SELLER ="offer_from_seller";
    public final static String OFFER_TYPE_FROM_TRANSPORTER ="offer_from_transport";
    public final static String OFFER_TYPE_FROM_WAREHOUSE ="offer_from_warehouse";

    public final static String REGISTERING="registering";
    public final static String RETREAT="retreat";
    public final static String ALL_GOOD="all_good";


    public final static int STATE_0=0;
    public final static int STATE_1=1;
    public final static int STATE_2=2;
    public final static int STATE_3=3;
    public final static int STATE_4=4;
    public final static int STATE_5=5;
    public final static int STATE_6=6;

    //nume_produs//pret//cantitate//prettransport/pretdepozitare
    public final static String LIST_BUYER= StaticItems.OFFER_TYPE_FROM_BUYER +"//" +
            "name_1//20//2//10//10//" +
            "name_2//30//3//10//10//" +
            "name_3//40//4//10//10//" +
            "name_4//50//5//10//10//" +
            "name_5//60//6//10//10";

    //nume_produs//pret//cantitate
    public final static String LIST_SELLER= StaticItems.OFFER_TYPE_FROM_SELLER +"//" +
            "name_1//20//10//" +
            "name_2//30//10//" +
            "name_3//40//10//" +
            "name_4//50//10//" +
            "name_5//60//10";

    //pret//cantitate
    public final static String LIST_TRANSPORTER= StaticItems.OFFER_TYPE_FROM_TRANSPORTER +"//" +
            "2//20//" +
            "3//30//" +
            "4//40//" +
            "5//50//" +
            "6//60";

    //pret//cantitate
    public final static String LIST_WAREHOUSE= StaticItems.OFFER_TYPE_FROM_WAREHOUSE +"//" +
            "2//20//" +
            "3//30//" +
            "4//40//" +
            "5//50//" +
            "6//60";
}
