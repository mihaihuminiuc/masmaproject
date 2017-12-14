package util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static List<ObjectItem> setList5Items(String[] items){

        ObjectItem objectItem = new ObjectItem();
        List<ObjectItem> objectItemList=new ArrayList<>();

        for(int i=0;i<items.length-1;i++){
            objectItem.setItemName(items[i]);
            objectItem.setPrice(Integer.valueOf(items[i+1]));
            objectItem.setQuantity(Integer.valueOf(items[i+2]));
            objectItem.setPriceTransport(Integer.valueOf(items[i+3]));
            objectItem.setPriceDepozit(Integer.valueOf(items[i+4]));
            objectItemList.add(objectItem);
            i=i+4;
        }

        return objectItemList;
    }

    public static List<ObjectItem> setList3Items(String[] items){


        List<ObjectItem> objectItemList=new ArrayList<>();

        for(int i=0;i<items.length-1;i++){
            ObjectItem objectItem = new ObjectItem();
            objectItem.setItemName(items[i]);
            objectItem.setPrice(Integer.valueOf(items[i+1]));
            objectItem.setQuantity(Integer.valueOf(items[i+2]));
            objectItemList.add(objectItem);
            i=i+2;
        }

        return objectItemList;
    }

    public static List<ObjectItem> setList2ItemsTransporter(String[] items){


        List<ObjectItem> objectItemList=new ArrayList<>();

        for(int i=0;i<items.length-1;i++){
            ObjectItem objectItem = new ObjectItem();
            objectItem.setQuantity(Integer.valueOf(items[i]));
            objectItem.setPriceTransport(Integer.valueOf(items[i+1]));
            objectItemList.add(objectItem);
            i++;
        }

        return objectItemList;
    }

    public static List<ObjectItem> setList2ItemsWarehouse(String[] items){

        List<ObjectItem> objectItemList=new ArrayList<>();

        for(int i=0;i<items.length-1;i++){
            ObjectItem objectItem = new ObjectItem();
            objectItem.setQuantity(Integer.valueOf(items[i]));
            objectItem.setPriceDepozit(Integer.valueOf(items[i+1]));
            objectItemList.add(objectItem);
            i++;
        }

        return objectItemList;
    }



    public static boolean isGoodOffer3Items(List<ObjectItem> objectItemReceived, List<ObjectItem> objectItemSent){
        boolean isGood=false;

        for(int i = 0; i< objectItemReceived.size(); i++){
            for(int j = 0; j< objectItemSent.size(); j++){
                if(objectItemReceived.get(i).getItemName().equals(objectItemSent.get(j).getItemName())
                        && objectItemReceived.get(i).getPrice()== objectItemSent.get(i).getPrice()
                        && objectItemReceived.get(i).getQuantity() == objectItemSent.get(i).getQuantity()){
                    isGood=true;
                }
            }
        }

        return isGood;
    }

    public static boolean isGoodOffer2Items(List<ObjectItem> objectItemReceived, List<ObjectItem> objectItemSent){
        boolean isGood=false;

        for(int i = 0; i< objectItemReceived.size(); i++){
            for(int j = 0; j< objectItemSent.size(); j++){
                if(objectItemReceived.get(i).getPrice() == objectItemSent.get(j).getPrice()
                        && objectItemReceived.get(i).getQuantity()== objectItemSent.get(i).getQuantity()){
                    isGood=true;
                }
            }
        }

        return isGood;
    }
}
