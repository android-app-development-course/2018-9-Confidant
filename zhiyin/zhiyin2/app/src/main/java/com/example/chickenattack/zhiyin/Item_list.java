package com.example.chickenattack.zhiyin;

/**
 * Created by Chicken Attack on 2018/12/27.
 */

public class Item_list {

    private Item item_main,item_other;

    public Item_list(Item itemmain,Item itemother){
        item_main=new Item(itemmain.getUserImage(),itemmain.getName(),itemmain.getUersSign(),itemmain.getSign(),itemmain.getDetails(),itemmain.getVideo(),itemmain.getKeyword(),itemmain.getDetailsImage());
        item_other=new Item(itemother.getUserImage(),itemother.getName(),itemother.getUersSign(),itemother.getSign(),itemother.getDetails(),itemother.getVideo(),itemother.getKeyword(),itemother.getDetailsImage());
    }

    public Item getItem_main(){
        return item_main;
    }
    public Item getItem_other(){
        return item_other;
    }

}
